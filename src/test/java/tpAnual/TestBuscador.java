package tpAnual;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.*;
import org.uqbar.geodds.Point;

import tpAnual.POIs.Banco;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.acciones.RepositorioRegistros;
import tpAnual.acciones.reportes.RegistroBusqueda;
import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;

public class TestBuscador {
	protected Mapa mapa = new Mapa();
	
	protected Set<String> tags = new HashSet<String>();
	protected EstacionDeColectivo tipo = new EstacionDeColectivo();
	protected Banco frances= new Banco();
	protected Point ubicacion = new Point(54, 10);
	
	protected Poi poi = new Poi(tipo, ubicacion, "107", tags);
	protected Poi poi2 = new Poi(tipo, ubicacion, "108", tags);
	
	
	protected Servicio servicio = new Servicio("rentas");
	
	private BancoAdapter bancoAdapter = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	private BuscadorLocal local = new BuscadorLocal(mapa);
	
	protected Terminal terminal = new Terminal(0);
	
	protected List<Poi> poisBusqueda = new ArrayList<>();
	protected RegistroBusqueda registro;
	
	@Before
	public void init() {
		terminal.desactivarMails();
		
		poi.agregarTag("107");
		poi.agregarTag("colectivo");
		mapa.alta(poi);

		poi2.agregarTag("108");
		poi2.agregarTag("colectivo");
		mapa.alta(poi2);
				
		RepositorioRegistros.resetSingleton();
		RepositorioBuscador.resetSingleton();
		RepositorioBuscador.getInstance().agregarConsultora(local);
		RepositorioBuscador.getInstance().agregarConsultora(bancoAdapter);
		RepositorioBuscador.getInstance().agregarConsultora(cgpAdapter);
		
		poisBusqueda = mapa.buscar("colectivo",terminal);
		registro = RepositorioRegistros.getInstance().getRegistros().get(0);
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
}
