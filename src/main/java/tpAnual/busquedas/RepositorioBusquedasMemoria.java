package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.List;
import tpAnual.POIs.Poi;

public class RepositorioBusquedasMemoria implements RepoBusqueda {
	
	private static List<BusquedaParaMemoria> busquedas = new ArrayList<BusquedaParaMemoria>();
	private static RepositorioBusquedasMemoria instance = null;
	

	private RepositorioBusquedasMemoria() {}
	
	public static RepositorioBusquedasMemoria getInstance(){
		if(instance==null){
			instance = new RepositorioBusquedasMemoria();
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
