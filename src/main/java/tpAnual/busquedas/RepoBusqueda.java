package tpAnual.busquedas;

import java.util.List;

import tpAnual.POIs.Poi;

public interface RepoBusqueda {
	
	void registrarBusqueda(String palabrasIngresadas, List<Poi> poisDeTodosOrigenes);

}
