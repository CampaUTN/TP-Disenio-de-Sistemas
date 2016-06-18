package tpAnual.com;

/**
 * Singleton usado cuando se deactiva la funcionalidad de enviar mails en cierta terminal.
 */
public class MockEmailSenderBusqueda implements EmailSender {
	private static MockEmailSenderBusqueda instance = null;
	
	private MockEmailSenderBusqueda(){
		//Para evitar que sea instanciada esta clase.
	}
	
	public static MockEmailSenderBusqueda getInstance(){
		if(instance==null){
			instance = new MockEmailSenderBusqueda();
		}
		return instance;
	}
	
	public void enviarMensajePorDemora(Long limite){
		// Es un mock, asi que esto no hace nada.
	}

	// Setters
	public void setMailReceptor(String mailAdministrador) {
		EmailSenderBusqueda.getInstance().setMailReceptor(mailAdministrador);
	}
}
