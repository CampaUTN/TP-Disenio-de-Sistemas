package tpAnual.bd.persistencia.mongo;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import tpAnual.externo.sistemasExternos.BancoDTO;
import tpAnual.util.Reseter;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;

public class TestPersistenciaBancoExternoMongo {
	private static Datastore datastore;
	
	private BancoDTO bancoExterno = new BancoDTO();
	private String[] servicios = {"pago cheques", "consultas"};
	
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		datastore = MongoDatastoreSingleton.getDatastore("BancoDTO");
	}
	
	@Before
	public void init(){
		bancoExterno.setBanco("galicia");
		bancoExterno.setServicios(servicios);
	}	
	
	@After
	public void clear() {
		Reseter.resetSingletons();
		Reseter.resetDatastore(datastore);
	}
	
	@Test
	public void sePersisteElBancoExterno(){		
		datastore.save(bancoExterno);
		
		Assert.assertFalse(datastore.createQuery(BancoDTO.class).asList().isEmpty());
	}
	
	@Test
	public void sePersistenLosServiciosDelBancoExterno(){
		datastore.save(bancoExterno);
		Assert.assertNotEquals(servicios, datastore.createQuery(BancoDTO.class).get().getServicios());
	}
}
