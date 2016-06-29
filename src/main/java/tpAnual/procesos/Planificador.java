package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import tpAnual.procesos.operaciones.Proceso;

public class Planificador implements IPlanificador{
	private List<HorarioProceso> horarios = new ArrayList<>();

	public void programarProceso(Proceso proceso,LocalDate fecha,LocalTime hora){
		Lanzador.getInstance().agregaAPendientes(proceso);
	}
	
	public void programarProcesoRutinario(Proceso proceso, LocalTime hora){
		HorarioProceso horario = HorarioProceso.horarioRutinario(proceso, hora);
		horarios.add(horario);
	}
	
	public boolean tieneQueEjecutarse(HorarioProceso horario, LocalDate fecha, LocalTime hora) {
		return fecha.equals(horario.getFecha()) &&
				hora.equals(horario.getHora());
	}

	public void ejecutarAFechaYHora(HorarioProceso horario, LocalDate fecha, LocalTime hora) {
		if(tieneQueEjecutarse(horario, fecha, hora)){
			Lanzador.getInstance().agregaAPendientes(horario.getProceso());
		}
		
	}

}