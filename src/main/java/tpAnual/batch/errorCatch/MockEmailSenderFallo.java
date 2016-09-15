package tpAnual.batch.errorCatch;

import tpAnual.batch.procesos.Proceso;
import tpAnual.com.EmailSender;


public class MockEmailSenderFallo extends EmailSender implements IEmailSenderFallo, Accion{
	
	@Override
	public void accionar(Proceso proceso) {}
}
