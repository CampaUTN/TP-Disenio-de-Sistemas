package tpAnual.bd.persistencia.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.busquedas.BuscadorLocal;
import tpAnual.busquedas.BuscadorTexto;
import tpAnual.busquedas.Busqueda;
import tpAnual.busquedas.RepositorioBuscadores;
import tpAnual.util.Reseter;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;


public class TestPersistenciaBusquedaMongo {
	
	private static Datastore datastore;
	
	private Set<String> tags1 = new HashSet<String>();
	private Set<String> tags2 = new HashSet<String>();
	private PointWrapper ubicacion = new PointWrapper(54, 10);
	
	private Poi poi1 = new EstacionDeColectivo(ubicacion, "107", tags1,0,"");
	private Poi poi2 = new EstacionDeColectivo(ubicacion, "106", tags2,0,"");
	
	private Terminal terminal = new Terminal();
	
	private BuscadorLocal local = new BuscadorLocal();
	private BuscadorTexto buscador = new BuscadorTexto();		
		
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		datastore = MongoDatastoreSingleton.getDatastore("busquedas");
	}
	
	@Before
	public void init() {
		Reseter.resetSingletons();
		Reseter.resetDatastore(datastore);
		
		poi1 = new EstacionDeColectivo(ubicacion, "107", tags1,0,"");
		poi2 = new EstacionDeColectivo(ubicacion, "106", tags2,0,"");
		
		poi1.removerTags();
		poi2.removerTags();
		
		poi1.agregarTag("mejor");
		poi1.agregarTag("colectivo");
		poi2.agregarTag("chocador");
				
		terminal.desactivarMails();
		terminal.desactivarRegistros();
		
		RepositorioBuscadores.getInstance().agregarConsultora(local);
		
		Mapa.getInstance().alta(poi1);
		Mapa.getInstance().alta(poi2);
	}
	
	@After
	public void clear() {
		Reseter.resetSingletons();
		Reseter.resetDatastore(datastore);
	}
	
	@Test
	public void comprueboQueNoHayNadaPersistido() throws UnknownHostException{		
		Assert.assertTrue(datastore.createQuery(Busqueda.class).asList().isEmpty());
	}
	
	@Test 
	public void sePersisteLaBusqueda() throws UnknownHostException{
		
		List <Poi> pois = new ArrayList<>();
		pois.add(poi1);
		
		Busqueda busqueda = new Busqueda("colectivo", pois);		
		datastore.save(busqueda);		
		
		Assert.assertFalse(datastore.createQuery(Busqueda.class).asList().isEmpty());
	}	
	
	@Test 
	public void seRealizaUnaBusquedaYLuegoSePersiste(){
		buscador.buscarSegunTexto("colectivo",terminal);		

		Assert.assertNotEquals(0, datastore.createQuery(Busqueda.class).asList().size());
	}
			
	
	@Test
	public void sePersistenVariosPoisLocalesConLaBusqueda(){		
		poi2.agregarTag("colectivo");
		List<Poi> pois = buscador.buscarSegunTexto("colectivo", terminal);	
		
		long cantidadPois = datastore.find(Busqueda.class).get().getResultado().size();
			
		Assert.assertEquals(pois.size(),cantidadPois);
	}
	
}
