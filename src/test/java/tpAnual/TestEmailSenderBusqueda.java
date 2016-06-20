package tpAnual;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tpAnual.acciones.com.EmailSender;

public class TestEmailSenderBusqueda extends TestSetup{
	
	private EmailSender sender;
	private Terminal terminal1;
	
	@Before
	public void init(){
		sender = Mockito.mock(EmailSender.class);
		terminal1 = new Terminal(1);
		terminal1.desactivarRegistros();
		terminal1.setSender(sender);
	}
	
	@Test
	public void testearMailPorDemora(){
		mapa.buscar("colectivo",terminal1);
		Mockito.verify(sender).enviarMensajePorDemora(Mockito.anyLong()); //uso anyLong porque la demora no es determinable antes de ejecutar el metodo.
	}
	
	@Test
	public void testMailsDesactivados(){
		terminal1.desactivarMails();
		mapa.buscar("colectivo",terminal);
		Mockito.verifyNoMoreInteractions(sender);
	}
}
