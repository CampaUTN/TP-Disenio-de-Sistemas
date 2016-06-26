package tpAnual;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tpAnual.acciones.emailSenderBusqueda.IEmailSenderBusqueda;

public class TestEmailSenderBusqueda extends TestSetup{
	
	private IEmailSenderBusqueda sender;
	
	@Before
	public void init(){
		super.init();
		sender = Mockito.mock(IEmailSenderBusqueda.class);
		terminal = new Terminal(1);
		terminal.desactivarRegistros();
		terminal.setSender(sender);
	}
	
	@Test
	public void testearMailPorDemora(){
		buscador.buscarSegunTexto("colectivo",terminal);
		Mockito.verify(sender).enviarMensajePorDemora(Mockito.anyLong()); //uso anyLong porque la demora no es determinable antes de ejecutar el metodo.
	}
	
	@Test
	public void testMailsDesactivados(){
		terminal.desactivarMails();
		buscador.buscarSegunTexto("colectivo",terminal);
		Mockito.verifyNoMoreInteractions(sender);
	}
}
