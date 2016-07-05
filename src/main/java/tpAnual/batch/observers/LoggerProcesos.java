package tpAnual.batch.observers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import tpAnual.batch.ResultadoEjecucionProceso;
import tpAnual.batch.procesos.Proceso;

public class LoggerProcesos {
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
	
	private void generarResultado(Proceso proceso, boolean seEjecutoCorrectamente){
		resultados.add(new ResultadoEjecucionProceso(proceso.getNombre(), LocalDate.now(), LocalTime.now(), seEjecutoCorrectamente));
	}
	
	public void registrarEjecucionExitosa(Proceso proceso){
		this.generarResultado(proceso, true);
	}
	
	public void registrarEjecucionFallida(Proceso proceso){
		this.generarResultado(proceso, false);
	}

	
	// Setters & getters
	public List<ResultadoEjecucionProceso> getResultados() {
		return resultados;
	}
}
