package tpAnual.busquedas;

import java.util.List;

import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.bd.mongo.PoiDTO;

public class RepositorioPersistente extends RepositorioBusqueda {
	protected void guardar(String palabrasIngresadas, List<PoiDTO> pois){
		MongoDatastoreSingleton.getDatastore("busquedas").save(new Busqueda(palabrasIngresadas,pois));
	}
}
