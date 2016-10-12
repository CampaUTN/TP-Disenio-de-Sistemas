package tpAnual.busquedas;

import java.util.List;

import tpAnual.POIs.Poi;


public abstract class RepositorioBusqueda {
	
	public abstract void registrarBusqueda(String palabrasIngresadas, List<Poi> poisDeTodosOrigenes);
	
}
