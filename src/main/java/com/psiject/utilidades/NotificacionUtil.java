package com.psiject.utilidades;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The Class NotificacionUtil.
 */
public class NotificacionUtil {

   private final static String USERNAME = "notificacionespsiject@gmail.com";
   private final static String PASSWORD = "nqobwhopfkxyapon";

   public static void notificacionNuevoRegistro(final String to, final String nombrePaciente, final String usuarioPsicologo, final String usuarioPaciente,
         final String contrasenaPaciente) throws MessagingException {
      final String subject = "Bienvenido";

      final String body = "Hola <b>" + nombrePaciente + "</b>.<br /><br /><b>" + usuarioPsicologo
            + "</b> le ha dado de alta en la aplicación. Puede iniciar sesión con los siguientes datos:<br /><ul><li><b>Usuario:</b> <i>" + usuarioPaciente
            + "</i></li><li><b>Contraseña:</b> <i>" + contrasenaPaciente + "</i></li></ul>Recuerda que puedes cambiar tu contraseña cuando lo desees desde la propia aplicación.";

      enviarEmail(to, subject, body);
   }

   public static void notificacionNuevaTarea(final String to, final String nombrePaciente, final String usuarioPsicologo, final String tituloTarea, final String descripcionTarea)
         throws MessagingException {
      final String subject = "Nueva tarea";

      final String body = "Hola <b>" + nombrePaciente + "</b>.<br /><br /><b>" + usuarioPsicologo + "</b> ha añadido una nueva tarea a tu cuenta:<br /><ul><li><b>Título:</b> <i>"
            + tituloTarea + ".</i></li><li><b>Descripción:</b> <i>" + descripcionTarea + ".</i></li></ul>";

      enviarEmail(to, subject, body);
   }

   public static void notificacionNuevoComentario(final String to, final String nombrePaciente, final String usuarioPsicologo, final String tituloTarea, final String comentario)
         throws MessagingException {
      final String subject = "Nuevo comentario";

      final String body = "Hola <b>" + nombrePaciente + "</b>.<br /><br /><b>" + usuarioPsicologo + "</b> ha añadido el siguiente comentario en la tarea <i>'" + tituloTarea
            + "'</i>:<br /><br /> <i>'" + comentario + ".'</i>";

      enviarEmail(to, subject, body);
   }

   public static void notificacionNuevaSugerencia(final String to, final String sugerencia) throws MessagingException {
      final String subject = "Nueva sugerencia";

      final String body = "Se ha enviado la siguiente sugerencia:<br /><br /> <i>'" + sugerencia + ".'</i>";

      enviarEmail(to, subject, body);
   }

   public static void notificacionRecuperarContraseña(final String to, final String nombreUsuario, final String codigo) throws MessagingException {
      final String subject = "Recuperar contraseña";

      final String body = "Hola <b>"
            + nombreUsuario
            + "</b>.<br /><br />Para restablecer su contraseña haga click en el siguiente enlace e introduzca el código de validación:<br /><ul><li><b>Código de validación:</b> <i>"
            + codigo + "</i></li><li><b>Restablecer contraseña:</b> <a href='" + Utilidades.getURL() + "/comun/recuperarContrasena.xhtml?correo=" + to
            + "'>Pulse aquí</a></li></ul>";
      enviarEmail(to, subject, body);
   }

   private static void enviarEmail(final String to, final String subject, final String body) throws MessagingException {
      final Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(USERNAME, PASSWORD);
         }
      });

      final InternetAddress address = new InternetAddress(to);
      final Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(USERNAME));
      message.setRecipient(Message.RecipientType.TO, address);
      message.setSubject(subject);
      message.setContent(body, "TEXT/HTML");

      Transport.send(message);

   }
}
