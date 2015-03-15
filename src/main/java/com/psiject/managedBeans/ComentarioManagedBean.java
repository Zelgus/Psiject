package com.psiject.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;

import com.psiject.entidades.Campo;
import com.psiject.entidades.Comentario;
import com.psiject.services.interfaces.ICampoService;
import com.psiject.services.interfaces.IComentarioService;
import com.psiject.services.interfaces.IExpedientePsicologoService;
import com.psiject.services.interfaces.ITareaCampoService;
import com.psiject.services.interfaces.ITareaService;
import com.psiject.utilidades.Mensajes;
import com.psiject.utilidades.NotificacionUtil;
import com.psiject.utilidades.Validaciones;

@ManagedBean(name = "comentarioMB")
@RequestScoped
public class ComentarioManagedBean implements Serializable {

	private static final long serialVersionUID = 6613370823541458878L;

	@ManagedProperty("#{sessionMB}")
	private SessionManagedBean sessionMB;

	@ManagedProperty("#{navegacionMB}")
	private NavegacionManagedBean navegacionMB;

	@ManagedProperty("#{wizardMB}")
	private WizardManagedBean wizardMB;

	@ManagedProperty(value = "#{ComentarioService}")
	private IComentarioService comentarioService;

	@ManagedProperty(value = "#{TareaService}")
	private ITareaService tareaService;

	@ManagedProperty(value = "#{TareaCampoService}")
	private ITareaCampoService tareaCampoService;

	@ManagedProperty(value = "#{CampoService}")
	private ICampoService campoService;

	@ManagedProperty(value = "#{ExpedientePsicologoService}")
	private IExpedientePsicologoService expedientePsicologoService;

	public ComentarioManagedBean() {
		super();
	}

	public List<Comentario> getComentariosPsicologoDeLaTareaList() {
		List<Comentario> res = new ArrayList<Comentario>();
		try {
			res = this.comentarioService
					.obtenerComentariosPsicologoPorTarea(this.sessionMB
							.getTareaSeleccionada());
			/*
			 * Marcar comentario como visto
			 */
			if (!this.sessionMB.getUsuarioEnSesion().getPermiso()
					&& this.sessionMB.getExpedienteSeleccionado().equals(
							this.sessionMB.getTareaSeleccionada()
									.getExpediente())) {
				for (final Comentario coment : res) {
					if (coment.getUsuarioCreador().getPermiso()
							&& !coment.getVisto()) {
						final Comentario comentAActualizar = new Comentario(
								coment.getIdComentario(),
								coment.getTareaCampo(),
								coment.getUsuarioCreador(),
								coment.getComentario(), coment.getHora(), true);
						this.comentarioService
								.actualizarComentario(comentAActualizar);
					}
				}
			}
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_COMENTARIOS_PSICOLOGO_DE_LA_TAREA_LIST,
					e);
		}
		return res;
	}

	public List<Comentario> getComentariosPacienteDeLaTareaCampoList(
			final Campo campo) {
		List<Comentario> res = new ArrayList<Comentario>();
		try {
			res = this.comentarioService
					.obtenerComentariosPacientePorTareaYCampo(
							this.sessionMB.getTareaSeleccionada(), campo);
			/*
			 * Marcar comentario como visto
			 */
			if (this.sessionMB.getUsuarioEnSesion().getPermiso()
					&& this.sessionMB.getUsuarioEnSesion().equals(
							this.sessionMB.getTareaSeleccionada()
									.getUsuarioCreador())) {
				for (final Comentario coment : res) {
					if (!coment.getUsuarioCreador().getPermiso()
							&& !coment.getVisto()) {
						final Comentario comentAActualizar = new Comentario(
								coment.getIdComentario(),
								coment.getTareaCampo(),
								coment.getUsuarioCreador(),
								coment.getComentario(), coment.getHora(), true);
						this.comentarioService
								.actualizarComentario(comentAActualizar);
					}
				}
			}
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_COMENTARIOS_PACIENTE_DE_LA_TAREA_CAMPO_LIST,
					e);
		}
		return res;
	}

	public void comentarPaciente(final Campo campo) {
		try {
			/*
			 * Rellenamos el mapa de comentarios con el ultimo par
			 * "idCampo - comentario"
			 */
			this.wizardMB.fillLastComent(campo.getIdCampo().toString());

			/*
			 * Almacenamos todos los comentarios en BD
			 */
			Boolean all = true;

			for (final String campoID : this.sessionMB.getComentariosPorCampo()
					.keySet()) {
				final Campo campoAComentar = this.campoService
						.buscarCampoPorId(new Long(campoID));
				String comentarioAAnadir = this.sessionMB
						.getComentariosPorCampo().get(campoID);
				if (comentarioAAnadir.isEmpty()) {
					comentarioAAnadir = "-";
				}
				Validaciones.validarComentarioPaciente(comentarioAAnadir);
				all = this.comentarioService.anadirComentario(
						comentarioAAnadir, new Date(), false,
						this.sessionMB.getTareaSeleccionada(), campoAComentar,
						this.sessionMB.getUsuarioEnSesion());

				if (!all) {
					break;
				}
			}

			/*
			 * Marcamos la tarea como vista (si no lo estaba)
			 */
			if (!this.sessionMB.getUsuarioEnSesion().getPermiso()
					&& !this.sessionMB.getTareaSeleccionada().getVista()) {
				this.sessionMB.getTareaSeleccionada().setVista(true);
				this.tareaService.actualizarTarea(this.sessionMB
						.getTareaSeleccionada());
			}

			/*
			 * Vaciamos el mapa de comentarios
			 */
			this.sessionMB.getComentariosPorCampo().clear();

			if (all) {
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_COMENTAR_PACIENTE);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_COMENTAR_PACIENTE);
			}
			navegacionMB.refrescar();
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_COMENTAR_PACIENTE, e);
			navegacionMB.refrescar();
		}
	}

	public void comentarPsicologo() {
		try {
			Validaciones.validarComentarioPsicologo(this.wizardMB
					.getComentario());

			/*
			 * Se toma el campo de comentarios de psicólogos
			 */
			final Campo campoAComentar = this.campoService
					.obtenerCampoPsicologo();

			/*
			 * Almacenamos el comentario
			 */
			final Boolean aux = this.comentarioService.anadirComentario(
					this.wizardMB.getComentario(), new Date(), false,
					this.sessionMB.getTareaSeleccionada(), campoAComentar,
					this.sessionMB.getUsuarioEnSesion());

			/*
			 * Notificamos al paciente
			 */
			NotificacionUtil.notificacionNuevoComentario(this.sessionMB
					.getExpedienteSeleccionado().getUsuario().getCorreo(),
					this.sessionMB.getExpedienteSeleccionado().getNombre(),
					this.sessionMB.getUsuarioEnSesion().getUsuario(),
					this.sessionMB.getTareaSeleccionada().getTitulo(),
					this.wizardMB.getComentario());

			if (aux) {
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_COMENTAR_PSICOLOGO);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_COMENTAR_PSICOLOGO);
			}
			navegacionMB.refrescar();
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (final MessagingException e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_NOTIFICACION, e);
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_COMENTAR_PACIENTE, e);
			navegacionMB.refrescar();
		}
	}

	public void editarComentario() {
		try {
			if (this.sessionMB.getComentarioSeleccionado() != null) {
				Validaciones.validarComentarioPsicologo(this.sessionMB
						.getComentarioSeleccionado().getComentario());
				this.sessionMB.getComentarioSeleccionado().setVisto(false);
				this.sessionMB.getComentarioSeleccionado().setHora(new Date());
				this.comentarioService.actualizarComentario(this.sessionMB
						.getComentarioSeleccionado());
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_EDITAR_COMENTARIO);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_EDITAR_COMENTARIO);
			}
			navegacionMB.refrescar();
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_EDITAR_COMENTARIO, e);
			navegacionMB.refrescar();
		}
	}

	public void borrarComentario() {
		try {
			if (this.sessionMB.getComentarioSeleccionado() != null) {
				if (this.sessionMB.getComentarioSeleccionado()
						.getUsuarioCreador().getPermiso()) {
					this.comentarioService.borrarComentario(this.sessionMB
							.getComentarioSeleccionado());
				} else {
					this.sessionMB.getComentarioSeleccionado().setComentario(
							"-");
					this.comentarioService.actualizarComentario(this.sessionMB
							.getComentarioSeleccionado());
				}
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_BORRAR_COMENTARIO);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_BORRAR_COMENTARIO);
			}
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_BORRAR_COMENTARIO, e);
			navegacionMB.refrescar();
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */

	public SessionManagedBean getSessionMB() {
		return this.sessionMB;
	}

	public void setSessionMB(final SessionManagedBean sessionMB) {
		this.sessionMB = sessionMB;
	}

	public NavegacionManagedBean getNavegacionMB() {
		return this.navegacionMB;
	}

	public void setNavegacionMB(final NavegacionManagedBean navegacionMB) {
		this.navegacionMB = navegacionMB;
	}

	public WizardManagedBean getWizardMB() {
		return this.wizardMB;
	}

	public void setWizardMB(final WizardManagedBean wizardMB) {
		this.wizardMB = wizardMB;
	}

	public ITareaService getTareaService() {
		return this.tareaService;
	}

	public void setTareaService(final ITareaService tareaService) {
		this.tareaService = tareaService;
	}

	public ITareaCampoService getTareaCampoService() {
		return this.tareaCampoService;
	}

	public void setTareaCampoService(final ITareaCampoService tareaCampoService) {
		this.tareaCampoService = tareaCampoService;
	}

	public ICampoService getCampoService() {
		return this.campoService;
	}

	public void setCampoService(final ICampoService campoService) {
		this.campoService = campoService;
	}

	public IExpedientePsicologoService getExpedientePsicologoService() {
		return this.expedientePsicologoService;
	}

	public void setExpedientePsicologoService(
			final IExpedientePsicologoService expedientePsicologoService) {
		this.expedientePsicologoService = expedientePsicologoService;
	}

	public IComentarioService getComentarioService() {
		return this.comentarioService;
	}

	public void setComentarioService(final IComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}
}