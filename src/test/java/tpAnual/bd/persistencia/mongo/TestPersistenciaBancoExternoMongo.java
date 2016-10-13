package tpAnual.bd.persistencia.mongo;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;

import tpAnual.externo.sistemasExternos.BancoDTO;
import tpAnual.util.Reseter;

public class TestPersistenciaBancoExternoMongo {
	private static Datastore datastore;
	
	DB db = new Fongo("Test").getDB("Database");
    Jongo jongo = new Jongo(db);

    MongoCollection collection = jongo.getCollection("BancoDTO");

	
	private BancoDTO bancoExterno = new BancoDTO();
	private String[] servicios = {"pago cheques", "consultas"};
	
	@BeforeClass
	public static void initClass() throws UnknownHostException{
		//datastore = MongoDatastoreSingleton.getDatastore("BancoDTO");
	}
	
	@Before
	public void init(){
		bancoExterno.setBanco("galicia");
		bancoExterno.setServicios(servicios);
	}	
	
	@After
	public void clear() {
		Reseter.resetSingletons();
		collection.drop();
		//Reseter.resetDatastore(datastore);
	}
	
	@Test
	public void sePersisteElBancoExterno(){		
		collection.save(bancoExterno);

		Assert.assertFalse(collection.find("{name: #}", "Sheldon").as(BancoDTO.class).isEmpty());
	}
	
	@Test
	public void sePersistenLosServiciosDelBancoExterno(){
		collection.save(bancoExterno);
		Assert.assertNotEquals(servicios, collection.find("{name: #}", "Sheldon").as(BancoDTO.class));
	}
}
