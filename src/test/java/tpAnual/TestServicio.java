package tpAnual;

import org.junit.*;

public class TestServicio {
	private Servicio rentas = new Servicio("Rentas");
	
	@Test
	public void esElMismoSerivio(){
		Assert.assertTrue(rentas.equals("Rentas"));
	}
	
	@Test
	public void tomaElNombreEnMayuscula(){
		Assert.assertTrue(rentas.equals("RENTAS"));
	}
	
	public void tomaElNombreEnMinuscula(){
		Assert.assertTrue(rentas.equals("rentas"));
	}
}
