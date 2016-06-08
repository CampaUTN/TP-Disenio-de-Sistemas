package tpAnual.com;

// Interface para mockear
public interface EmailSender {
	
	public void enviarMensaje(String titulo, String contenido);

	public void setMailAdministrador(String mailAdministrador);
}
