package tpAnual.util.bd.mongo;

import java.net.UnknownHostException;

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
					try {
						mongoSingleton = new MongoClient("localhost", 27017);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
}
