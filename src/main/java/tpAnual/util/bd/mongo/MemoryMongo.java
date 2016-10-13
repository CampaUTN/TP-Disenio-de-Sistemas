package tpAnual.util.bd.mongo;



import com.mongodb.DB;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class MemoryMongo {
	public static MongodStarter getDatastore(String nombreBD){
		try {
			return MemoryMongo.magia(nombreBD);
		} catch (Exception e) {
			return null;
		}
	}
    public static MongodStarter magia(String nombreBD) throws Exception {
    	MongodStarter starter = MongodStarter.getDefaultInstance();

        int port = 27017;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
            .version(Version.Main.PRODUCTION)
            .net(new Net(port, Network.localhostIsIPv6()))
            .build();

        MongodExecutable mongodExecutable = null;
        try {
            mongodExecutable = starter.prepare(mongodConfig);
            MongodProcess mongod = mongodExecutable.start();

            MongoClient mongo = new MongoClient("localhost", port);
            DB db = mongo.getDB(nombreBD);
//            DBCollection col = db.createCollection("testCol", new BasicDBObject());
//            col.save(new BasicDBObject("testDoc", new Date()));

        } finally {
            if (mongodExecutable != null)
                mongodExecutable.stop();
        }
        return starter;
    }
}
