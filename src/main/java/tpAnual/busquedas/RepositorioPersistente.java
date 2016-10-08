package tpAnual.busquedas;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.POIs.Poi;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.bd.mongo.PoiDTO;

public class RepositorioPersistente implements RepositorioBusqueda {
	
public void registrarBusqueda(String palabrasIngresadas, List<Poi> poisDeTodosOrigenes){
		
		List <PoiDTO> pois = poisDeTodosOrigenes.stream()
														.map( poi -> PoiDTO.nuevoDesdePoi(poi))
														.collect(Collectors.toList());
		
		Busqueda busqueda = new Busqueda(palabrasIngresadas,pois);

		MongoDatastoreSingleton.getDatastore("busquedas").save(busqueda);
	}
}
