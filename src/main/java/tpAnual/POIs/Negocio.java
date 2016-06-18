package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.*;

import tpAnual.Horario;


public class Negocio extends TipoPoi {

	private int radio;
	@SuppressWarnings("unused") // Cada poi tiene un nombre y suponemos que
	// en se va a usar en una futura iteracion.
	private String nombre;
	private String rubro;
	private List <Horario> horarios;

	
	//Busqueda
	
	public boolean cumpleBusqueda(List<String> palabras){
		return palabras.stream()
				.anyMatch(palabra -> palabra == rubro);
	}
	
	//Disponibilidad
	@Override
	public boolean estaDisponible(DayOfWeek dia,LocalTime hora){
		return  horarios.stream()
				.anyMatch(horario -> horario.estaEnFranjaHoraria(dia,hora));
	}

	public void agregarHorario(Horario unHorario){
		horarios.add(unHorario);
	}
	
	//Cercania
	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return puntoPoi.distance(unPunto) < radio;
	}
	
	public Negocio(String rubro){
		this.rubro = rubro;
		horarios = new ArrayList<>();
	}

	public String getRubro() {
		return rubro;
	}
	
	
}
