package tpAnual.bd.persistencia.mongo;

import java.net.UnknownHostException;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.util.SingletonReseter;
import tpAnual.util.bd.PoiDTO;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;


public class TestPersistenciaBusquedaMongo {
	
//	private static Datastore datastore = morphia.createDatastore(mongo, DB_Name);

	private static Datastore datastore;
	
	private Set<String> tags1 = new HashSet<String>();
	private Set<String> tags2 = new HashSet<String>();
	private PointWrapper ubicacion = new PointWrapper(54, 10);
	
	private Poi poi1;
	private Poi poi2;
	private PoiDTO poiDto1;
	private PoiDTO poiDto2;
	
//	Query<PoiDTO> query = datastore.createQuery(PoiDTO.class);
	
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		datastore = MongoDatastoreSingleton.getDatastore("busquedas");
	}
	
	@Before
	public void init() {
		SingletonReseter.resetAll();
		
		poi1 = new EstacionDeColectivo(ubicacion, "107", tags1,0,"");
		poi2 = new EstacionDeColectivo(ubicacion, "106", tags2,0,"");
		
		poi1.removerTags();
		poi2.removerTags();
		
		poi1.agregarTag("mejor");
		poi1.agregarTag("colectivo");
		poi2.agregarTag("chocador");
		
		poiDto1 = PoiDTO.nuevoDesdePoi(poi1);
		poiDto2 = PoiDTO.nuevoDesdePoi(poi2);
	}
	
	@After
	public void clear() {
		SingletonReseter.resetAll();
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
