package tpAnual.busquedas;

import java.util.List;


import tpAnual.POIs.Poi;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioPersistente extends RepositorioBusqueda implements WithGlobalEntityManager {
	
	public void registrarBusqueda(String palabrasIngresadas, List<Poi> pois){
		
		MongoDatastoreSingleton.
			getDatastore("busquedas").
			save(new Busqueda(palabrasIngresadas,pois));
	}
	
	public List<Busqueda> listar() {
		return entityManager().createQuery("from Busqueda", Busqueda.class)
		.getResultList();
	}
	
	
}
