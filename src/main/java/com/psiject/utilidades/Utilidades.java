package com.psiject.utilidades;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import jonelo.jacksum.JacksumAPI;
import jonelo.jacksum.algorithm.AbstractChecksum;

import com.sun.faces.component.visit.FullVisitContext;

public class Utilidades {

	public static String encriptar(final String password)
			throws NoSuchAlgorithmException {
		AbstractChecksum checksum = null;
		checksum = JacksumAPI.getChecksumInstance("whirlpool");
		checksum.update(password.getBytes());
		System.out.println(checksum.getFormattedValue());

		return checksum.getFormattedValue();
	}

	public static String getURL() {
		final HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		final String contextURL = request.getRequestURL().toString()
				.replace(request.getRequestURI().substring(0), "")
				+ request.getContextPath();
		return contextURL;
	}

	public static String formatearFecha(final Date date) {
		final String newstring = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
				.format(date);
		return newstring;
	}

	public static Long diferenciaFechaEnHoras(final Date ini, final Date fin) {
		final Calendar cal1 = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();

		// Establecer las fechas
		cal1.setTime(ini);
		cal2.setTime(fin);

		// conseguir la representacion de la fecha en milisegundos
		final long milis1 = cal1.getTimeInMillis();
		final long milis2 = cal2.getTimeInMillis();
		final long diff = milis2 - milis1;
		// calcular la diferencia en horas
		return diff / (60 * 60 * 1000);
	}

	public static UIComponent findComponent(FacesContext context,
			final String id) {

		UIViewRoot root = context.getViewRoot();
		final UIComponent[] found = new UIComponent[1];

		root.visitTree(new FullVisitContext(context), new VisitCallback() {
			@Override
			public VisitResult visit(VisitContext context, UIComponent component) {
				if (component.getId().equals(id)) {
					found[0] = component;
					return VisitResult.COMPLETE;
				}
				return VisitResult.ACCEPT;
			}
		});

		return found[0];

	}
}