package com.psiject.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.hibernate.AssertionFailure;

import com.psiject.entidades.Campo;
import com.psiject.services.interfaces.ICampoService;
import com.psiject.services.interfaces.ITareaCampoService;
import com.psiject.utilidades.Mensajes;
import com.psiject.utilidades.Validaciones;

@ManagedBean(name = "campoMB")
@RequestScoped
public class CampoManagedBean implements Serializable {

	private static final long serialVersionUID = 6199044579421370267L;

	@ManagedProperty("#{sessionMB}")
	private SessionManagedBean sessionMB;

	@ManagedProperty("#{navegacionMB}")
	private NavegacionManagedBean navegacionMB;

	@ManagedProperty(value = "#{CampoService}")
	private ICampoService campoService;

	@ManagedProperty(value = "#{TareaCampoService}")
	private ITareaCampoService tareaCampoService;

	private String nombre;
	private String descripcion;

	public CampoManagedBean() {
		super();
	}

	public List<Campo> getCamposList() {
		List<Campo> campos = new ArrayList<Campo>();
		try {
			campos = this.campoService.obtenerCampos();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_GET_CAMPOS_LIST, e);
		}
		return campos;
	}

	public void anadirCampo() {
		try {
			Validaciones.validarNombreCampo(this.getNombre());
			Validaciones.validarDescripcionCampo(this.getDescripcion());
			this.campoService.anadirCampo(this.getNombre(),
					this.getDescripcion());
			Mensajes.mostrarMensajeExito(Mensajes.EXITO_ANADIR_CAMPO);
			navegacionMB.refrescar();
		} catch (final DataFormatException e) {
			Mensajes.mostrarMensajeAlerta(e.getMessage());
			navegacionMB.refrescar();
		} catch (final AssertionFailure e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_ANADIR_CAMPO_EXISTE, e);
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_ANADIR_CAMPO, e);
			navegacionMB.refrescar();
		}
	}

	public void borrarCampo() {
		try {
			Boolean aux = this.campoService.borrarCampo(this.sessionMB
					.getCampoSeleccionado());
			if (aux) {
				Mensajes.mostrarMensajeExito(Mensajes.EXITO_BORRAR_CAMPO);
			} else {
				Mensajes.mostrarMensajeAlerta(Mensajes.ALERTA_BORRAR_CAMPO);
			}
			navegacionMB.refrescar();
		} catch (final Exception e) {
			Mensajes.mostrarMensajeError(Mensajes.ERROR_BORRAR_CAMPO, e);
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

	public ICampoService getCampoService() {
		return this.campoService;
	}

	public void setCampoService(final ICampoService campoService) {
		this.campoService = campoService;
	}

	public ITareaCampoService getTareaCampoService() {
		return this.tareaCampoService;
	}

	public void setTareaCampoService(final ITareaCampoService tareaCampoService) {
		this.tareaCampoService = tareaCampoService;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}
}
