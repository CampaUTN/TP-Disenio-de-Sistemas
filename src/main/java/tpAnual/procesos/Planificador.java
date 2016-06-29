package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.procesos.operaciones.Proceso;

public class Planificador implements IPlanificador{
	
	public void programarProceso(Proceso proceso,LocalDate fecha,LocalTime hora){
		Lanzador.getInstance().agregarProceso(proceso);
	}
	
	public void programarProcesoRutinario(Proceso proceso, LocalTime Fecha){
		
	}
	
	public void tieneQueEjecutarse(Proceso proceso){
	}
		
	private Planificador(){}

}