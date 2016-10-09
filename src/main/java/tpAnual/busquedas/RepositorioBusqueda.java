package tpAnual.busquedas;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.POIs.Poi;
import tpAnual.util.bd.mongo.PoiDTO;

public abstract class RepositorioBusqueda {
	public void registrarBusqueda(String palabrasIngresadas, List<Poi> poisDeTodosOrigenes){
		this.guardar(palabrasIngresadas, this.convertir(poisDeTodosOrigenes));
	}
	
	protected abstract void guardar(String palabrasIngresadas, List<PoiDTO> pois);
	
	private List<PoiDTO> convertir(List<Poi> poisDeTodosOrigenes){
		return poisDeTodosOrigenes.stream()
									.map(poi -> PoiDTO.nuevoDesdePoi(poi))
									.collect(Collectors.toList());
	}
}
