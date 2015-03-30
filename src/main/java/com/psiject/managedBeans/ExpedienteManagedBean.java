package com.psiject.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.DataFormatException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.DualListModel;

import com.psiject.entidades.Expediente;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.Usuario;
import com.psiject.services.interfaces.IExpedientePsicologoService;
import com.psiject.services.interfaces.IExpedienteService;
import com.psiject.services.interfaces.ITareaService;
import com.psiject.services.interfaces.IUsuarioService;
import com.psiject.utilidades.Constantes;
import com.psiject.utilidades.Mensajes;
import com.psiject.utilidades.NotificacionUtil;
import com.psiject.utilidades.Validaciones;

@ManagedBean(name = "expedienteMB")
@RequestScoped
public class ExpedienteManagedBean implements Serializable {

	private static final long serialVersionUID = 1054170073497812218L;

	@ManagedProperty("#{sessionMB}")
	private SessionManagedBean sessionMB;

	@ManagedProperty("#{navegacionMB}")
	private NavegacionManagedBean navegacionMB;

	@ManagedProperty(value = "#{UsuarioService}")
	private IUsuarioService usuarioService;

	@ManagedProperty(value = "#{ExpedienteService}")
	private IExpedienteService expedienteService;

	@ManagedProperty(value = "#{ExpedientePsicologoService}")
	private IExpedientePsicologoService expedientePsicologoService;

	@ManagedProperty(value = "#{TareaService}")
	private ITareaService tareaService;

	private String nombre;
	private String apellidos;
	private String usuario;
	private String contrasena;
	private String correo;

	private DualListModel<String> psicologosEnExpediente;
	private String nuevoPsicologoCreador;
	private List<Tarea> tareasPendientesFiltradas;

	public ExpedienteManagedBean() {
		super();
	}

	public ExpedienteManagedBean(final String nombre, final String apellidos,
			final String usuario, final String contrasena, final String correo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.correo = correo;
		this.psicologosEnExpediente = this.cargarPsicologosEnExpediente();
	}

	public List<Expediente> getExpedientesCreadosList() {
		final List<Expediente> res = new ArrayList<Expediente>();
		try {
			final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
			for (final Expediente exp : this.expedienteService
					.obtenerExpedientesCreadosPorUsuario(usuarioEnSesion)) {
				if (!exp.getCerrado()) {
					res.add(exp);
				}
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_EXPEDIENTES_CREADOS_LIST, e);
		}
		return res;
	}

	public List<Expediente> getExpedientesAccesiblesList() {
		final List<Expediente> res = new ArrayList<Expediente>();
		try {
			final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
			for (final Expediente exp : this.expedientePsicologoService
					.obtenerExpedientesAccesiblesPorPsicologo(usuarioEnSesion)) {
				if (!exp.getUsuarioCreador().equals(usuarioEnSesion)
						&& !exp.getCerrado()) {
					res.add(exp);
				}
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_EXPEDIENTES_ACCESIBLES_LIST, e);
		}
		return res;
	}

	public List<Expediente> getExpedientesCerradosList() {
		final List<Expediente> res = new ArrayList<Expediente>();
		try {
			final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
			for (final Expediente exp : this.expedientePsicologoService
					.obtenerExpedientesAccesiblesPorPsicologo(usuarioEnSesion)) {
				if (exp.getCerrado()) {
					res.add(exp);
				}
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_EXPEDIENTES_CERRADOS_LIST, e);
		}

		return res;
	}

	public List<String> getPsicologos() {
		final List<String> psicologos = new ArrayList<String>();
		try {
			for (final Usuario u : this.usuarioService.obtenerPsicologos()) {
				psicologos.add(u.getUsuario());
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_GET_PSICOLOGOS, e);
		}
		return psicologos;
	}

	public String getPsicologosConAcceso() {
		String res = Constantes.CADENA_VACIA;
		try {
			for (final Usuario u : this.expedientePsicologoService
					.obtenerPsicologosConAccesoPorExpediente(this.sessionMB
							.getExpedienteSeleccionado())) {
				if (!u.equals(this.sessionMB.getExpedienteSeleccionado()
						.getUsuarioCreador())) {
					res += u.getUsuario() + Constantes.COMA_ESPACIO;
				}
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_GET_PSICOLOGOS_CON_ACCESO, e);
		}
		if (!res.isEmpty()) {
			res = res.substring(0, res.length() - 2);
			res += Constantes.PUNTO;
		} else {
			res = Constantes.NINGUN_PSICOLOGO_CON_ACCESO;
		}
		return res;
	}

	public String getNombreYApellidos() {
		String res = Constantes.CADENA_VACIA;
		if (!this.sessionMB.getUsuarioEnSesion().getPermiso()) {
			res = this.sessionMB.getExpedienteSeleccionado().getNombre()
					+ Constantes.ESPACIO
					+ this.sessionMB.getExpedienteSeleccionado().getApellidos();
		}
		return res;
	}

	public void cerrarExpediente() {
		try {
			if (this.sessionMB.getExpedienteSeleccionado() != null) {
				this.sessionMB.getExpedienteSeleccionado().setCerrado(true);
				this.expedienteService.actualizarExpediente(this.sessionMB
						.getExpedienteSeleccionado());
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_CERRAR_EXPEDIENTE);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_CERRAR_EXPEDIENTE);
			}
			navegacionMB.refrescar();
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_CERRAR_EXPEDIENTE, e);
			navegacionMB.refrescar();
		}
	}

	public void reabrirExpediente() {
		try {
			if (this.sessionMB.getExpedienteSeleccionado() != null) {
				this.sessionMB.getExpedienteSeleccionado().setCerrado(false);
				this.expedienteService.actualizarExpediente(this.sessionMB
						.getExpedienteSeleccionado());
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_REABRIR_EXPEDIENTE);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_REABRIR_EXPEDIENTE);
			}
			navegacionMB.refrescar();
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_REABRIR_EXPEDIENTE, e);
			navegacionMB.refrescar();
		}
	}

	public void transferir() {
		try {
			if (this.getNuevoPsicologoCreador() != null
					&& !this.getNuevoPsicologoCreador().isEmpty()) {
				final Usuario nuevo = this.usuarioService
						.buscarUsuarioPorNombreUsuario(this
								.getNuevoPsicologoCreador());
				this.sessionMB.getExpedienteSeleccionado().setUsuarioCreador(
						nuevo);
				this.expedientePsicologoService
						.comprobarYAnadirExpedientePsicologo(
								this.sessionMB.getExpedienteSeleccionado(),
								nuevo);
				this.expedienteService.actualizarExpediente(this.sessionMB
						.getExpedienteSeleccionado());
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_TRANSFERIR_EXPEDIENTE);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_TRANSFERIR_EXPEDIENTE);
			}
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_TRANSFERIR_EXPEDIENTE,
					e);
			navegacionMB.refrescar();
		}
	}

	public void abandonarExpediente() {
		try {
			if (this.sessionMB.getExpedienteSeleccionado() != null) {
				// Pasamos las tareas creadas al creador del expediente
				final List<Tarea> tareasCreadas = this.tareaService
						.obtenerTareasPorExpedienteYCreador(
								this.sessionMB.getExpedienteSeleccionado(),
								this.sessionMB.getUsuarioEnSesion());
				for (final Tarea t : tareasCreadas) {
					t.setUsuarioCreador(this.sessionMB
							.getExpedienteSeleccionado().getUsuarioCreador());
					this.tareaService.actualizarTarea(t);
				}

				// Abandonamos el expediente
				this.expedientePsicologoService
						.comprobarYBorrarExpedientePsicologo(
								this.sessionMB.getExpedienteSeleccionado(),
								this.sessionMB.getUsuarioEnSesion());
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_ABANDONAR_EXPEDIENTE);
				navegacionMB.toPsicologo();
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_ABANDONAR_EXPEDIENTE);
				navegacionMB.refrescar();
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_ABANDONAR_EXPEDIENTE, e);
			navegacionMB.refrescar();
		}
	}

	public void altaExpediente() {
		try {
			Validaciones.validarNombreUsuario(this.usuario);
			Validaciones.validarCorreo(this.correo);
			Validaciones.validarContraseña(this.contrasena);
			Validaciones.validarNombreExpediente(this.nombre);
			Validaciones.validarApellidosExpediente(this.apellidos);

			this.usuarioService.anadirUsuario(this.usuario, this.contrasena,
					this.correo, false);
			/*
			 * Obtenemos el usuario almacenado
			 */
			final Usuario usuarioAnadido = this.usuarioService
					.buscarUsuarioPorNombreUsuario(this.usuario);
			/*
			 * Obtenemos el usuario en sesión
			 */
			final Usuario usuarioEnSesion = this.sessionMB.getUsuarioEnSesion();
			/*
			 * Almacenamos el expediente
			 */
			final Expediente e = this.expedienteService.anadirExpediente(
					this.nombre, this.apellidos, false, usuarioAnadido,
					usuarioEnSesion);
			/*
			 * Añadimos al creador a Expediente_Psicologo
			 */
			this.expedientePsicologoService.anadirExpedientePsicologo(e,
					usuarioEnSesion);
			this.sessionMB.setExpedienteSeleccionado(e);
			/*
			 * Notifcamos al paciente
			 */
			NotificacionUtil.notificacionNuevoRegistro(this.correo,
					this.nombre, this.sessionMB.getUsuarioEnSesion()
							.getUsuario(), this.usuario, this.contrasena);
			Mensajes.mostrarMensajeExito(Mensajes.EXITO_ALTA_EXPEDIENTE);
			navegacionMB.toExpediente();
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (final MessagingException e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_NOTIFICACION, e);
			navegacionMB.refrescar();
		} catch (final ConstraintViolationException e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_ALTA_EXPEDIENTE_EXISTE,
					e);
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_ALTA_EXPEDIENTE, e);
			navegacionMB.refrescar();
		}
	}

	public void editarExpediente() {
		try {
			if (this.sessionMB.getExpedienteSeleccionado() != null) {
				Validaciones.validarNombreExpediente(this.sessionMB
						.getExpedienteSeleccionado().getNombre());
				Validaciones.validarApellidosExpediente(this.sessionMB
						.getExpedienteSeleccionado().getApellidos());
				// Editamos el expediente
				this.expedienteService.actualizarExpediente(this.sessionMB
						.getExpedienteSeleccionado());
				// Añadimos y quitamos los psicologos
				this.modificarPsicologosDelExpediente();
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_EDITAR_EXPEDIENTE);
				navegacionMB.toExpediente();
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_EDITAR_EXPEDIENTE);
				navegacionMB.refrescar();
			}
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_EDITAR_EXPEDIENTE, e);
			navegacionMB.refrescar();
		}
	}

	public void modificarPsicologosDelExpediente() throws Exception {
		// Pasamos la lista de String a lista de Usuario
		final List<Usuario> psicologosFuera = new ArrayList<Usuario>();
		for (final String nombreUsuario : this.getPsicologosEnExpediente()
				.getSource()) {
			psicologosFuera.add(this.usuarioService
					.buscarUsuarioPorNombreUsuario(nombreUsuario));
		}
		// Pasamos la lista de String a lista de Usuario
		final List<Usuario> psicologosDentro = new ArrayList<Usuario>();
		for (final String nombreUsuario : this.getPsicologosEnExpediente()
				.getTarget()) {
			psicologosDentro.add(this.usuarioService
					.buscarUsuarioPorNombreUsuario(nombreUsuario));
		}
		// Eliminamos los expedientes_psicologos que deban estar fuera
		for (final Usuario psicologoFuera : psicologosFuera) {
			this.expedientePsicologoService
					.comprobarYBorrarExpedientePsicologo(
							this.sessionMB.getExpedienteSeleccionado(),
							psicologoFuera);
		}
		// Añadimos los expedientes_psicologos que haya que añadir
		for (final Usuario psicologoDentro : psicologosDentro) {
			this.expedientePsicologoService
					.comprobarYAnadirExpedientePsicologo(
							this.sessionMB.getExpedienteSeleccionado(),
							psicologoDentro);
		}
	}

	public DualListModel<String> cargarPsicologosEnExpediente() {
		final List<String> psicologosEnExpediente = new ArrayList<String>();
		final List<String> psicologosFuera = new ArrayList<String>();
		try {
			for (final Usuario u : this.expedientePsicologoService
					.obtenerPsicologosConAccesoPorExpediente(this.sessionMB
							.getExpedienteSeleccionado())) {
				psicologosEnExpediente.add(u.getUsuario());
			}
			final List<Usuario> psicologos = this.usuarioService
					.obtenerPsicologos();
			for (final Usuario u : psicologos) {
				psicologosFuera.add(u.getUsuario());
			}
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(
					Mensajes.ERROR_CARGAR_PSICOLOGOS_EN_EXPEDIENTE, e);
		}
		psicologosFuera.removeAll(psicologosEnExpediente);
		return new DualListModel<String>(psicologosFuera,
				psicologosEnExpediente);
	}

	public Boolean fijarEnLista(final String nombrePsicologo) {
		Boolean res = false;
		if (nombrePsicologo.equals(this.sessionMB.getUsuarioEnSesion()
				.getUsuario())) {
			res = true;
		}
		return res;
	}

	public void generarUsuario() {
		if (this.nombre.length() < 3 || this.apellidos.length() < 3) {
			Mensajes.mostrarMensajeAlerta("Debe escribir un nombre y un apellido valido");
		} else {
			Character caracteres[] = { '0', '1', '2', '3', '4', '5', '6', '7',
					'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
					'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
					'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
					'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
					'u', 'v', 'w', 'x', 'y', 'z' };
			String pass = "";
			for (int i = 0; i < 6; i++) {
				int caracter = new Random().nextInt(62);
				pass += caracteres[caracter];
			}

			this.usuario = (this.nombre.substring(0, 3).concat(this.apellidos
					.substring(0, 3)));
			this.contrasena = (pass);
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

	public String getNuevoPsicologoCreador() {
		return this.nuevoPsicologoCreador;
	}

	public void setNuevoPsicologoCreador(final String nuevoPsicologoCreador) {
		this.nuevoPsicologoCreador = nuevoPsicologoCreador;
	}

	public IUsuarioService getUsuarioService() {
		return this.usuarioService;
	}

	public void setUsuarioService(final IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
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

	public ITareaService getTareaService() {
		return this.tareaService;
	}

	public void setTareaService(final ITareaService tareaService) {
		this.tareaService = tareaService;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(final String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(final String correo) {
		this.correo = correo;
	}

	public DualListModel<String> getPsicologosEnExpediente() {
		if (this.psicologosEnExpediente == null) {
			this.psicologosEnExpediente = this.cargarPsicologosEnExpediente();
		}
		return this.psicologosEnExpediente;
	}

	public void setPsicologosEnExpediente(
			final DualListModel<String> psicologosEnExpediente) {
		this.psicologosEnExpediente = psicologosEnExpediente;
	}

	public List<Tarea> getTareasFiltradas() {
		return this.tareasPendientesFiltradas;
	}

	public void setTareasFiltradas(final List<Tarea> tareasFiltradas) {
		this.tareasPendientesFiltradas = tareasFiltradas;
	}
}
