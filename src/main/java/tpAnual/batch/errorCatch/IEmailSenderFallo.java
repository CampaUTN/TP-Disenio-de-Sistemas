package tpAnual.batch.errorCatch;

/**
 * Interface para usar mockito.
 */
public interface IEmailSenderFallo extends Accion{
	public void setMailReceptor(String mailReceptor);
}
