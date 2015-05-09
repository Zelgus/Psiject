package com.psiject.managedBeans;

import java.io.Serializable;
import java.util.zip.DataFormatException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;

import org.hibernate.exception.ConstraintViolationException;

import com.psiject.services.interfaces.IUsuarioService;
import com.psiject.utilidades.Mensajes;
import com.psiject.utilidades.NotificacionUtil;
import com.psiject.utilidades.Utilidades;
import com.psiject.utilidades.Validaciones;

@ManagedBean(name = "usuarioMB")
@RequestScoped
public class UsuarioManagedBean implements Serializable {

	private static final long serialVersionUID = 4484349701800290253L;

	@ManagedProperty("#{sessionMB}")
	private SessionManagedBean sessionMB;

	@ManagedProperty("#{navegacionMB}")
	private NavegacionManagedBean navegacionMB;

	@ManagedProperty(value = "#{UsuarioService}")
	private IUsuarioService usuarioService;

	private String usuario;
	private String contrasena;
	private String nuevaContrasena;
	private String repetirContrasena;
	private String correo;

	public UsuarioManagedBean() {
		super();
	}

	public UsuarioManagedBean(final String usuario, final String contrasena,
			final String correo) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.correo = correo;
	}

	public void volverInicio() {
		if (this.sessionMB.getUsuarioEnSesion().getPermiso()) {
			navegacionMB.toPsicologo();
		} else {
			navegacionMB.toPaciente();
		}
	}

	public void anadirUsuario() {
		try {
			Validaciones.validarNombreUsuario(this.usuario);
			Validaciones.validarCorreo(this.correo);
			Validaciones.validarContraseña(this.nuevaContrasena);
			this.usuarioService.anadirUsuario(this.usuario,
					this.nuevaContrasena, this.correo, true);
			Mensajes.mostrarMensajeExito(Mensajes.EXITO_ANADIR_USUARIO);
			navegacionMB.refrescar();
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (final ConstraintViolationException e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_ANADIR_USUARIO_EXISTE,
					e);
			navegacionMB.refrescar();
		} catch (Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_ANADIR_USUARIO, e);
			navegacionMB.refrescar();
		}
	}

	public void editarCuenta() {
		try {
			Validaciones.validarNombreUsuario(this.sessionMB
					.getUsuarioEnSesion().getUsuario());
			Validaciones.validarCorreo(this.sessionMB.getUsuarioEnSesion()
					.getCorreo());
			if (this.getContrasena().isEmpty()) {
				// Actualizamos el usuario (sin variar la contrasena)
				this.usuarioService.actualizarUsuario(this.sessionMB
						.getUsuarioEnSesion());

				Mensajes.mostrarMensajeExito(Mensajes.EXITO_EDITAR_USUARIO);
				if (this.sessionMB.getUsuarioEnSesion().getPermiso()) {
					navegacionMB.toPsicologo();
				} else {
					navegacionMB.toPaciente();
				}
			} else {
				// Actualizamos el usuario (contrasena incluida)
				Validaciones.validarContraseña(this.getContrasena());
				Validaciones.validarContraseña(this.getNuevaContrasena());
				String contrasenaEncriptada = Utilidades.encriptar(this
						.getContrasena());
				if (!contrasenaEncriptada.equals(this.sessionMB
						.getUsuarioEnSesion().getContrasena())) {
					Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_EDITAR_USUARIO_NO_CONTRASENA);
					navegacionMB.refrescar();
				} else {
					if (!this.getNuevaContrasena().equals(
							this.getRepetirContrasena())) {
						Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_EDITAR_USUARIO_CONTRASENA);
						navegacionMB.refrescar();
					} else {
						// Encriptado de la nueva contraseña
						String nuevaContrasenaEncriptada = Utilidades
								.encriptar(this.getNuevaContrasena());
						// Modificamos los datos del usuario en sesion
						this.sessionMB.getUsuarioEnSesion().setContrasena(
								nuevaContrasenaEncriptada);
						// Actualizamos el usuario
						this.usuarioService.actualizarUsuario(this.sessionMB
								.getUsuarioEnSesion());

						Mensajes.mostrarMensajeExito(Mensajes.EXITO_EDITAR_USUARIO);
						if (this.sessionMB.getUsuarioEnSesion().getPermiso()) {
							navegacionMB.toPsicologo();
						} else {
							navegacionMB.toPaciente();
						}
					}
				}
			}
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (final ConstraintViolationException e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_EDITAR_USUARIO_EXISTE,
					e);
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_EDITAR_USUARIO, e);
			navegacionMB.refrescar();
		}
	}

	public void enviarSugerencia() {
		try {
			NotificacionUtil.notificacionNuevaSugerencia("joaquin_casart@hotmail.com",
					sessionMB.getSugerencia());
			NotificacionUtil.notificacionNuevaSugerencia(
					"ivanom90@hotmail.com", sessionMB.getSugerencia());
			Mensajes.mostrarMensajeExito(Mensajes.EXITO_MENSAJE_SUGERENCIA_ENVIADO);
			navegacionMB.refrescar();
		} catch (MessagingException e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_NOTIFICACION, e);
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

	public IUsuarioService getUsuarioService() {
		return this.usuarioService;
	}

	public void setUsuarioService(final IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
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

	public String getNuevaContrasena() {
		return this.nuevaContrasena;
	}

	public void setNuevaContrasena(final String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

	public String getRepetirContrasena() {
		return this.repetirContrasena;
	}

	public void setRepetirContrasena(final String repetirContrasena) {
		this.repetirContrasena = repetirContrasena;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(final String correo) {
		this.correo = correo;
	}
}
