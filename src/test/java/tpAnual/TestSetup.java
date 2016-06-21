package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.Before;
import org.uqbar.geodds.Point;

import tpAnual.POIs.Banco;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.acciones.RepositorioRegistros;
import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;

public class TestSetup {
	
	protected Set<String> tags = new HashSet<String>();
	protected EstacionDeColectivo tipo = new EstacionDeColectivo();
	protected Banco frances= new Banco();
	protected Point ubicacion = new Point(54, 10);
	
	protected Poi poi = new Poi(tipo, ubicacion, "107", tags);
	protected Poi poi2 = new Poi(tipo, ubicacion, "108", tags);
	
	protected BuscadorTexto buscador = new BuscadorTexto();
	protected Servicio servicio = new Servicio("rentas");
	
	private BancoAdapter bancoAdapter = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	protected BuscadorLocal local = new BuscadorLocal(Mapa.getInstance());
	
	protected Terminal terminal;
	
	protected List<Poi> poisBusqueda = new ArrayList<>();
	
	@Before
	public void init() {
		Mapa.resetSingleton();
		terminal = new Terminal(0);
		terminal.desactivarMails();
		
		poi.agregarTag("107");
		poi.agregarTag("colectivo");
		Mapa.getInstance().alta(poi);

		poi2.agregarTag("108");
		poi2.agregarTag("colectivo");
		Mapa.getInstance().alta(poi2);
				
		RepositorioRegistros.resetSingleton();
		RepositorioBuscador.resetSingleton();
		RepositorioBuscador.getInstance().agregarConsultora(local);
		RepositorioBuscador.getInstance().agregarConsultora(bancoAdapter);
		RepositorioBuscador.getInstance().agregarConsultora(cgpAdapter);
	}
}
