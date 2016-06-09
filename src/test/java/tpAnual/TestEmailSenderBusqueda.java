package tpAnual;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tpAnual.com.EmailSender;


public class TestEmailSenderBusqueda extends TestBuscador{
	private EmailSender sender;
	private Terminal terminal;
	@Before
	public void init(){
		super.init();
		sender = Mockito.mock(EmailSender.class);
		mapa.getBuscador().setSender(sender);
	}
	
	@Test
	public void testearAlgo(){
		// Testeo con 0 como limite porque es imposible que tarde menos que 0.
		mapa.getBuscador().setLimite((long) 0);
<<<<<<< HEAD
		mapa.buscar("colectivo",null);
=======
		mapa.buscar("colectivo",terminal);
>>>>>>> 30bc869988045b65da32432db6c9ed2f0802a984
		Mockito.verify(sender).enviarMensajePorDemora((long) 0);
	}
	
	@Test
	public void adssd(){
		// Por default no deberia tardar, porque los test usan mocks asi que no hay mas retardo que el de
		// la maquina donde se corran, y el limite por defecto esta seteado en un valor altisimo.
<<<<<<< HEAD
		mapa.buscar("colectivo",null);
=======
		mapa.buscar("colectivo",terminal);
>>>>>>> 30bc869988045b65da32432db6c9ed2f0802a984
		Mockito.verifyNoMoreInteractions(sender);
	}
}
