package tpAnual.bd.persistencia.mongo;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.util.SingletonReseter;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;
public class TestPersistenciaBusquedaMongo {
	
	private static Datastore datastore;
	private Set<String> tags = new HashSet<String>();
	private PointWrapper ubicacion = new PointWrapper(54, 10);
	private Poi poi = new EstacionDeColectivo(ubicacion, "107", tags,0,"");
	
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		datastore = MongoDatastoreSingleton.getDatastore("busquedas");
	}
	
	@Before
	public void init() {
		SingletonReseter.resetAll();
	}
	
	@After
	public void clear() {
		SingletonReseter.resetAll();
	}
	
	@Test
	public void testDePrueba000000000000000000000000000000001(){
		datastore.save(poi);
	}
}
