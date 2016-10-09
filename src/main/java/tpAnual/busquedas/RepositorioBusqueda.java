package tpAnual.busquedas;

import java.util.List;

import tpAnual.POIs.Poi;


public abstract class RepositorioBusqueda {
	public void registrarBusqueda(String palabrasIngresadas, List<Poi> poisDeTodosOrigenes){
		this.guardar(palabrasIngresadas, this.convertir(poisDeTodosOrigenes));
	}
	
	protected abstract void guardar(String palabrasIngresadas, List<Poi> pois);
	
	private List<Poi> convertir(List<Poi> poisDeTodosOrigenes){
		return poisDeTodosOrigenes;
	}
}
