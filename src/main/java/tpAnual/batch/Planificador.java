package tpAnual.batch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tpAnual.batch.procesos.Proceso;

public class Planificador{
	private List<HorarioProceso> horarios = new ArrayList<>();

	public void programarProceso(Proceso proceso,LocalDateTime fechaYhora){
		HorarioProceso horario = HorarioProceso.horarioEspecifico(proceso, fechaYhora);
		horarios.add(horario);
	}
	
	public void programarProcesoRutinario(Proceso proceso, LocalTime hora){
		HorarioProceso horario = HorarioProceso.horarioRutinario(proceso, hora);
		horarios.add(horario);
	}
		
	public void notificarLanzador(HorarioProceso horarioEjecucion) {
		Lanzador.getInstance().solicitudEjecucion(horarioEjecucion.getProceso());
	}
	
	public List<HorarioProceso> getHorarios(){
		return horarios;
	}
	
	public List<HorarioProceso> filtrarProcesos(LocalDateTime fechaYhora){
		return horarios.
						stream().
						filter(horario -> horario.tieneQueEjecutarse(fechaYhora)).
						collect(Collectors.toList());
	}
	
	public void mandarAEjecutar(LocalDateTime fechaYhora){
		List<HorarioProceso> horariosDisponibles = filtrarProcesos(fechaYhora);
		
		horariosDisponibles.forEach(horario -> notificarLanzador(horario));
	}
	
}