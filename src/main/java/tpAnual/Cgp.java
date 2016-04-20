package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.*;

import org.uqbar.geodds.*;

public class Cgp extends TipoPoi {

	private Polygon comuna;
	private Set<Servicio> servicios = new HashSet<>();
	
	// Constructor
	public Cgp(List<Point> puntosComu){
		comuna = new Polygon(puntosComu); 
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
		LocalTime horaComp = LocalTime.parse(hora);
		return servicios.stream()
				.anyMatch(unServicio -> unServicio.es(nombreServ) && unServicio.disponible(dia,horaComp));
	}
	
		
	// Cercania
	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return comuna.isInside(unPunto) && comuna.isInside(puntoPoi);
	}
	
	// Otros
	public void agregarServicio(Servicio servicio){
		servicios.add(servicio);
	}
	
	// Getters:
	@Override
	public Set<String> getServicios(){
		return servicios.stream()
				.map(unServicio -> unServicio.getNombre())
				.collect(Collectors.toSet());
	}
}
