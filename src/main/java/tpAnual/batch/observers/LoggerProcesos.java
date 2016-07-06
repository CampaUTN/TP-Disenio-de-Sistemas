package tpAnual.batch.observers;

import java.util.ArrayList;
import java.util.List;

import tpAnual.batch.ResultadoEjecucionProceso;
import tpAnual.batch.procesos.Proceso;

public class LoggerProcesos implements Accion{
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
