package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.List;

import tpAnual.POIs.Poi;



public class RepositorioNoPersistente extends RepositorioBusqueda {
	
	private static List<Busqueda> busquedas = new ArrayList<Busqueda>();
	private static RepositorioNoPersistente instance = null;
	

	private RepositorioNoPersistente() {}
	
	public static RepositorioNoPersistente getInstance(){
		if(instance==null){
			instance = new RepositorioNoPersistente();
		}
		return instance;
	}
	
	public static void resetSingleton(){
	    instance = null;
	}

	public void registrarBusqueda(String palabrasIngresadas, List<Poi> pois){
		Busqueda busqueda = new Busqueda(palabrasIngresadas,pois);
		busquedas.add(busqueda);
	}
}
