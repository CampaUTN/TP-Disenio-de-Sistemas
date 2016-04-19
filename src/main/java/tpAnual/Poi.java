package tpAnual;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.uqbar.geodds.Point;

public class Poi {
	private String nombre;
	private Point ubicacion;
	private TipoPoi tipo;
	private Set<String> tagsPoi = new HashSet<String>();

	public Poi(TipoPoi tipo, Point ubicacion, String nombre, Set<String> tags) {
		this.tipo = tipo;
		this.ubicacion=ubicacion;
		this.nombre = nombre;
		this.tagsPoi = tags;
		tags.add(nombre);
	}

	// Disponibilidad:
	public boolean estaDisponible(DayOfWeek dia,String hora) {
		return tipo.estaDisponible(dia, hora);
	}

	public boolean estaDisponible(String servicio,DayOfWeek dia,String hora) {
		return tipo.estaDisponible(servicio, dia, hora);
	}

	// Distancia:
	public boolean estaCerca(Point unPunto) {
		return tipo.estaCerca(unPunto, this.ubicacion);
	}

	// Tags:
	public boolean tieneTag(String clave) {
		return this.getTags()
				.stream()
				.anyMatch(tag -> tag.toLowerCase().contains(clave.toLowerCase()));
	}

	public void agregarTag(String nuevoTag) {
		tagsPoi.add(nuevoTag);
	}
	
	// Getters:
	public String getNombre(){
		return this.nombre;
	}
	
	private Set<String> getTags(){
		return Stream.concat(tagsPoi.stream(),tipo.getSerivicios().stream())
				.collect(Collectors.toSet());
	}
}
