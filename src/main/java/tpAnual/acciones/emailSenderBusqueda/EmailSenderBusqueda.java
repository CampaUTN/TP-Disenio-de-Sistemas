package tpAnual.acciones.emailSenderBusqueda;

import tpAnual.com.EmailSender;

/**
 * Singleton para avisar por mail al administrador si las busquedas tardan mas del limite establecido.
 */
public class EmailSenderBusqueda extends EmailSender implements IEmailSenderBusqueda{
	private Long limite = Long.parseLong("0");
	private static EmailSenderBusqueda instance = null;
	
	
	private EmailSenderBusqueda(){
		//Para evitar que sea instanciada esta clase.
	}
	
	public static EmailSenderBusqueda getInstance(){
		if(instance==null){
			instance = new EmailSenderBusqueda();
		}
		return instance;
	}
	
	@Override
	public void enviarMensajePorDemora(Long duracionBusqueda) {
		if(duracionBusqueda>limite){
			enviarMensaje("Busqueda lenta", "La busqueda tardó más de "+limite+" segundos.");
		}
	}
	
	//Getters
	public Long getLimite() {
		return limite;
	}
	
	public void setLimite(Long limite) {
		this.limite = limite;
	}
}
