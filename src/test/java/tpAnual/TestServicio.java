package tpAnual;

import org.junit.*;

public class TestServicio {
	private Servicio rentas = new Servicio("Rentas");
	
	@Test
	public void esElMismoSerivio()
	{
		Assert.assertTrue(rentas.es("Rentas"));
	}
	
	@Test
	public void tomaElNombreEnMayuscula(){
		Assert.assertTrue(rentas.es("RENTAS"));
	}
	
	public void tomaElNombreEnMinuscula(){
		Assert.assertTrue(rentas.es("rentas"));
	}
}
