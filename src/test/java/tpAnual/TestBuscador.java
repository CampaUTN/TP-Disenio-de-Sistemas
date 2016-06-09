package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.*;
import org.uqbar.geodds.Point;

import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;

public class TestBuscador {
	protected Set<String> tags = new HashSet<String>();
	protected EstacionDeColectivo tipo = new EstacionDeColectivo();
	protected Banco frances= new Banco();
	protected Point ubicacion = new Point(54, 10);
	protected Poi poi = new Poi(tipo, ubicacion, "107", tags);
	protected Poi poi2 = new Poi(tipo, ubicacion, "108", tags);
	protected Mapa mapa = new Mapa();
	protected Servicio servicio = new Servicio("rentas");
	
	private BancoAdapter bancoAdapter = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	
	@Before
	public void init() {
		poi.agregarTag("107");
		poi.agregarTag("colectivo");
		mapa.alta(poi);

		poi2.agregarTag("108");
		poi2.agregarTag("colectivo");
		mapa.alta(poi2);

		mapa.getBuscador().agregarAdapterExterno(bancoAdapter);
		mapa.getBuscador().agregarAdapterExterno(cgpAdapter);
	}

	@Test 
	public void elBuscadorAgregaAdaptersPolimorficamente(){
		Assert.assertEquals(2, mapa.getBuscador().getAdapters().size(), 0);
	}
	
	@Test
	public void elBuscadorTrataAdaptersPolimorficamente(){
		
		BuscadorTexto buscador = new BuscadorTexto();
		buscador.agregarAdapterExterno(bancoAdapter);
		buscador.agregarAdapterExterno(cgpAdapter);
		
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		List<Poi> pois = new ArrayList<>();
		
		buscador.buscarEnPoisExternos(palabras, pois);
		
		Assert.assertEquals(4, pois.size() ,0);
	}
	
	@Test
	public void testUnaBusquedaParaLosTresOrigenes(){
		Assert.assertEquals(6, mapa.buscar("colectivo",null).size(),0); //2 del init, 2 de bancos, 2 de cpos
	}
	
	@Test
	public void testFiltroSoloCGPs(){
				
		List<Poi> pois = mapa.getBuscador().obtenerCGPsConServicioExternos("Cheques");
		Assert.assertEquals(2,pois.size(),0);
		
	}
}
