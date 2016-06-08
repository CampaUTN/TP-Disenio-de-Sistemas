package tpAnual.com;

// Interface para mockear
public interface EmailSender {
	
	public void enviarMensajePorDemora(int limite);

	public void setMailAdministrador(String mailAdministrador);
}
