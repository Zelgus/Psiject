package com.psiject.managedBeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;

import com.psiject.entidades.Expediente;
import com.psiject.entidades.Usuario;
import com.psiject.services.interfaces.IExpedienteService;
import com.psiject.services.interfaces.IUsuarioService;
import com.psiject.utilidades.Mensajes;
import com.psiject.utilidades.NotificacionUtil;
import com.psiject.utilidades.Utilidades;
import com.psiject.utilidades.Validaciones;

@ManagedBean(name = "loginMB")
@RequestScoped
public class LoginManagedBean implements Serializable {

	private static final long serialVersionUID = 2778990001012123721L;

	@ManagedProperty("#{sessionMB}")
	private SessionManagedBean sessionMB;

	@ManagedProperty("#{navegacionMB}")
	private NavegacionManagedBean navegacionMB;

	@ManagedProperty(value = "#{UsuarioService}")
	private IUsuarioService usuarioService;

	@ManagedProperty(value = "#{ExpedienteService}")
	private IExpedienteService expedienteService;

	private String usuario;
	private String contrasena;

	private String recuperar;

	private String recuUsuario;
	private String codigoValidacion;
	private String recuNuevaContrasena;
	private String recuRepetirContrasena;

	public LoginManagedBean() {
		super();
	}

	public void iniciarSesion() {
		try {
			final Usuario usuario = this.usuarioService.buscarUsuarioLogin(this
					.getUsuario());
			if (usuario == null) {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_INICIAR_SESION_USUARIO_CONTRASENA);
				this.navegacionMB.toLogin();
			} else {
				final String contrasenaEncriptada = Utilidades.encriptar(this
						.getContrasena());
				if (!contrasenaEncriptada.equals(usuario.getContrasena())) {
					Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_INICIAR_SESION_USUARIO_CONTRASENA);
					this.navegacionMB.toLogin();
				} else {
					this.sessionMB.setUsuarioEnSesion(usuario);
					if (usuario.getPermiso()) {
						this.navegacionMB.toPsicologo();
					} else {
						final List<Expediente> expedientes = this.expedienteService
								.buscarExpedientesPorUsuario(this.sessionMB
										.getUsuarioEnSesion());
						if (expedientes != null && !expedientes.isEmpty()) {
							this.sessionMB
									.setExpedienteSeleccionado(expedientes
											.get(0));
							this.navegacionMB.toPaciente();
						} else {
							Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_INICIAR_SESION);
							this.navegacionMB.toLogin();
						}
					}
				}
			}
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_INICIAR_SESION, e);
			this.navegacionMB.toLogin();
		}
	}

	public void recuperarContrasena() {
		try {
			final Usuario usu = this.usuarioService
					.buscarUsuarioLogin(this.recuperar);
			if (usu != null) {
				final String codigo = this.generarCodigo(usu);
				NotificacionUtil.notificacionRecuperarContraseña(
						usu.getCorreo(), usu.getUsuario(), codigo);
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_MENSAJE_ENVIADO);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_USUARIO_NO_ENCONTRADO);
			}
			navegacionMB.refrescar();
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeError(e.getMessage(), e);
			navegacionMB.refrescar();
		} catch (final MessagingException e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_NOTIFICACION, e);
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_USUARIO_NO_ENCONTRADO,
					e);
			navegacionMB.refrescar();
		}
	}

	private String generarCodigo(final Usuario u) throws Exception {

		final String nombre = u.getUsuario();
		final String correo = u.getCorreo();
		final Date hoy = Calendar.getInstance().getTime();
		final String fecha = Utilidades.formatearFecha(hoy);

		final String texto = nombre + correo + fecha;

		final String res = Utilidades.encriptar(texto).substring(32, 39);
		u.setVerificacion(res);
		u.setEnvio(hoy);
		this.usuarioService.actualizarUsuario(u);
		return res;
	}

	public void restablecerContrasena() {
		try {
			if (this.recuUsuario != null) {
				Validaciones.validarContraseña(this.recuNuevaContrasena);
				Validaciones.validarCodigoVerificacion(this.codigoValidacion);
				final Usuario usu = this.usuarioService
						.buscarUsuarioLogin(this.recuUsuario);
				if (usu != null) {
					if (Utilidades.diferenciaFechaEnHoras(usu.getEnvio(),
							Calendar.getInstance().getTime()) > 1L) {
						usu.setEnvio(null);
						usu.setVerificacion(null);
						this.usuarioService.actualizarUsuario(usu);
						Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_TIEMPO_EXPIRADO);
					} else if (usu.getVerificacion().equals(
							this.codigoValidacion.replace(" ", ""))) {
						if (!this.recuNuevaContrasena
								.equals(this.recuRepetirContrasena)) {
							Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_ANADIR_USUARIO_CONTRASENA);
						} else {
							usu.setContrasena(Utilidades
									.encriptar(this.recuNuevaContrasena));
							usu.setEnvio(null);
							usu.setVerificacion(null);
							this.usuarioService.actualizarUsuario(usu);
							Mensajes.mostrarMensajeExito(Mensajes.EXITO_EDITAR_USUARIO);
						}
					} else {
						Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_CODIGO_NO_VALIDO);
					}
				} else {
					Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_USUARIO_NO_ENCONTRADO);
				}
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_USUARIO_NO_ENCONTRADO);
			}
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_RECUPERAR_CONTRASEÑA, e);
		}
		this.navegacionMB.toLogin();
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

	public IExpedienteService getExpedienteService() {
		return this.expedienteService;
	}

	public void setExpedienteService(final IExpedienteService expedienteService) {
		this.expedienteService = expedienteService;
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

	public String getRecuperar() {
		return this.recuperar;
	}

	public void setRecuperar(final String recuperar) {
		this.recuperar = recuperar;
	}

	public String getCodigoValidacion() {
		return this.codigoValidacion;
	}

	public void setCodigoValidacion(final String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public String getRecuUsuario() {
		return this.recuUsuario;
	}

	public void setRecuUsuario(final String recuUsuario) {
		this.recuUsuario = recuUsuario;
	}

	public String getRecuRepetirContrasena() {
		return this.recuRepetirContrasena;
	}

	public void setRecuRepetirContrasena(final String recuRepetirContrasena) {
		this.recuRepetirContrasena = recuRepetirContrasena;
	}

	public String getRecuNuevaContrasena() {
		return this.recuNuevaContrasena;
	}

	public void setRecuNuevaContrasena(final String recuNuevaContrasena) {
		this.recuNuevaContrasena = recuNuevaContrasena;
	}
}
