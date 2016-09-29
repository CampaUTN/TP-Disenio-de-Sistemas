package tpAnual;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import tpAnual.POIs.Poi;
import tpAnual.busquedas.RepositorioBuscador;

public class TestBuscador extends TestSetup{
	
	
	@Test
	public void testNoSeEncuentraEnPoisLocales(){ 
		terminal.desactivarRegistros();
		List<String> palabras = Arrays.asList("zzzz".split(" "));
		Assert.assertTrue(local.consultar(palabras).isEmpty());
	}
	
	@Test
	public void encuentroRubroMuebleria(){
		Mapa.getInstance().alta(negocio);
		poisBusqueda = buscador.buscarSegunTexto("muebleria",terminal);
		
		Assert.assertTrue(poisBusqueda.contains(negocio));
	}
}