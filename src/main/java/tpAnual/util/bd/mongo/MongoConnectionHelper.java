package tpAnual.util.bd.mongo;

import java.net.UnknownHostException;
import java.util.ResourceBundle;
import com.mongodb.MongoClient;

public class MongoConnectionHelper {

	private static MongoClient mongoSingleton = null;

	public static synchronized MongoClient getMongoClient() throws UnknownHostException {

		if (mongoSingleton == null) {

			synchronized (MongoConnectionHelper.class) {
				if (mongoSingleton == null) {

					ResourceBundle bundle = ResourceBundle.getBundle("mongodb");
					String host = bundle.getString("host");
					int port = Integer.parseInt(bundle.getString("port"));

					mongoSingleton = new MongoClient(host, port);
				}
			}
		}
		return mongoSingleton;
	}
}
