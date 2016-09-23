package tpAnual.util.bd.mongo;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MongoDatastoreSingleton {

	private static MongoClient mongoSingleton = null;
	private static Morphia morphiaSingleton = null;
	private static Datastore datastoreSingleton = null;
	
	private static MongoClient getMongoClient(String nombreBD) throws UnknownHostException {
		if (mongoSingleton == null) {
			synchronized (MongoDatastoreSingleton.class) {
				if (mongoSingleton == null) {
					ResourceBundle bundle = ResourceBundle.getBundle(nombreBD);
					String host = bundle.getString("host");
					int port = Integer.parseInt(bundle.getString("port"));

					mongoSingleton = new MongoClient(host, port);
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
	
	public static Datastore getDatastore(String nombreBD) throws UnknownHostException{
		if(datastoreSingleton==null){
			datastoreSingleton = getMorphia().createDatastore(getMongoClient(nombreBD), nombreBD);
		}
		return datastoreSingleton;
	}
}
