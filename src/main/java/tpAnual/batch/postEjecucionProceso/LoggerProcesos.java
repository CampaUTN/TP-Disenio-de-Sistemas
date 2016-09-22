package tpAnual.batch.postEjecucionProceso;

import java.util.ArrayList;
import java.util.List;

import tpAnual.batch.ResultadoEjecucionProceso;
import tpAnual.batch.procesos.Proceso;

/**
 * Dado que tiene la lista de resultados, me importa que sea singleton.
 */
public class LoggerProcesos implements PostEjecucionProceso{
	private static LoggerProcesos instance = null;
	private List<ResultadoEjecucionProceso> resultados;
	
	private LoggerProcesos(){
		this.resultados = new ArrayList<ResultadoEjecucionProceso>();
	}
	
	public static LoggerProcesos getInstance(){
		if(instance==null){
			instance = new LoggerProcesos();
		}
		return instance;
	}
	
	public static void resetSingleton(){
	    instance = null;
	}
	

	@Override
	public void accionar(Proceso proceso) {
		resultados.add(new ResultadoEjecucionProceso(proceso));
	}

	
	// Setters & getters
	public List<ResultadoEjecucionProceso> getResultados() {
		return resultados;
	}
}
