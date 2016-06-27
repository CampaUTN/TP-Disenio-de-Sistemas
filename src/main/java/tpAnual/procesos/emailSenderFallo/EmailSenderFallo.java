package tpAnual.procesos.emailSenderFallo;

import tpAnual.com.EmailSender;
import tpAnual.procesos.operaciones.Proceso;

/**
 * Singleton para avisar por mail al administrador que un proceso fallo mas de N veces consecutivas.
 */
public class EmailSenderFallo extends EmailSender implements IEmailSenderFallo{
	private static EmailSenderFallo instance = null;
	
	private EmailSenderFallo(){
		//Para evitar que sea instanciada esta clase.
	}
	
	public static EmailSenderFallo getInstance(){
		if(instance==null){
			instance = new EmailSenderFallo();
		}
		return instance;
	}
	
	@Override
	public void enviarMensajePorFallo(Proceso proceso) {
		enviarMensaje("Proceso fallido",
				"El proceso "+proceso.getNombre()
				+" falló luego de intentar ejecutarlo "
				+proceso.getIntentos()+" veces.");
	}
}
