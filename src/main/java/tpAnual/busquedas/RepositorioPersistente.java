package tpAnual.busquedas;

import java.util.List;

import tpAnual.POIs.Poi;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;


public class RepositorioPersistente extends RepositorioBusqueda {
	protected void guardar(String palabrasIngresadas, List<Poi> pois){
		MongoDatastoreSingleton.getDatastore("busquedas").save(new Busqueda(palabrasIngresadas,pois));
	}
}
