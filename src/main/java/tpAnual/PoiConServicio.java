package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class PoiConServicio extends TipoPoi{
	protected Set<Servicio> servicios = new HashSet<>();
	
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
		LocalTime horaComp = LocalTime.parse(hora);
		return servicios.stream()
				.anyMatch(unServicio -> unServicio.tienePorNombre(nombreServ) && unServicio.disponible(dia,horaComp));
	}
	
	// Getters:
	@Override
	public Set<String> getServicios(){
		return servicios.stream()
						.map(servicio -> servicio.getNombre())
						.collect(Collectors.toSet());
	}
	
	// Otros
	public void agregarServicio(Servicio servicio){
		servicios.add(servicio);
	}
	
}	
