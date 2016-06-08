package tpAnual.com;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderBusqueda implements EmailSender {
	private String mailAdministrador;
	private String de = "grupo7-noreply@gmail.com";
	private String usuario = "grupo7";
	private String contrasenia = "******";
	private String host = "pois.gob.ar";
	// Si cambian habria que tocar el codigo de todas formas, asi que los declaro final
	private final Properties propiedades = this.crearPropiedades();
	private final Session sesion = this.crearSesion();

	public void enviarMensaje(String titulo, String contenido) {
		try {
			Transport.send(crearMensaje(titulo, contenido));
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	
	// Constructor
	public EmailSenderBusqueda(String mailAdministrador) {
		this.mailAdministrador = mailAdministrador;
	}


	// Setters
	public void setMailAdministrador(String mailAdministrador) {
		this.mailAdministrador = mailAdministrador;
	}
	
	
	// Constructores varios
	// Estos constructores están aca porque no tiene sentido hacer una clase
	// para que solo haga esto.
	private Properties crearPropiedades() {
		Properties propiedades = new Properties();
		propiedades.put("mail.smtp.auth", "true");
		propiedades.put("mail.smtp.starttls.enable", "true");
		propiedades.put("mail.smtp.host", host);
		propiedades.put("mail.smtp.port", "25");
		return propiedades;
	}

	private Message crearMensaje(String titulo, String contenido) {
		Message mensaje = new MimeMessage(sesion);
		try {
			mensaje.setFrom(new InternetAddress(de));
			mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailAdministrador));
			mensaje.setSubject(titulo);
			mensaje.setText(contenido);
		} catch (AddressException e) {
			throw new RuntimeException(e);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return mensaje;
	}

	private Session crearSesion() {
		return Session.getInstance(propiedades, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, contrasenia);
			}
		});
	}
}
