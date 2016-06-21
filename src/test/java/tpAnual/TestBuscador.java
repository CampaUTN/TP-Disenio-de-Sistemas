package tpAnual;


import java.util.Arrays;
import java.util.List;

import org.junit.*;

import tpAnual.POIs.Poi;

public class TestBuscador extends TestSetup{
	
	@Before
	public void init(){
		super.init();
		poisBusqueda = buscador.buscarSegunTexto("colectivo",terminal);
	}
	
	
	@Test
	public void testNoSeEncuentraEnPoisLocales(){ 
		terminal.desactivarRegistros();
		List<String> palabras = Arrays.asList("zzzz".split(" "));
		Assert.assertEquals(0,local.consultar(palabras).size(),0);
	}

	@Test
	public void testUnaBusquedaParaLosTresOrigenes(){ 
		Assert.assertEquals(6, poisBusqueda.size(),0); //2 del init, 2 de bancos, 2 de cpos
	}
	
	@Test
	public void testFiltroSoloCGPs(){
		List<Poi> pois = RepositorioBuscador.getInstance().obtenerCGPsConServicioExternos("Cheques");
		Assert.assertEquals(2,pois.size(),0);
	}
	
	@Test
	public void encuentroRubroMuebleria(){
		Mapa.getInstance().alta(poiNegocio);
		List<Poi> pois = buscador.buscarSegunTexto("muebleria", terminal);
		pois.stream().anyMatch(poi->poi.getNombre().equals("mueblesSA"));
	}
}
