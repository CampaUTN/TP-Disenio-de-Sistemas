package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.procesos.operaciones.Proceso;

public interface IPlanificador {
	
	public void programarProceso(Proceso proceso,LocalDate fecha,LocalTime hora);
	public void programarProcesoRutinario(Proceso proceso, LocalTime Fecha);
	public void tieneQueEjecutarse(Proceso proceso);

}