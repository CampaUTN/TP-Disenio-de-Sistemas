package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class Banco extends TipoPoi{

	private Set<Servicio> servicios = new HashSet<>();
	private Horario horarioAtencion;
	
	public Banco(){
		this.horarioAtencion = new Horario(DayOfWeek.MONDAY,DayOfWeek.FRIDAY,"10:00", "17:00");
		this.servicios = new HashSet<Servicio>();
	}
	
	// Disponibilidad sin nombre de servicio
		@Override
		public boolean estaDisponible(DayOfWeek dia,String hora) {
			LocalTime horaComp = LocalTime.parse(hora);
			return servicios.stream()
					.anyMatch(unServicio -> unServicio.disponible(dia, horaComp));
		}
		
		// Disponibilidad con nombre de servicio
		@Override
		public boolean estaDisponible(String nombreServ, DayOfWeek dia,String hora) {
			return servicios.stream()
					.anyMatch(unServicio -> unServicio.es(nombreServ) && this.estaDisponible(dia,hora));
		}
	
	// Servicios
	@Override
	public boolean brinda(String servicio){
		return servicios.contains(servicio);
	}
	
	public void agregarServicio(String servicio){
		servicios.add(servicio.toLowerCase());
	}
	
	// Getters:
	@Override
	public Set<String> getServicios(){
		return this.servicios;
	}
}
