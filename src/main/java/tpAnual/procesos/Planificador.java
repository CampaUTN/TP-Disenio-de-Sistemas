package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.procesos.operaciones.Proceso;

public class Planificador implements IPlanificador{

	private static Planificador instance = null;
	private Lanzador lanzador = Lanzador.getInstance();
	
	public void programarProceso(Proceso proceso,LocalDate fecha,LocalTime hora){
		lanzador.agregarProceso(proceso);
	}
	
	public void programarProcesoRutinario(Proceso proceso, LocalTime Fecha){
		
	}
	
	public void tieneQueEjecutarse(Proceso proceso){
	}
		
	private Planificador(){}

}