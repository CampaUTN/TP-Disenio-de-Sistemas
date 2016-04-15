package tpAnual;

import org.junit.*;

public class TestServicio {
	private Servicio rentas = new Servicio("rentas");
	
	@Test
	public void esElMismoSerivio()
	{
		Assert.assertTrue(rentas.es("rentas"));
	}
	
	// aca irian test de si da el servicio a tal o cual hora en tal o cual dia...
}
