package tpAnual.util;

import org.mongodb.morphia.Datastore;

import tpAnual.Mapa;
import tpAnual.batch.Lanzador;
import tpAnual.batch.postEjecucionProceso.LoggerProcesos;
import tpAnual.busquedas.RepositorioBuscadores;
import tpAnual.busquedas.RepositorioRegistros;

public class Reseter {
	public static void resetSingletons(){
		//Mapa.resetSingleton();
		RepositorioRegistros.resetSingleton();
		RepositorioBuscadores.resetSingleton();
		LoggerProcesos.resetSingleton();
		Lanzador.resetSingleton();
	}

	public static void resetDatastore(Datastore datastore) {
		datastore.getDB().dropDatabase();	
	}
}
