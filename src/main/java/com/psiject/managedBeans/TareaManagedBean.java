package com.psiject.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;

import org.primefaces.model.DualListModel;

import com.psiject.entidades.Campo;
import com.psiject.entidades.Comentario;
import com.psiject.entidades.Tarea;
import com.psiject.services.interfaces.ICampoService;
import com.psiject.services.interfaces.IComentarioService;
import com.psiject.services.interfaces.IExpedientePsicologoService;
import com.psiject.services.interfaces.IExpedienteService;
import com.psiject.services.interfaces.ITareaCampoService;
import com.psiject.services.interfaces.ITareaService;
import com.psiject.utilidades.Constantes;
import com.psiject.utilidades.Mensajes;
import com.psiject.utilidades.NotificacionUtil;
import com.psiject.utilidades.Validaciones;

@ManagedBean(name = "tareaMB")
@RequestScoped
public class TareaManagedBean implements Serializable {

	private static final long serialVersionUID = -2190319967099175736L;

	@ManagedProperty("#{sessionMB}")
	private SessionManagedBean sessionMB;

	@ManagedProperty("#{navegacionMB}")
	private NavegacionManagedBean navegacionMB;

	@ManagedProperty(value = "#{TareaService}")
	private ITareaService tareaService;

	@ManagedProperty(value = "#{TareaCampoService}")
	private ITareaCampoService tareaCampoService;

	@ManagedProperty(value = "#{CampoService}")
	private ICampoService campoService;

	@ManagedProperty(value = "#{ExpedienteService}")
	private IExpedienteService expedienteService;

	@ManagedProperty(value = "#{ExpedientePsicologoService}")
	private IExpedientePsicologoService expedientePsicologoService;

	@ManagedProperty(value = "#{ComentarioService}")
	private IComentarioService comentarioService;

	private String titulo;
	private String descripcion;
	private Boolean importante;

	private DualListModel<String> camposEnNuevaTarea;
	private DualListModel<String> camposEnTarea;

	public TareaManagedBean() {
		super();
	}

	public TareaManagedBean(final String titulo, final String descripcion,
			final Boolean importante) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.importante = importante;
		this.camposEnNuevaTarea = this.cargarCamposEnNuevaTarea();
		this.camposEnTarea = this.cargarCamposEnTarea();
	}

	public List<Tarea> getTareasPendientesDelExpedienteList() {
		final List<Tarea> res = new ArrayList<Tarea>();
		try {
			for (final Tarea t : this.tareaService
					.obtenerTareasPorExpediente(this.sessionMB
							.getExpedienteSeleccionado())) {
				if (!t.getCompletada()) {
					res.add(t);
				}
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_TAREAS_PENDIENTES_DEL_EXPEDIENTE_LIST, e);
		}
		return res;
	}

	public List<Campo> getCamposDeLaTareaList() {
		List<Campo> res = new ArrayList<Campo>();
		try {
			res = this.tareaCampoService.obtenerCamposPorTarea(this.sessionMB
					.getTareaSeleccionada());
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_CAMPOS_DE_LA_TAREA_LIST, e);
		}
		return res;
	}

	public List<Tarea> getTareasCompletadasDelExpedienteList() {
		final List<Tarea> res = new ArrayList<Tarea>();
		try {
			for (final Tarea t : this.tareaService
					.obtenerTareasPorExpediente(this.sessionMB
							.getExpedienteSeleccionado())) {
				if (t.getCompletada()) {
					res.add(t);
				}
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_TAREAS_COMPLETADAS_DEL_EXPEDIENTE_LIST,
					e);
		}
		return res;
	}

	public String getCamposDeLaTarea() {
		String res = Constantes.CADENA_VACIA;
		try {
			for (final Campo c : this.tareaCampoService
					.obtenerCamposPorTarea(this.sessionMB
							.getTareaSeleccionada())) {
				res += c.getNombre() + Constantes.COMA_ESPACIO;
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_GET_CAMPOS_DE_LA_TAREA,
					e);
		}
		if (!res.isEmpty()) {
			res = res.substring(0, res.length() - 2);
			res += Constantes.PUNTO;
		} else {
			res = Constantes.TAREA_SIN_CAMPOS;
		}
		return res;
	}

	public void crearNuevaTarea() {
		try {
			Validaciones.validarTituloTarea(this.titulo);
			Validaciones.validarDescripcionTarea(this.descripcion);
			if (this.sessionMB.getExpedienteSeleccionado() != null) {
				/*
				 * Almacenamos la tarea
				 */
				final Tarea t = this.tareaService.anadirTarea(this.titulo,
						this.descripcion, this.importante, false, false,
						this.sessionMB.getExpedienteSeleccionado(),
						this.sessionMB.getUsuarioEnSesion());
				/*
				 * Añadimos el campo de comentario de psicologo
				 */
				this.anadirCampoPsicologo(t);
				/*
				 * Añadimos los campos
				 */
				this.anadirCamposALaTarea(t);
				/*
				 * Notificamos al paciente
				 */
				NotificacionUtil.notificacionNuevaTarea(this.sessionMB
						.getExpedienteSeleccionado().getUsuario().getCorreo(),
						this.sessionMB.getExpedienteSeleccionado().getNombre(),
						this.sessionMB.getUsuarioEnSesion().getUsuario(),
						this.titulo, this.descripcion);
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_CREAR_NUEVA_TAREA);
				navegacionMB.toExpediente();
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_CREAR_NUEVA_TAREA);
				navegacionMB.refrescar();
			}
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (final MessagingException e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_NOTIFICACION, e);
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_CREAR_NUEVA_TAREA, e);
			navegacionMB.refrescar();
		}
	}

	public void editarTarea() {
		try {
			if (this.sessionMB.getTareaSeleccionada() != null) {
				Validaciones.validarTituloTarea(this.sessionMB
						.getTareaSeleccionada().getTitulo());
				Validaciones.validarDescripcionTarea(this.sessionMB
						.getTareaSeleccionada().getDescripcion());
				Validaciones.validarConclusionesPsicologoTarea(this.sessionMB
						.getTareaSeleccionada().getConclusionesPsicologo());
				Validaciones.validarConclusionesPacienteTarea(this.sessionMB
						.getTareaSeleccionada().getConclusionesPaciente());
				// Editamos la tarea (marcandola como no vista)
				this.sessionMB.getTareaSeleccionada().setVista(false);
				this.tareaService.actualizarTarea(this.sessionMB
						.getTareaSeleccionada());
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_EDITAR_TAREA);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_EDITAR_TAREA);
			}
			navegacionMB.refrescar();
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_EDITAR_TAREA, e);
			navegacionMB.refrescar();
		}
	}

	public void editarCamposTarea() {
		try {
			if (this.sessionMB.getTareaSeleccionada() != null) {
				// Añadimos y quitamos los campos
				this.modificarCamposDeLaTarea();
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_EDITAR_CAMPOS_TAREA);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_EDITAR_CAMPOS_TAREA);
			}
			navegacionMB.refrescar();
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_EDITAR_CAMPOS_TAREA, e);
			navegacionMB.refrescar();
		}
	}

	public void completarTarea() {
		try {
			if (this.sessionMB.getTareaSeleccionada() != null) {
				this.sessionMB.getTareaSeleccionada().setCompletada(true);
				this.tareaService.actualizarTarea(this.sessionMB
						.getTareaSeleccionada());

				Mensajes.mostrarMensajeExito(Mensajes.EXITO_COMPLETAR_TAREA);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_COMPLETAR_TAREA);
			}
			navegacionMB.refrescar();
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_COMPLETAR_TAREA, e);
			navegacionMB.refrescar();
		}
	}

	public void reabrirTarea() {
		try {
			if (this.sessionMB.getTareaSeleccionada() != null) {
				this.sessionMB.getTareaSeleccionada().setCompletada(false);
				this.tareaService.actualizarTarea(this.sessionMB
						.getTareaSeleccionada());
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_REABRIR_TAREA);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_REABRIR_TAREA);
			}
			navegacionMB.refrescar();
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_REABRIR_TAREA, e);
			navegacionMB.refrescar();
		}
	}

	public void consultarTareaPaciente() {
		try {
			if (this.sessionMB.getTareaSeleccionada() != null) {
				/*
				 * Marcamos la tarea como vista (si no lo estaba)
				 */
				if (!this.sessionMB.getTareaSeleccionada().getVista()) {
					this.sessionMB.getTareaSeleccionada().setVista(true);
					this.tareaService.actualizarTarea(this.sessionMB
							.getTareaSeleccionada());
				}
				navegacionMB.toTareaPaciente();
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_CONSULTAR_TAREA, e);
			navegacionMB.refrescar();
		}
	}

	public void anadirCampoPsicologo(final Tarea t) throws Exception {
		// Obtenemos el campo (si no existe en BD, lo crea)
		final Campo campoPsicologo = this.campoService.obtenerCampoPsicologo();
		// Añadimos el tareaCampo
		this.tareaCampoService.comprobarYAnadirTareaCampo(t, campoPsicologo);
	}

	public void anadirCamposALaTarea(final Tarea t) throws Exception {
		// Pasamos la lista de String a lista de Campo
		final List<Campo> camposDentro = new ArrayList<Campo>();
		for (final String nombreCampo : this.getCamposEnNuevaTarea()
				.getTarget()) {
			camposDentro.add(this.campoService
					.buscarCampoPorNombre(nombreCampo));
		}
		// Añadimos los tareas_campos
		for (final Campo campoDentro : camposDentro) {
			this.tareaCampoService.comprobarYAnadirTareaCampo(t, campoDentro);
		}
	}

	public void modificarCamposDeLaTarea() throws Exception {
		// Pasamos la lista de String a lista de Campo
		final List<Campo> camposFuera = new ArrayList<Campo>();
		for (final String nombreCampo : this.getCamposEnTarea().getSource()) {
			camposFuera
					.add(this.campoService.buscarCampoPorNombre(nombreCampo));
		}
		// Pasamos la lista de String a lista de Campo
		final List<Campo> camposDentro = new ArrayList<Campo>();
		for (final String nombreCampo : this.getCamposEnTarea().getTarget()) {
			camposDentro.add(this.campoService
					.buscarCampoPorNombre(nombreCampo));
		}
		// Eliminamos los tareas_campos que deban estar fuera
		for (final Campo campoFuera : camposFuera) {
			this.tareaCampoService.comprobarYBorrarTareaCampo(
					this.sessionMB.getTareaSeleccionada(), campoFuera);
		}
		// Añadimos los tareas_campos que haya que añadir
		for (final Campo campoDentro : camposDentro) {
			this.tareaCampoService.comprobarYAnadirTareaCampo(
					this.sessionMB.getTareaSeleccionada(), campoDentro);
		}
	}

	public DualListModel<String> cargarCamposEnNuevaTarea() {
		final List<String> camposEnTarea = new ArrayList<String>();
		final List<String> camposFuera = new ArrayList<String>();
		try {
			List<Campo> campos = this.campoService.obtenerCampos();
			for (final Campo c : campos) {
				camposFuera.add(c.getNombre());
			}
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_CARGAR_CAMPOS_EN_NUEVA_TAREA, e);
		}
		return new DualListModel<String>(camposFuera, camposEnTarea);
	}

	public DualListModel<String> cargarCamposEnTarea() {
		final List<String> camposEnTarea = new ArrayList<String>();
		final List<String> camposFuera = new ArrayList<String>();
		try {
			for (final Campo c : this.tareaCampoService
					.obtenerCamposPorTarea(this.sessionMB
							.getTareaSeleccionada())) {
				camposEnTarea.add(c.getNombre());
			}
			List<Campo> campos = this.campoService.obtenerCampos();
			for (final Campo c : campos) {
				camposFuera.add(c.getNombre());
			}
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_CARGAR_CAMPOS_EN_TAREA,
					e);
		}
		camposFuera.removeAll(camposEnTarea);
		return new DualListModel<String>(camposFuera, camposEnTarea);
	}

	public Boolean fijarEnLista(final String nombreCampo) {
		Boolean res = false;
		try {
			final Campo campo = this.campoService
					.buscarCampoPorNombre(nombreCampo);
			final Tarea tarea = this.sessionMB.getTareaSeleccionada();
			final List<Comentario> lista = this.comentarioService
					.obtenerComentariosPacientePorTareaYCampo(tarea, campo);
			for (int i = 0; i < lista.size() && !res; i++) {
				final Comentario c = lista.get(i);
				if (!c.getComentario().equals(Constantes.GUION)) {
					res = true;
				}
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_FIJAR_EN_LISTA, e);
		}
		return res;
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

	public IComentarioService getComentarioService() {
		return this.comentarioService;
	}

	public void setComentarioService(final IComentarioService comentarioService) {
		this.comentarioService = comentarioService;
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

	public IExpedienteService getExpedienteService() {
		return this.expedienteService;
	}

	public void setExpedienteService(final IExpedienteService expedienteService) {
		this.expedienteService = expedienteService;
	}

	public IExpedientePsicologoService getExpedientePsicologoService() {
		return this.expedientePsicologoService;
	}

	public void setExpedientePsicologoService(
			final IExpedientePsicologoService expedientePsicologoService) {
		this.expedientePsicologoService = expedientePsicologoService;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getImportante() {
		return this.importante;
	}

	public void setImportante(final Boolean importante) {
		this.importante = importante;
	}

	public DualListModel<String> getCamposEnNuevaTarea() {
		if (this.camposEnNuevaTarea == null) {
			this.camposEnNuevaTarea = this.cargarCamposEnNuevaTarea();
		}
		return this.camposEnNuevaTarea;
	}

	public void setCamposEnNuevaTarea(
			final DualListModel<String> camposEnNuevaTarea) {
		this.camposEnNuevaTarea = camposEnNuevaTarea;
	}

	public DualListModel<String> getCamposEnTarea() {
		if (this.camposEnTarea == null) {
			this.camposEnTarea = this.cargarCamposEnTarea();
		}
		return this.camposEnTarea;
	}

	public void setCamposEnTarea(final DualListModel<String> camposEnTarea) {
		this.camposEnTarea = camposEnTarea;
	}
}