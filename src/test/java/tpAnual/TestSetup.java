package tpAnual;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.Before;
import org.uqbar.geodds.Point;

import tpAnual.POIs.Banco;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.acciones.RepositorioRegistros;
import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.utils.PointWrapper;

public class TestSetup {
	
	protected Set<String> tags = new HashSet<String>();
	protected PointWrapper ubicacion = new PointWrapper(54, 10);
	
	protected Poi poi = new EstacionDeColectivo(ubicacion, "107", tags,0,"");
	protected Poi poi2 = new EstacionDeColectivo(ubicacion, "108", tags,0,"");
	protected Negocio negocio = new Negocio(ubicacion,"mueblesSA",tags,"muebleria",10);
	
	protected BuscadorTexto buscador = new BuscadorTexto();
	protected Servicio servicio = new Servicio("rentas");
	
	protected BuscadorLocal local = new BuscadorLocal();
	
	protected Terminal terminal;
	
	protected List<Poi> poisBusqueda;
	
	@Before
	public void init() {
		SingletonReseter.resetAll();
		
		terminal = new Terminal(0);
		terminal.desactivarMails();
		
		poi.agregarTag("107");
		poi.agregarTag("colectivo");
		Mapa.getInstance().alta(poi);

		poi2.agregarTag("108");
		poi2.agregarTag("colectivo");
		Mapa.getInstance().alta(poi2);
				

		RepositorioBuscador.getInstance().agregarConsultora(local);
		RepositorioBuscador.getInstance().agregarConsultora(new BancoAdapter());
		RepositorioBuscador.getInstance().agregarConsultora(new CGPAdapter());
		
	}
}