package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.procesos.operaciones.Proceso;

public class HorarioProceso {
	private Proceso proceso;
	private LocalTime hora;
	private LocalDate fecha;
	
	
	public Proceso getProceso() {
		return proceso;
	}
	
	public LocalTime getHora() {
		return hora;
	}	
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public HorarioProceso(Proceso proceso,LocalDate fecha,LocalTime hora){
		this.proceso = proceso;
		this.hora = hora;
		this.fecha = fecha;
	}
	public static HorarioProceso horarioRutinario(Proceso proceso, LocalTime hora) {
		return new HorarioProceso(proceso, null, hora);
	}

}

