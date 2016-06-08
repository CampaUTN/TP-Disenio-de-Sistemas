package tpAnual.com;

// Interface para mockear
public interface EmailSender {
	
	public void enviarMensajePorDemora(Long limite);

	public void setMailAdministrador(String mailAdministrador);
}
