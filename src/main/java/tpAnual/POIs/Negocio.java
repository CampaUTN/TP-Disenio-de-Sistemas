package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.ManyToMany;

import org.uqbar.geodds.*;

import tpAnual.Horario;


public class Negocio extends TipoPoi {

	private int radio;
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
	public boolean estaCerca(Point ubicacion1, Point ubicacion2) {
		return ubicacion1.distance(ubicacion2) < radio;
	}
	
	public Negocio(String rubro){
		this.rubro = rubro;
		horarios = new ArrayList<>();
	}

	public String getRubro() {
		return rubro;
	}
	
	public void setRadio(int radio){
		this.radio = radio;
	}
	
}
