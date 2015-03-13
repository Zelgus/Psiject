package com.psiject.managedBeans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.psiject.utilidades.Mensajes;

@ManagedBean(name = "navegacionMB")
@SessionScoped
public class NavegacionManagedBean implements Serializable {

	private static final long serialVersionUID = 8160241440668453219L;

	public NavegacionManagedBean() {
		super();
	}

	public void refrescar() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/comun/login.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toConfigurarCuenta() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/comun/configurarCuenta.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toMisComentariosPaciente() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/paciente/misComentariosPaciente.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toPaciente() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/paciente/paciente.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toTareaPaciente() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/paciente/tareaPaciente.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toPsicologo() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/psicologo/psicologo.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toExpediente() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/psicologo/expediente.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toAltaExpediente() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/psicologo/altaExpediente.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toEditarCampos() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/psicologo/editarCampos.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toEditarExpediente() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/psicologo/editarExpediente.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toMisComentariosPsicologo() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/psicologo/misComentariosPsicologo.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toNuevaTarea() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/psicologo/nuevaTarea.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}

	public void toTareaPsicologo() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			ec.redirect("/psiject/psicologo/tareaPsicologo.xhtml");
		} catch (IOException e) {
			Mensajes.mostrarLog(e);
		}
	}
}