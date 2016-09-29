package tpAnual.util.bd.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MongoDatastoreSingleton {

	private static MongoClient mongoSingleton = null;
	private static Morphia morphiaSingleton = null;
	private static Datastore datastoreSingleton = null;
	
	private static MongoClient getMongoClient(String nombreBD) {
		if (mongoSingleton == null) {
			synchronized (MongoDatastoreSingleton.class) {
				if (mongoSingleton == null) {
					mongoSingleton = new MongoClient("localhost", 27017);
				}
			}
		}
		return mongoSingleton;
	}
	
	private static Morphia getMorphia(){
		if (morphiaSingleton==null){
			morphiaSingleton = new Morphia();
		}
		return morphiaSingleton;
	}
	
	public static Datastore getDatastore(String nombreBD){
		if(datastoreSingleton==null){
			datastoreSingleton = getMorphia().createDatastore(getMongoClient(nombreBD), nombreBD);
		}
		return datastoreSingleton;
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static Query getQueryFor(String database, Class clase){
//		return MongoDatastoreSingleton.getDatastore(database)
//				.createQuery(clase);
//	}
}
