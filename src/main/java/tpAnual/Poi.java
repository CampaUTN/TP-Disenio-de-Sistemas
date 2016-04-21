package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.uqbar.geodds.Point;

public class Poi {
	private String nombre;
	private Point ubicacion;
	private TipoPoi tipo;
	private Set<String> tagsPoi = new HashSet<String>();
	@SuppressWarnings("unused")
	private String calle;
	@SuppressWarnings("unused")
	private Integer direccion;
	
	public Poi(TipoPoi tipo, Point ubicacion, String nombre, Set<String> tags) {
		this.tipo = tipo;
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.tagsPoi = tags;
		this.agregarTag(nombre);
	}

	// Disponibilidad:
	public boolean estaDisponible(LocalDate fecha,String hora) {
		DayOfWeek dia = fecha.getDayOfWeek();
		return tipo.estaDisponible(dia, hora);
	}

	public boolean estaDisponible(String servicio,LocalDate fecha,String hora) {
		DayOfWeek dia = fecha.getDayOfWeek();
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
				.anyMatch(tag -> tag.contains(clave.toLowerCase()));
	}

	public void agregarTag(String nuevoTag) {
		tagsPoi.add(nuevoTag.toLowerCase());
	}
	
	// Setters:
	public void agregarDireccion(String calle, Integer direccion){
		this.calle=calle;
		this.direccion=direccion;
		this.agregarTag(calle);
	}
	// Getters:
	public String getNombre(){
		return this.nombre;
	}
	
	private Set<String> getTags(){
		return Stream.concat(tagsPoi.stream(),tipo.getServicios().stream())
					 .collect(Collectors.toSet());
	}

	public Point getUbicacion() {
		return ubicacion;
	}
}
