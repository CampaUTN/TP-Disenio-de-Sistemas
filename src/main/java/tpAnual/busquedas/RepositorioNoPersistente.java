package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.List;

import tpAnual.POIs.Poi;

public class RepositorioNoPersistente implements RepositorioBusqueda {
	
	private static List<BusquedaParaMemoria> busquedas = new ArrayList<BusquedaParaMemoria>();
	private static RepositorioNoPersistente instance = null;
	

	private RepositorioNoPersistente() {}
	
	public static RepositorioNoPersistente getInstance(){
		if(instance==null){
			instance = new RepositorioNoPersistente();
		}
		return instance;
	}
	
	public static void resetSingleton(){
		busquedas.clear();
	    instance = null;
	}

	@Override
	public void registrarBusqueda(String palabrasIngresadas, List<Poi> poisDeTodosOrigenes) {
		BusquedaParaMemoria busqueda = new BusquedaParaMemoria(poisDeTodosOrigenes,palabrasIngresadas);
		busquedas.add(busqueda);
	}

	
}
