package com.psiject.utilidades;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Mensajes {

	public Mensajes() {
		super();
	}

	public static void mostrarMensajeExito(final String mensaje) {
		Logger.getLogger(Mensajes.class).log(Level.INFO, mensaje);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensaje));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
	}

	public static void mostrarMensajeAlerta(final String mensaje) {
		Logger.getLogger(Mensajes.class).log(Level.WARN, mensaje);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, null, mensaje));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
	}

	public static void mostrarMensajeError(final String mensaje,
			final Exception e) {

		crearArchivoLog(e);

		Logger.getLogger(Mensajes.class).log(Level.ERROR, mensaje, e);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensaje));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
	}

	public static void mostrarLog(final Exception e) {
		Logger.getLogger(Mensajes.class).log(Level.ERROR, e.getMessage(), e);
	}

	private static void crearArchivoLog(Exception excep) {
		Calendar fecha = Calendar.getInstance();
		Integer dia = fecha.get(Calendar.DAY_OF_MONTH);
		Integer mes = fecha.get(Calendar.MONTH);
		Integer anyo = fecha.get(Calendar.YEAR);

		Path path = Paths.get("log" + dia + mes + anyo + ".txt");
		try {

			if (!Files.exists(path)) {
				Files.createFile(path);
			}
			final StringWriter sw = new StringWriter();
			final PrintWriter pw = new PrintWriter(sw, true);
			excep.printStackTrace(pw);
			String texto = Utilidades.formatearFecha(fecha.getTime()) + ": " +sw.getBuffer().toString().concat("\n\n");
			Files.write(path, texto.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e1) {
		}
	}

	/*
	 * Notificaciones
	 */
	public static final String ERROR_NOTIFICACION = "Ocurrió un error al enviar la notificación.";
	public static final String EXITO_MENSAJE_ENVIADO = "El mensaje se ha enviado con éxito. Compruebe su correo electrónico.";

	/*
	 * Validaciones
	 */
	public static final String ALERTA_VALIDACION_USUARIO_LONGITUD_CORTA = "El nombre de usuario no debe ser menor de 5 caracteres.";
	public static final String ALERTA_VALIDACION_USUARIO_LONGITUD_LARGA = "El nombre de usuario no debe superar los 20 caracteres.";
	public static final String ALERTA_VALIDACION_USUARIO_VACIO = "Debe escribir un nombre de usuario.";
	public static final String ALERTA_VALIDACION_USUARIO_FORMATO = "El nombre de usuario solo puede contener letras y números.";
	public static final String ALERTA_VALIDACION_CORREO_VACIO = "Debe escribir un correo electrónico.";
	public static final String ALERTA_VALIDACION_CORREO_FORMATO = "El correo electrónico introducido no es correcto.";
	public static final String ALERTA_VALIDACION_CONTRASENA_LONGITUD_CORTA = "La contraseña no debe ser menor de 5 caracteres.";
	public static final String ALERTA_VALIDACION_CONTRASENA_LONGITUD_LARGA = "La contraseña no debe superar los 20 caracteres.";
	public static final String ALERTA_VALIDACION_CONTRASENA_VACIO = "Debe escribir una contraseña.";
	public static final String ALERTA_VALIDACION_CONTRASENA_FORMATO = "La contraseña solo puede contener letras y numeros.";
	public static final String ALERTA_VALIDACION_CODIGO_VERIFICACION_VACIO = "Debe escribir el código que le hemos enviado al correo.";
	public static final String ALERTA_VALIDACION_NOMBRE_EXPEDIENTE_VACIO = "Debe escribir un nombre para el expediente.";
	public static final String ALERTA_VALIDACION_NOMBRE_EXPEDIENTE_LARGO = "El nombre del expediente es demasiado largo.";
	public static final String ALERTA_VALIDACION_APELLIDOS_EXPEDIENTE_VACIO = "Debe escribir un apellido para el expediente.";
	public static final String ALERTA_VALIDACION_APELLIDOS_EXPEDIENTE_LARGO = "El apellido del expediente es demasiado largo.";
	public static final String ALERTA_VALIDACION_TITULO_TAREA_VACIO = "Debe escribir un título para la tarea.";
	public static final String ALERTA_VALIDACION_TITULO_TAREA_LARGO = "El título de la tarea es demasiado largo.";
	public static final String ALERTA_VALIDACION_DESCRIPCION_TAREA_LARGO = "La descripción de la tarea es demasiado larga.";
	public static final String ALERTA_VALIDACION_CONCLUSIONES_PSICOLOGO_TAREA_LARGO = "Las conclusiones que ha escrito para el psicólogo son demasiado largas.";
	public static final String ALERTA_VALIDACION_CONCLUSIONES_PACIENTE_TAREA_LARGO = "Las conclusiones que ha escrito para el paciente son demasiado largas.";
	public static final String ALERTA_VALIDACION_NOMBRE_CAMPO_VACIO = "Debe escribir un nombre para el campo.";
	public static final String ALERTA_VALIDACION_NOMBRE_CAMPO_LARGO = "El nombre del campo es demasiado largo.";
	public static final String ALERTA_VALIDACION_DESCRIPCION_CAMPO_LARGO = "La descripción del campo es demasiado larga.";
	public static final String ALERTA_VALIDACION_COMENTARIO_VACIO = "Debe escribir un comentario.";
	public static final String ALERTA_VALIDACION_COMENTARIO_LARGO = "El comentario es demasiado largo.";

	/*
	 * CampoMB
	 */
	// Mensajes Error
	public static final String ERROR_GET_CAMPOS_LIST = "No se pudo obtener la lista de campos.";
	public static final String ERROR_ANADIR_CAMPO = "No se pudo crear el nuevo campo.";
	public static final String ERROR_ANADIR_CAMPO_EXISTE = "El campo ya existe.";
	public static final String ERROR_BORRAR_CAMPO = "No se pudo borrar el campo.";
	// Mensajes Exito
	public static final String EXITO_ANADIR_CAMPO = "Se ha añadido el nuevo campo.";
	public static final String EXITO_BORRAR_CAMPO = "El campo se ha eliminado con exito.";
	// Mensajes Alerta
	public static final String ALERTA_BORRAR_CAMPO = "El campo está asociado al menos a una tarea.";

	/*
	 * ComentarioMB
	 */
	// Mensajes Error
	public static final String ERROR_GET_COMENTARIOS_PSICOLOGO_DE_LA_TAREA_LIST = "No se pudo obtener la lista de comentarios.";
	public static final String ERROR_GET_COMENTARIOS_PACIENTE_DE_LA_TAREA_CAMPO_LIST = "No se pudo obtener la lista de comentarios.";
	public static final String ERROR_COMENTAR_PACIENTE = "No se pudieron enviar sus comentarios.";
	public static final String ERROR_COMENTAR_PSICOLOGO = "No se pudo enviar su comentario.";
	public static final String ERROR_EDITAR_COMENTARIO = "No se pudo editar su comentario.";
	public static final String ERROR_BORRAR_COMENTARIO = "No se pudo borrar su comentario.";
	// Mensajes Exito
	public static final String EXITO_COMENTAR_PACIENTE = "Sus comentarios han sido enviados.";
	public static final String EXITO_COMENTAR_PSICOLOGO = "Su comentario ha sido enviado.";
	public static final String EXITO_EDITAR_COMENTARIO = "Su comentario ha sido editado con éxito.";
	public static final String EXITO_BORRAR_COMENTARIO = "Su comentario ha sido borrado con éxito.";
	// Mesajes Alerta
	public static final String ALERTA_COMENTAR_PACIENTE = "No se pudieron enviar sus comentarios.";
	public static final String ALERTA_COMENTAR_PSICOLOGO = "No se pudo enviar su comentario.";
	public static final String ALERTA_EDITAR_COMENTARIO = "No se pudo editar su comentario.";
	public static final String ALERTA_BORRAR_COMENTARIO = "No se pudo borrar su comentario.";

	/*
	 * ExpedienteMB
	 */
	// Mensajes Error
	public static final String ERROR_GET_EXPEDIENTES_CREADOS_LIST = "No se pudo obtener la lista de expedientes.";
	public static final String ERROR_GET_EXPEDIENTES_ACCESIBLES_LIST = "No se pudo obtener la lista de expedientes.";
	public static final String ERROR_GET_EXPEDIENTES_CERRADOS_LIST = "No se pudo obtener la lista de expedientes.";
	public static final String ERROR_GET_PSICOLOGOS = "No se pudo obtener la lista de psicólogos.";
	public static final String ERROR_GET_PSICOLOGOS_CON_ACCESO = "No se pudo obtener la lista de psicólogos con acceso.";
	public static final String ERROR_CARGAR_PSICOLOGOS_EN_EXPEDIENTE = "No se pudo obtener la lista de psicólogos";
	public static final String ERROR_CERRAR_EXPEDIENTE = "No se pudo cerrar el expediente.";
	public static final String ERROR_REABRIR_EXPEDIENTE = "No se pudo reabrir el expediente.";
	public static final String ERROR_ABANDONAR_EXPEDIENTE = "No se pudo abandonar el expediente.";
	public static final String ERROR_ALTA_EXPEDIENTE = "No se pudo dar de alta el expediente.";
	public static final String ERROR_ALTA_EXPEDIENTE_EXISTE = "Usuario o correo ya registrados.";
	public static final String ERROR_EDITAR_EXPEDIENTE = "No se pudo editar el expediente.";
	public static final String ERROR_TRANSFERIR_EXPEDIENTE = "No se pudo transferir el expediente.";
	// Mensajes Exito
	public static final String EXITO_CERRAR_EXPEDIENTE = "El expediente se ha cerrado con éxito.";
	public static final String EXITO_REABRIR_EXPEDIENTE = "El expediente se ha reabierto con éxito.";
	public static final String EXITO_ABANDONAR_EXPEDIENTE = "El expediente se ha abandonado con éxito.";
	public static final String EXITO_ALTA_EXPEDIENTE = "El expediente se ha dado de alta con éxito.";
	public static final String EXITO_EDITAR_EXPEDIENTE = "El expediente se ha editado con éxito.";
	public static final String EXITO_TRANSFERIR_EXPEDIENTE = "El expediente ha sido transferido con éxito.";
	// Mesajes Alerta
	public static final String ALERTA_CERRAR_EXPEDIENTE = "No se pudo cerrar el expediente.";
	public static final String ALERTA_REABRIR_EXPEDIENTE = "No se pudo reabrir el expediente.";
	public static final String ALERTA_ABANDONAR_EXPEDIENTE = "No se pudo abandonar el expediente.";
	public static final String ALERTA_ALTA_EXPEDIENTE_USUARIO_VACIO = "El usuario no puede estar vacío.";
	public static final String ALERTA_ALTA_EXPEDIENTE_CORREO_VACIO = "El correo no puede estar vacío.";
	public static final String ALERTA_ALTA_EXPEDIENTE_CONTRASENA_VACIA = "La contraseña no puede estar vacía.";
	public static final String ALERTA_EDITAR_EXPEDIENTE = "No se pudo editar el expediente.";
	public static final String ALERTA_TRANSFERIR_EXPEDIENTE = "No se pudo transferir el expediente.";

	/*
	 * LoginMB
	 */
	// Mensajes Error
	public static final String ERROR_INICIAR_SESION = "No se pudo iniciar sesión.";
	public static final String ERROR_USUARIO_NO_ENCONTRADO = "El usuario o correo no existen";
	public static final String ERROR_RECUPERAR_CONTRASEÑA = "No se pudo restablecer su contraseña.";
	// Mensajes Alerta
	public static final String ALERTA_INICIAR_SESION_USUARIO_CONTRASENA = "Usuario o contraseña no válidos.";
	public static final String ALERTA_INICIAR_SESION = "No se pudo iniciar sesión.";
	public static final String ALERTA_CODIGO_NO_VALIDO = "El código introducido no es correcto. Revise nuevamente el correo que le hemos enviado.";
	public static final String ALERTA_USUARIO_NO_ENCONTRADO = "El usuario o correo no existen";
	public static final String ALERTA_TIEMPO_EXPIRADO = "El tiempo de utilización de su código ha expirado. Solicite un nuevo código.";

	/*
	 * TareaMB
	 */
	// Mensajes Error
	public static final String ERROR_GET_TAREAS_PENDIENTES_DEL_EXPEDIENTE_LIST = "No se pudo obtener la lista de tareas.";
	public static final String ERROR_GET_TAREAS_COMPLETADAS_DEL_EXPEDIENTE_LIST = "No se pudo obtener la lista de tareas.";
	public static final String ERROR_GET_CAMPOS_DE_LA_TAREA = "No se pudieron obtener los campos de la tarea.";
	public static final String ERROR_CARGAR_CAMPOS_EN_NUEVA_TAREA = "No se pudieron obtener los campos de la tarea.";
	public static final String ERROR_CARGAR_CAMPOS_EN_TAREA = "No se pudieron obtener los campos de la tarea.";
	public static final String ERROR_FIJAR_EN_LISTA = "No se pudieron obtener fijos de la tarea.";
	public static final String ERROR_CREAR_NUEVA_TAREA = "No se pudo crear la tarea.";
	public static final String ERROR_EDITAR_TAREA = "No se pudo editar la tarea.";
	public static final String ERROR_EDITAR_CAMPOS_TAREA = "No se pudieron editar los campos de la tarea.";
	public static final String ERROR_COMPLETAR_TAREA = "No se pudo completar la tarea.";
	public static final String ERROR_REABRIR_TAREA = "No se pudo reabrir la tarea.";
	public static final String ERROR_GET_CAMPOS_DE_LA_TAREA_LIST = "No se pudo obtener la lista de campos.";
	public static final String ERROR_CONSULTAR_TAREA = "No se pudo acceder a la tarea.";
	// Mensajes Exito
	public static final String EXITO_CREAR_NUEVA_TAREA = "La tarea se ha creado con éxito.";
	public static final String EXITO_EDITAR_TAREA = "La tarea se ha editado con éxito.";
	public static final String EXITO_EDITAR_CAMPOS_TAREA = "La tarea se ha actualizado con éxito.";
	public static final String EXITO_COMPLETAR_TAREA = "La tarea se ha completado con éxito.";
	public static final String EXITO_REABRIR_TAREA = "La tarea se ha reabierto con éxito.";
	// Mensajes Alerta
	public static final String ALERTA_CREAR_NUEVA_TAREA = "No se pudo crear la tarea.";
	public static final String ALERTA_EDITAR_TAREA = "No se pudo editar la tarea.";
	public static final String ALERTA_EDITAR_CAMPOS_TAREA = "No se pudieron editar los campos de la tarea.";
	public static final String ALERTA_COMPLETAR_TAREA = "No se pudo completar la tarea.";
	public static final String ALERTA_REABRIR_TAREA = "No se pudo reabrir la tarea.";

	/*
	 * UsuarioMB
	 */
	// Mensajes Error
	public static final String ERROR_ANADIR_USUARIO = "No se pudo registrar al usuario.";
	public static final String ERROR_ANADIR_USUARIO_EXISTE = "El usuario o el correo ya están registrados.";
	public static final String ERROR_EDITAR_USUARIO = "No se pudo editar su usuario.";
	public static final String ERROR_EDITAR_USUARIO_EXISTE = "El usuario o el correo ya están registrados.";
	// Mensajes Exito
	public static final String EXITO_ANADIR_USUARIO = "El usuario se ha registrado con éxito.";
	public static final String EXITO_EDITAR_USUARIO = "El usuario se ha editado con éxito.";
	// Mensajes Alerta
	public static final String ALERTA_ANADIR_USUARIO_VACIO = "El usuario no puede estar vacío.";
	public static final String ALERTA_ANADIR_USUARIO_CORREO = "El correo no puede estar vacío.";
	public static final String ALERTA_ANADIR_USUARIO_CONTRASENA = "Las contraseñas no coinciden.";
	public static final String ALERTA_EDITAR_USUARIO_NO_CONTRASENA = "La contraseña introducida no es correcta.";
	public static final String ALERTA_EDITAR_USUARIO_CONTRASENA = "Las contraseñas no coinciden.";

}