package tpAnual.bd.persistencia.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import tpAnual.externo.sistemasExternos.CentroDTO;
import tpAnual.externo.sistemasExternos.ServicioDTO;
import tpAnual.util.Reseter;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;

public class TestPersistenciaCentroDTOMongo {
	
	private static Datastore datastore;
	
	private CentroDTO centro = new CentroDTO();
	List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
	private ServicioDTO unServicio = new ServicioDTO("consultas", 3, 10, 00, 18, 00);
	
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		datastore = MongoDatastoreSingleton.getDatastore("busquedas");
	}
	
	@Before
	public void init(){
		servicios.add(unServicio);
		centro.setServicios(servicios);
		servicios.add(unServicio);
		
	}
	
	@After
	public void clear() {
		Reseter.resetSingletons();
		Reseter.resetDatastore(datastore);
	}
	
	@Test
	public void sePersisteCentroDTO(){
		
		centro.setServicios(servicios);
		
		datastore.save(centro);

		Assert.assertFalse(datastore.createQuery(CentroDTO.class).asList().isEmpty());
	}
	
	@Test
	public void sePersistenLosServiciosDeCentroDTO(){
		CentroDTO centro = new CentroDTO();
		List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
		ServicioDTO unServicio = new ServicioDTO("consultas", 3, 10, 00, 18, 00);
		servicios.add(unServicio);
		centro.setServicios(servicios);
		
		datastore.save(centro);

		Assert.assertNotEquals(0, datastore.createQuery(CentroDTO.class).asList().get(0).getServicios().size(),0);
	}
}
