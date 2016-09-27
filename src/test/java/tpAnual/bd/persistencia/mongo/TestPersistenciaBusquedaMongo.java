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
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;


public class TestPersistenciaBusquedaMongo {
	
	private static Datastore datastore;
	private Set<String> tags = new HashSet<String>();
	private PointWrapper ubicacion = new PointWrapper(54, 10);
	private Poi poi1 = new EstacionDeColectivo(ubicacion, "107", tags,0,"");
	private Poi poi2 = new EstacionDeColectivo(ubicacion, "106", tags,0,"");
	Query<Poi> query = datastore.createQuery(Poi.class);
	
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		datastore = MongoDatastoreSingleton.getDatastore("busquedas");
	}
	
	@Before
	public void init() {
		SingletonReseter.resetAll();
		poi1.agregarTag("mejor");
		poi1.agregarTag("colectivo");
		poi2.agregarTag("chocador");
	}
	
	@After
	public void clear() {
		SingletonReseter.resetAll();
	}
	
	
	//Saque las queries de aca http://mongodb.github.io/morphia/1.0/guides/querying/
	
//	@Test
//	public void testDePrueba01(){
//		datastore.save(poi1);
//		Poi poiEncontrado = query.filter("id =","107").asList().get(0);
//		Assert.assertEquals(2, poiEncontrado.getTags().size(),0);
//		
//	}
//	
//	@Test
//	public void testDePrueba02(){
//		datastore.save(poi1);
//		Poi poiEncontrado = query.filter("tags =","chocador").asList().get(0);
//		Assert.assertEquals(1, poiEncontrado.getTags().size(),0);
//		
//	}
}
