package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToMany;

import org.uqbar.geodds.*;

import tpAnual.Horario;


public class Negocio extends Poi {

	public Negocio(Point ubicacion, String nombre, Set<String> tags, String rubro, int radioCercania) {
		super(ubicacion, nombre, tags);
		this.rubro = rubro;
		horarios = new ArrayList<>();
		this.radioCercania = radioCercania;
	}

	private int radioCercania = 5;
	private String rubro;
	
	@ManyToMany
	private List <Horario> horarios;

	
	//Busqueda
	
	public boolean cumpleBusqueda(List<String> palabras){
		return palabras.stream()
				.anyMatch(palabra -> palabra.equalsIgnoreCase(rubro));
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
	public boolean estaCerca(Point ubicacion) {
		return this.getUbicacion().distance(ubicacion) < radioCercania;
	}

	public String getRubro() {
		return rubro;
	}
}
