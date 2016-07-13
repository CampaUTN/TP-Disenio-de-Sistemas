package tpAnual.batch.accionesPostEjecucion;

/**
 * Interface para usar mockito.
 */
public interface IEmailSenderFallo extends Accion{
	public void setMailReceptor(String mailReceptor);
}
