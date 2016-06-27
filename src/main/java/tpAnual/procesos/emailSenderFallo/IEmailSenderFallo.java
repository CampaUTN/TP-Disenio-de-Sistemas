package tpAnual.procesos.emailSenderFallo;

import tpAnual.procesos.operaciones.Proceso;

/**
 * Interface para usar mockito.
 */
public interface IEmailSenderFallo {
	
	public void enviarMensajePorFallo(Proceso proceso);

	public void setMailReceptor(String mailReceptor);

}
