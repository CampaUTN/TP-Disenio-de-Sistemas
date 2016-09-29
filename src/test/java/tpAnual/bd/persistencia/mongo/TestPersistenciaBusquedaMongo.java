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
import tpAnual.busquedas.RepositorioBuscador;
import tpAnual.externo.sistemasExternos.BancoExterno;
import tpAnual.externo.sistemasExternos.ServicioDTO;
import tpAnual.util.Reseter;
import tpAnual.util.bd.PoiDTO;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;


public class TestPersistenciaBusquedaMongo {
	
//	private static Datastore datastore = morphia.createDatastore(mongo, DB_Name);

	private static Datastore datastore;
	
	private Set<String> tags1 = new HashSet<String>();
	private Set<String> tags2 = new HashSet<String>();
	private PointWrapper ubicacion = new PointWrapper(54, 10);
	
	private Poi poi1 = new EstacionDeColectivo(ubicacion, "107", tags1,0,"");
	private Poi poi2 = new EstacionDeColectivo(ubicacion, "106", tags2,0,"");

	private PoiDTO poiDto1;
	private PoiDTO poiDto2;
	
	private Terminal terminal = new Terminal();
	
	private BuscadorLocal local = new BuscadorLocal();
	private BuscadorTexto buscador = new BuscadorTexto();		
	
//	Query<PoiDTO> query = datastore.createQuery(PoiDTO.class);
	
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		datastore = MongoDatastoreSingleton.getDatastore("busquedas");
	}
	
	@Before
	public void init() {
		Reseter.resetSingletons();
		
		poi1 = new EstacionDeColectivo(ubicacion, "107", tags1,0,"");
		poi2 = new EstacionDeColectivo(ubicacion, "106", tags2,0,"");
		
		poi1.removerTags();
		poi2.removerTags();
		
		poi1.agregarTag("mejor");
		poi1.agregarTag("colectivo");
		poi2.agregarTag("chocador");
		
		poiDto1 = PoiDTO.nuevoDesdePoi(poi1);
		poiDto2 = PoiDTO.nuevoDesdePoi(poi2);
				
		terminal.desactivarMails();
		terminal.desactivarRegistros();
		
		RepositorioBuscador.getInstance().agregarConsultora(local);
		
		Mapa.getInstance().alta(poi1);
		Mapa.getInstance().alta(poi2);
	}
	
	@After
	public void clear() {
		Reseter.resetSingletons();
		Reseter.resetDatastore(datastore);
	}
	
	@Test
	public void sePersisteElBancoExterno(){
		BancoExterno bancoExterno = new BancoExterno();
		bancoExterno.setBanco("galicia");
		String[] servicios = {"pago cheques", "consultas"};
		bancoExterno.setServicios(servicios);
		
		datastore.save(bancoExterno);
		
		Assert.assertFalse(datastore.createQuery(BancoExterno.class).asList().isEmpty());
	}
	
	@Test
	public void sePersistenLosServiciosDelBancoExterno(){
		BancoExterno bancoExterno = new BancoExterno();
		bancoExterno.setBanco("galicia");
		String[] servicios = {"pago cheques", "consultas"};
		bancoExterno.setServicios(servicios);
		
		datastore.save(bancoExterno);
		
		Assert.assertEquals(2, datastore.createQuery(BancoExterno.class).asList().get(0).getServicios().length,0);
	}
	
	
	@Test
	public void comprueboQueNoHayNadaPersistido() throws UnknownHostException{		
		Assert.assertTrue(datastore.createQuery(Busqueda.class).asList().isEmpty());
	}
	
	@Test 
	public void sePersisteLaBusqueda() throws UnknownHostException{
		
		List <PoiDTO> pois = new ArrayList<>();
		pois.add(poiDto1);
		
		Busqueda busqueda = new Busqueda("colectivo", pois);		
		datastore.save(busqueda);		
		
		Assert.assertFalse(datastore.createQuery(Busqueda.class).asList().isEmpty());
	}
	
	
	@Test 
	public void seRealizaUnaBusquedaYLuegoSePersiste(){
		buscador.buscarSegunTexto("colectivo",terminal);		

		Assert.assertEquals(1, datastore.createQuery(Busqueda.class).asList().size());
	}
			
	
	@Test
	public void sePersistenVariosPoisLocalesConLaBusqueda(){		
		poi2.agregarTag("colectivo");
		List<Poi> pois = buscador.buscarSegunTexto("colectivo", terminal);	
		
		long cantidadPois = datastore.find(Busqueda.class).get().getResultado().size();
			
		Assert.assertEquals(pois.size(),cantidadPois);
	}
	
	//Saque las queries de aca http://mongodb.github.io/morphia/1.0/guides/querying/
	
	@Test
	public void testDePrueba01(){
		datastore.save(poiDto1);
		PoiDTO poiEncontrado = datastore.createQuery(PoiDTO.class)
								.filter("nombre","107").asList().get(0);
		
		Assert.assertEquals(2, poiEncontrado.getTagsPoi().size(),0);
	}
	
	
	@Test
	public void testDePrueba02(){
		datastore.save(poiDto2);
		PoiDTO poiEncontra = datastore.createQuery(PoiDTO.class)
								.filter("nombre","106").asList().get(0);
		
		Assert.assertEquals(1, poiEncontra.getTagsPoi().size(),0);
	}
	
}
