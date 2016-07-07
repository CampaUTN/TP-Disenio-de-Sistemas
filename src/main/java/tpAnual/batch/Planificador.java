package tpAnual.batch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tpAnual.batch.procesos.Proceso;

public class Planificador{
	private List<PlanificacionProceso> horarios = new ArrayList<>();

	public void programarProceso(Proceso proceso,LocalDateTime fechaYhora){
		PlanificacionProceso horario = PlanificacionProceso.unicaVez(proceso, fechaYhora);
		horarios.add(horario);
	}
	
	public void programarProcesoRutinario(Proceso proceso, LocalTime hora){
		PlanificacionProceso horario = PlanificacionProceso.periodico(proceso, hora);
		horarios.add(horario);
	}
		
	public void notificarLanzador(PlanificacionProceso horarioEjecucion) {
		Lanzador.getInstance().solicitudEjecucion(horarioEjecucion.getProceso());
	}
	
	public List<PlanificacionProceso> getHorarios(){
		return horarios;
	}
	
	public List<PlanificacionProceso> filtrarProcesos(LocalDateTime fechaYhora){
		return horarios.
						stream().
						filter(horario -> horario.tieneQueEjecutarse(fechaYhora)).
						collect(Collectors.toList());
	}
	
	public void mandarAEjecutar(LocalDateTime fechaYhora){
		List<PlanificacionProceso> horariosDisponibles = filtrarProcesos(fechaYhora);
		
		horariosDisponibles.forEach(horario -> notificarLanzador(horario));
	}
	
}