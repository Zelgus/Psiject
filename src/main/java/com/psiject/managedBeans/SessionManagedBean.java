package com.psiject.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.psiject.entidades.Campo;
import com.psiject.entidades.Comentario;
import com.psiject.entidades.Expediente;
import com.psiject.entidades.Tarea;
import com.psiject.entidades.Usuario;
import com.psiject.utilidades.Mensajes;

@ManagedBean(name = "sessionMB")
@SessionScoped
public class SessionManagedBean implements Serializable {

	private static final long serialVersionUID = 5988642823476837656L;
	private Usuario usuarioEnSesion = null;
	private Expediente expedienteSeleccionado = null;
	private Tarea tareaSeleccionada = null;
	private Campo campoSeleccionado = null;
	private Comentario comentarioSeleccionado = null;
	private Map<String, String> comentariosPorCampo = new HashMap<String, String>();
	private String sugerencia;

	public void cerrarSesion() {
		this.usuarioEnSesion = null;
		this.expedienteSeleccionado = null;
		this.tareaSeleccionada = null;
		this.campoSeleccionado = null;
		this.comentarioSeleccionado = null;
		this.comentariosPorCampo.clear();
		this.sugerencia = null;

		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/psiject/comun/login.xhtml");
		} catch (final IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void timeOut() {
		this.cerrarSesion();
	}

	/*
	 * Métodos para controlar atributos de sesión
	 */
	public void configurarCuentaLoad() {
		if (this.usuarioEnSesion != null) {
			this.tareaSeleccionada = null;
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void pacienteLoad() {
		if (this.usuarioEnSesion != null && !this.usuarioEnSesion.getPermiso()
				&& this.expedienteSeleccionado != null) {
			this.tareaSeleccionada = null;
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void tareaPacienteLoad() {
		if (this.usuarioEnSesion != null && !this.usuarioEnSesion.getPermiso()
				&& this.expedienteSeleccionado != null
				&& this.tareaSeleccionada != null) {
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void misComentariosPacienteLoad() {
		if (this.usuarioEnSesion != null && !this.usuarioEnSesion.getPermiso()
				&& this.expedienteSeleccionado != null
				&& this.tareaSeleccionada != null) {
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void altaExpedienteLoad() {
		if (this.usuarioEnSesion != null && this.usuarioEnSesion.getPermiso()) {
			this.expedienteSeleccionado = null;
			this.tareaSeleccionada = null;
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void editarCamposLoad() {
		if (this.usuarioEnSesion != null && this.usuarioEnSesion.getPermiso()) {
			this.expedienteSeleccionado = null;
			this.tareaSeleccionada = null;
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void expedienteLoad() {
		if (this.usuarioEnSesion != null && this.usuarioEnSesion.getPermiso()
				&& this.expedienteSeleccionado != null) {
			this.tareaSeleccionada = null;
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void misComentariosPsicologoLoad() {
		if (this.usuarioEnSesion != null && this.usuarioEnSesion.getPermiso()
				&& this.expedienteSeleccionado != null
				&& this.tareaSeleccionada != null) {
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void nuevaTareaLoad() {
		if (this.usuarioEnSesion != null && this.usuarioEnSesion.getPermiso()
				&& this.expedienteSeleccionado != null) {
			this.tareaSeleccionada = null;
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void psicologoLoad() {
		if (this.usuarioEnSesion != null && this.usuarioEnSesion.getPermiso()) {
			this.expedienteSeleccionado = null;
			this.tareaSeleccionada = null;
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	public void tareaPsicologoLoad() {
		if (this.usuarioEnSesion != null && this.usuarioEnSesion.getPermiso()
				&& this.expedienteSeleccionado != null
				&& this.tareaSeleccionada != null) {
			this.campoSeleccionado = null;
			this.comentarioSeleccionado = null;
			this.comentariosPorCampo.clear();
			this.sugerencia = null;
		} else {
			this.cerrarSesion();
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */

	public Usuario getUsuarioEnSesion() {
		return this.usuarioEnSesion;
	}

	public void setUsuarioEnSesion(final Usuario usuarioEnSesion) {
		this.usuarioEnSesion = usuarioEnSesion;
	}

	public Expediente getExpedienteSeleccionado() {
		return this.expedienteSeleccionado;
	}

	public void setExpedienteSeleccionado(
			final Expediente expedienteSeleccionado) {
		this.expedienteSeleccionado = expedienteSeleccionado;
	}

	public Tarea getTareaSeleccionada() {
		return this.tareaSeleccionada;
	}

	public void setTareaSeleccionada(final Tarea tareaSeleccionada) {
		this.tareaSeleccionada = tareaSeleccionada;
	}

	public Campo getCampoSeleccionado() {
		return this.campoSeleccionado;
	}

	public void setCampoSeleccionado(final Campo campoSeleccionado) {
		this.campoSeleccionado = campoSeleccionado;
	}

	public Comentario getComentarioSeleccionado() {
		return this.comentarioSeleccionado;
	}

	public void setComentarioSeleccionado(
			final Comentario comentarioSeleccionado) {
		this.comentarioSeleccionado = comentarioSeleccionado;
	}

	public Map<String, String> getComentariosPorCampo() {
		return this.comentariosPorCampo;
	}

	public void setComentariosPorCampo(
			final Map<String, String> comentariosPorCampo) {
		this.comentariosPorCampo = comentariosPorCampo;
	}

	public String getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}
}
