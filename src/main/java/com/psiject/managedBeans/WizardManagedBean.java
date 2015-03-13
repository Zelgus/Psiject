package com.psiject.managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

@ManagedBean(name = "wizardMB")
@ViewScoped
public class WizardManagedBean implements Serializable {

	private static final long serialVersionUID = 7929650150768811254L;

	@ManagedProperty("#{sessionMB}")
	private SessionManagedBean sessionMB;

	private String comentario;

	public WizardManagedBean() {
		super();
	}

	public String onFlowProcess(FlowEvent event) {
		/*
		 * Rellenamos el mapa de comentarios con los pares
		 * "idCampo - comentario"
		 */
		String idCampoOld = event.getOldStep().replace("campo", "");
		String idCampoNew = event.getNewStep().replace("campo", "");

		this.sessionMB.getComentariosPorCampo().put(idCampoOld, comentario);

		if (this.sessionMB.getComentariosPorCampo().containsKey(idCampoNew)) {
			comentario = this.sessionMB.getComentariosPorCampo()
					.get(idCampoNew);
		} else {
			comentario = "";
		}

		return event.getNewStep();
	}

	public void fillLastComent(String idCampo) {
		this.sessionMB.getComentariosPorCampo().put(idCampo, comentario);
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

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(final String comentario) {
		this.comentario = comentario;
	}
}
