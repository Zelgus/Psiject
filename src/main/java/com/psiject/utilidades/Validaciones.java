package com.psiject.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class Validaciones {

   public static void validarNombreUsuario(String texto) throws DataFormatException {
      final Pattern pat = Pattern.compile("^[a-zA-Z0-9Ò—][a-zA-Z0-9Ò—]*$");
      final Matcher mat = pat.matcher(texto);

      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_USUARIO_VACIO);
      } else if (texto.length() < 5) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_USUARIO_LONGITUD_CORTA);
      } else if (texto.length() > 20) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_USUARIO_LONGITUD_LARGA);
      }
      
      if (!mat.matches()) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_USUARIO_FORMATO);
      }
   }

   public static void validarCorreo(String texto) throws DataFormatException {
      final Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
      final Matcher mat = pat.matcher(texto);

      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CORREO_VACIO);
      }
      
      if (!mat.matches()) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CORREO_FORMATO);
      }
   }

   public static void validarContraseÒa(String texto) throws DataFormatException {
      final Pattern pat = Pattern.compile("^[a-zA-Z0-9Ò—][a-zA-Z0-9Ò—]*$");
      final Matcher mat = pat.matcher(texto);

      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CONTRASENA_VACIO);
      } else if (texto.length() < 5) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CONTRASENA_LONGITUD_CORTA);
      } else if (texto.length() > 20) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CONTRASENA_LONGITUD_LARGA);
      }
      
      if (!mat.matches()) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CONTRASENA_FORMATO);
      }
   }
   
   public static void validarCodigoVerificacion(String texto) throws DataFormatException {
      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CODIGO_VERIFICACION_VACIO);
      }
   }
   
   public static void validarNombreExpediente(String texto) throws DataFormatException {
      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_NOMBRE_EXPEDIENTE_VACIO);
      } else if (texto.length() > 250) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_NOMBRE_EXPEDIENTE_LARGO);
      }
   }
   
   public static void validarApellidosExpediente(String texto) throws DataFormatException {
      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_APELLIDOS_EXPEDIENTE_VACIO);
      } else if (texto.length() > 250) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_APELLIDOS_EXPEDIENTE_LARGO);
      }
   }
   
   public static void validarTituloTarea(String texto) throws DataFormatException {
      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_TITULO_TAREA_VACIO);
      } else if (texto.length() > 250) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_TITULO_TAREA_LARGO);
      }
   }
   
   public static void validarDescripcionTarea(String texto) throws DataFormatException {
      if (texto.length() > 1000) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_DESCRIPCION_TAREA_LARGO);
      }
   }
   
   public static void validarConclusionesPsicologoTarea(String texto) throws DataFormatException {
      if (texto.length() > 1000) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CONCLUSIONES_PSICOLOGO_TAREA_LARGO);
      }
   }
   
   public static void validarConclusionesPacienteTarea(String texto) throws DataFormatException {
      if (texto.length() > 1000) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_CONCLUSIONES_PACIENTE_TAREA_LARGO);
      }
   }

   public static void validarNombreCampo(String texto) throws DataFormatException {
      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_NOMBRE_CAMPO_VACIO);
      } else if (texto.length() > 250) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_NOMBRE_CAMPO_LARGO);
      }
   }
   
   public static void validarDescripcionCampo(String texto) throws DataFormatException {
     if (texto.length() > 1000) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_DESCRIPCION_CAMPO_LARGO);
      }
   }
   
   public static void validarComentarioPaciente(String texto) throws DataFormatException {
      if (texto.length() > 1000) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_COMENTARIO_LARGO);
      }
   }

   public static void validarComentarioPsicologo(String texto) throws DataFormatException {
      if (texto.length() == 0) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_COMENTARIO_VACIO);
      } else if (texto.length() > 1000) {
         throw new DataFormatException(Mensajes.ALERTA_VALIDACION_COMENTARIO_LARGO);
      }
   }
}
