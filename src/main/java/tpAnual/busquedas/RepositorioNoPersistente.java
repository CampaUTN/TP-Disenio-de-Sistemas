package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.List;

import tpAnual.util.bd.mongo.PoiDTO;

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

	protected void guardar(String palabrasIngresadas, List<PoiDTO> pois){
		Busqueda busqueda = new Busqueda(palabrasIngresadas,pois);
		busquedas.add(busqueda);
	}
}
