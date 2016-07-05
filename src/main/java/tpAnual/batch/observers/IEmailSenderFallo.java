package tpAnual.batch.observers;

import tpAnual.batch.procesos.Proceso;

/**
 * Interface para usar mockito.
 */
public interface IEmailSenderFallo {
	
	public void enviarMensajePorFallo(Proceso proceso);

	public void setMailReceptor(String mailReceptor);

}
