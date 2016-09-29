package tpAnual.externo.sistemasExternos;

import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;

import tpAnual.POIs.Poi;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;

public abstract class Buscador implements Consultora {
	
	protected String base;
	
	protected Datastore getDataBase(){
		return MongoDatastoreSingleton.getDatastore(base);
	}
	public abstract List<Poi> consultar(List<String> palabras);
	
	public abstract List<Poi> getPois();
	
	protected void actualizarTodos() {
		this.getPois()
			.stream()
			.collect(Collectors.toSet())
			.forEach(poi -> MongoDatastoreSingleton.getDatastore(base).save(poi));;
	}
}
