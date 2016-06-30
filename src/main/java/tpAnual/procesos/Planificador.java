package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import tpAnual.procesos.operaciones.Proceso;

public class Planificador implements IPlanificador{
	private List<HorarioProceso> horarios = new ArrayList<>();

	public void programarProceso(Proceso proceso,LocalDate fecha,LocalTime hora){
		HorarioProceso horario = HorarioProceso.horarioEspecifico(proceso, fecha, hora);
		horarios.add(horario);
	}
	
	public void programarProcesoRutinario(Proceso proceso, LocalTime hora){
		HorarioProceso horario = HorarioProceso.horarioRutinario(proceso, hora);
		horarios.add(horario);
	}
	
	public boolean tieneQueEjecutarse(HorarioProceso horarioEjecucion, LocalDate fecha, LocalTime hora) {
		return fecha.equals(horarioEjecucion.getFecha()) &&
				hora.equals(horarioEjecucion.getHora());
	}

	
	public boolean tieneQueEjecutarseEnHora(HorarioProceso horarioEjecucion,LocalTime hora){
		return horarioEjecucion.getHora().equals(hora);
	}
	
	public void ejecutarAFechaYHora(HorarioProceso horarioEjecucion, LocalDate fecha, LocalTime hora) {
		if(tieneQueEjecutarse(horarioEjecucion, fecha, hora)){
			notificarLanzador(horarioEjecucion);
		}
		
	}	
	
	public void ejecutarEnHora(HorarioProceso horarioEjecucion,LocalTime hora) {
		if(tieneQueEjecutarseEnHora(horarioEjecucion, hora)){
			notificarLanzador(horarioEjecucion);
		}	
	}

	public void notificarLanzador(HorarioProceso horarioEjecucion) {
		Lanzador.getInstance().agregaAPendientes(horarioEjecucion.getProceso());
		
	}
	
	public List<HorarioProceso> getHorarios(){
		return horarios;
	}
	
	
}