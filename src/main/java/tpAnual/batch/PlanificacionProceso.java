package tpAnual.batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import tpAnual.batch.procesos.Proceso;

public class PlanificacionProceso {
	private Proceso proceso;
	private LocalTime hora;
	private LocalDate fecha;
	private boolean periodico;
	
	public Proceso getProceso() {
		return proceso;
	}
	
	public LocalTime getHora() {
		return hora;
	}	
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public boolean esPeriodico(){
		return periodico;
	}
	
	public boolean coincideFechaYhora(LocalDateTime fechaYhora){
		return fechaYhora.toLocalDate().equals(fecha) && 
				fechaYhora.toLocalTime().equals(hora);
	}
	
	public PlanificacionProceso(Proceso proceso,LocalDate fecha,LocalTime hora, boolean esPeriodico){
		this.proceso = proceso;
		this.hora = hora;
		this.fecha = fecha;
		this.periodico = esPeriodico;
	}
	
	public static PlanificacionProceso unicaVez(Proceso proceso,LocalDateTime fechaYhora) {
		return new PlanificacionProceso(proceso, fechaYhora.toLocalDate(), fechaYhora.toLocalTime(),false);
	}
	
	public static PlanificacionProceso periodico(Proceso proceso, LocalTime hora) {
		return new PlanificacionProceso(proceso,null, hora, true);
	}
	
	public boolean tieneQueEjecutarseAEstaHora(LocalTime hora){
		return this.hora.equals(hora) && esPeriodico();
	}
	
	public boolean tieneQueEjecutarseAEstaFechaYHora(LocalDateTime fechaYHora){
		boolean ret = false;
		 if(!esPeriodico()){
			 ret =  coincideFechaYhora(fechaYHora);
		 }
		 return ret;
	}
	
	public boolean tieneQueEjecutarse(LocalDateTime fechaYHora){
		return tieneQueEjecutarseAEstaHora(fechaYHora.toLocalTime()) || tieneQueEjecutarseAEstaFechaYHora(fechaYHora);
	}
}