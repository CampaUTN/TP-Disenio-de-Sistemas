package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class Banco extends TipoPoi{

	private Set<String> servicios;
	private Horario horarioAtencion;
	
	public Banco(){
		this.horarioAtencion = new Horario(DayOfWeek.MONDAY,DayOfWeek.FRIDAY,"10:00", "17:00");
		this.servicios = new HashSet<String>();
	}
	
	// Disponibilidad
	
	public boolean estaDisponible(DayOfWeek dia,String hora) {
		return horarioAtencion.estaEnFranjaHoraria(dia, LocalTime.parse(hora));
	}
	
	public boolean estaDisponible(String nombreServ,DayOfWeek dia,String hora){
		return this.brinda(nombreServ) && this.estaDisponible(dia,hora);
	}
	
	// Servicios
	
	public boolean brinda(String servicio){
		return servicios.contains(servicio);
	}
	
	public void agregarServicio(String servicio){
		servicios.add(servicio);
	}
	
	// Getters:
	public Set<String> getServicios(){
		return this.servicios;
	}
}
