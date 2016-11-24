package tpAnual.busquedas;

import java.util.List;



import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.BancoDTO;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import org.mongodb.morphia.Datastore;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioPersistente extends RepositorioBusqueda implements WithGlobalEntityManager {
	
	private static Datastore datastore;
	
	public void registrarBusqueda(String palabrasIngresadas, List<Poi> pois){
		
		MongoDatastoreSingleton.
			getDatastore("busquedas").
			save(new Busqueda(palabrasIngresadas,pois));
	}
	
	public List<Busqueda> listar(String fechaDesde, String fechaHasta) {
		List<Busqueda> busquedas = datastore.createQuery(Busqueda.class).asList();
		return busquedas;
	}
	
	
}
