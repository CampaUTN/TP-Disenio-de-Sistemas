package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tpAnual.POIs.Poi;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;

public class RepositorioPersistente extends RepositorioBusqueda implements WithGlobalEntityManager {
	
	private static Datastore datastore;
	private static RepositorioPersistente instance = null;
	

	private RepositorioPersistente() {}
	public static RepositorioPersistente getInstance(){
		if(instance==null){
			instance = new RepositorioPersistente();
		}
		return instance;
	}
	
	public static void resetSingleton(){
	    instance = null;
	}
	
	public void registrarBusqueda(String palabrasIngresadas, List<Poi> pois){
		
		MongoDatastoreSingleton.
			getDatastore("busquedas").
			save(new Busqueda(palabrasIngresadas,pois));
	}
	
	public List<Busqueda> getBusquedas(){
		List<Busqueda> busquedas = new ArrayList<Busqueda>();
//		busquedas = datastore.createQuery(Busqueda.class).asList();
		busquedas.add(new Busqueda("palabras1", null));
		busquedas.add(new Busqueda("palabras2", null));
		return busquedas;
	}
	
	public List<Busqueda> listar() {
		List<Busqueda> busquedas = new ArrayList<>();
		busquedas = datastore.find(Busqueda.class).asList();
		return busquedas;
	}
	
	public Busqueda buscarPorId(long id){
		return datastore.get(Busqueda.class, id);
	}
	
}
