package tpAnual.acciones.com;

/**
 * Interface para mockear.
 */
public interface EmailSender {
	
	public void enviarMensajePorDemora(Long limite);

	public void setMailReceptor(String mailReceptor);
}
