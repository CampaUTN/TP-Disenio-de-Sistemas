package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.*;

import org.uqbar.geodds.*;

public class Cgp extends TipoPoi {

	private Polygon comuna;
	private List<Servicio> servicios = new ArrayList<>();
	
	// Constructor
	public Cgp(List<Point> puntosComu){ //Pedimos una lista de n puntos al constructor para definir la comuna
		comuna = new Polygon(puntosComu); 
	}

	// Disponibilidad 
	public boolean estaDisponible(DayOfWeek dia,String hora) {
		LocalTime horaComp = LocalTime.parse(hora);
		return servicios.stream()
				.anyMatch(unServicio -> unServicio.disponible(dia, horaComp));
	}//No se ingresa el nombre del servicio y se verifica que haya al menos uno abierto
	
	
	public boolean estaDisponible(String nombreServ, DayOfWeek dia,String hora) {
		LocalTime horaComp = LocalTime.parse(hora);
		return servicios.stream()
				.anyMatch(unServicio -> unServicio.es(nombreServ) && unServicio.disponible(dia,horaComp));
	}//Se ingresa el nombre del servicio y se verifica la disponibilidad del mismo
	
		
	// Cercania
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return comuna.isInside(puntoPoi) && comuna.isInside(unPunto);
	}
	
	// Otros
	public void agregarServicio(Servicio servicio){
		servicios.add(servicio);
	}
	
	// Getters:
	public Set<String> getServicios(){
		return servicios.stream()
				.map(unServicio -> unServicio.getNombre())
				.collect(Collectors.toSet());
	}
}
