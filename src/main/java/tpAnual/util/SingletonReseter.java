package tpAnual.util;

import tpAnual.Mapa;
import tpAnual.batch.Lanzador;
import tpAnual.batch.postEjecucionProceso.LoggerProcesos;
import tpAnual.busquedas.RepositorioBuscador;
import tpAnual.busquedas.RepositorioRegistros;

public class SingletonReseter {
	public static void resetAll(){
		Mapa.resetSingleton();
		RepositorioRegistros.resetSingleton();
		RepositorioBuscador.resetSingleton();
		LoggerProcesos.resetSingleton();
		Lanzador.resetSingleton();
	}
}
