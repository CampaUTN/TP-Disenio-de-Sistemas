package tpAnual.util.com;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase abstracta de la que heredan todos los Senders que se hagan.
 */
public abstract class EmailSender {
	protected String receptor="pepe@gmail.com";
	protected String de = "grupo7-noreply@gmail.com";
	protected String usuario = "grupo7";
	protected String contrasenia = "******";
	protected String host = "pois.gob.ar";
	// Si cambian habria que tocar el codigo de todas formas, asi que los declaro final
	protected final Properties propiedades = this.crearPropiedades();
	protected final Session sesion = this.crearSesion();
	
	public void enviarMensaje(String titulo, String contenido) {
		try {
			Transport.send(crearMensaje(titulo, contenido));
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	// Setters
	public void setMailReceptor(String mailReceptor) {
		this.receptor = mailReceptor;
	}
	
	// Constructores varios
	// Estos constructores están aca porque no tiene sentido hacer una clase
	// para que solo haga esto.
	protected Properties crearPropiedades() {
		Properties propiedades = new Properties();
		propiedades.put("mail.smtp.auth", "true");
		propiedades.put("mail.smtp.starttls.enable", "true");
		propiedades.put("mail.smtp.host", host);
		propiedades.put("mail.smtp.port", "25");
		return propiedades;
	}

	protected Message crearMensaje(String titulo, String contenido) {
		Message mensaje = new MimeMessage(sesion);
		try {
			mensaje.setFrom(new InternetAddress(de));
			mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
			mensaje.setSubject(titulo);
			mensaje.setText(contenido);
		} catch (AddressException e) {
			throw new RuntimeException(e);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return mensaje;
	}

	protected Session crearSesion() {
		return Session.getInstance(propiedades, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, contrasenia);
			}
		});
	}
}
