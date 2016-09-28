package tpAnual;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.util.Reseter;

public class TestServicio {
	private Servicio rentas = new Servicio("Rentas");
	
	@Before
	public void init(){
		Reseter.resetSingletons();
	}
	
	@Test
	public void esElMismoSerivio(){
		Assert.assertTrue(rentas.tienePorNombre("Rentas"));
	}
	
	@Test
	public void tomaElNombreEnMayuscula(){
		Assert.assertTrue(rentas.tienePorNombre("RENTAS"));
	}
	
	public void tomaElNombreEnMinuscula(){
		Assert.assertTrue(rentas.tienePorNombre("rentas"));
	}
}
