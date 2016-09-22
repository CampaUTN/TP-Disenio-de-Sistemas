package tpAnual.batch.postEjecucionProceso;

import tpAnual.batch.procesos.Proceso;
import tpAnual.util.com.EmailSender;


public class MockEmailSenderFallo extends EmailSender implements IEmailSenderFallo, PostEjecucionProceso{
	
	@Override
	public void accionar(Proceso proceso) {}
}
