package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.procesos.operaciones.Proceso;

public interface IPlanificador {

	public void programarProceso(Proceso proceso,LocalDate fecha,LocalTime hora, int veces);
	public void ejecutarProceso(Proceso proceso);

}