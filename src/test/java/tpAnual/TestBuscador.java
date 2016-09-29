package tpAnual;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.POIs.Poi;
import tpAnual.busquedas.RepositorioBuscador;

public class TestBuscador extends TestSetup{
	
	@Before
	public void init(){
		super.init();
	}
	
	
	@Test
	public void testNoSeEncuentraEnPoisLocales(){ 
		terminal.desactivarRegistros();
		List<String> palabras = Arrays.asList("zzzz".split(" "));
		Assert.assertTrue(local.consultar(palabras).isEmpty());
	}

	@Test
	public void testUnaBusquedaParaLosTresOrigenes(){ 
		poisBusqueda = buscador.buscarSegunTexto("colectivo",terminal);
		Assert.assertEquals(6, poisBusqueda.size(),0); //2 del init, 2 de bancos, 2 de cpos
	}
	
	@Test
	public void testFiltroSoloCGPs(){
		List<Poi> pois = RepositorioBuscador.getInstance().obtenerCGPsConServicioExternos("Cheques");
		Assert.assertEquals(2,pois.size(),0);
	}
	
	@Test
	public void encuentroRubroMuebleria(){
		Mapa.getInstance().alta(negocio);
		poisBusqueda = buscador.buscarSegunTexto("muebleria",terminal);
		
		Assert.assertTrue(poisBusqueda.contains(negocio));
	}
}