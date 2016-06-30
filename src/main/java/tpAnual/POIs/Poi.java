package tpAnual.POIs;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tpAnual.Mapa;
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
	private Integer id;
	
	public Poi(TipoPoi tipo, Point ubicacion, String nombre, Set<String> tags) {
		this.tipo = tipo;
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.tagsPoi = tags;
		this.id= Mapa.getInstance().getNextId();
	}
	
	public boolean cumpleCondicionBusqueda(List<String> palabras){
		return this.tieneAlgunTag(palabras) || tipo.cumpleBusqueda(palabras); 
	}

	// Disponibilidad:
	public boolean estaDisponible(LocalDate fecha,LocalTime hora) {
		DayOfWeek dia = fecha.getDayOfWeek();
		return tipo.estaDisponible(dia, hora);
	}

	public boolean estaDisponibleConServicio(String servicio,LocalDate fecha,LocalTime hora) {
		DayOfWeek dia = fecha.getDayOfWeek();
		return tipo.estaDisponibleConServicio(servicio, dia, hora);
	}

	// Distancia:
	public boolean estaCerca(Point unPunto) {
		return tipo.estaCerca(unPunto, this.ubicacion);
	}
	
	// Tags:
	
	public boolean tieneAlgunTag(List<String> palabras){
		return palabras.stream()
				.anyMatch(palabra -> this.tieneTag(palabra));
	}
	
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
	
	public void setNombre(String nuevoNombre){
		this.nombre = nuevoNombre;
	}
	
	public void setUbicacion(Point nuevaUbic){
		this.ubicacion = nuevaUbic;
	}
	// Getters:
	public String getNombre(){
		return this.nombre;
	}
	
	public Set<String> getTags(){
		return Stream.concat(tagsPoi.stream(),tipo.getServicios().stream())
					 .collect(Collectors.toSet());
	}

	public Point getUbicacion() {
		return ubicacion;
	}

	public void cambiarTags(Set<String> palabrasClave) {
		this.tagsPoi = palabrasClave;
		
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}

}