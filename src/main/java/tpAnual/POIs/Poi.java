package tpAnual.POIs;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.uqbar.geodds.Point;

//Imports para BD
import tpAnual.bd.PointToDoubleConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Poi {
	@Id @GeneratedValue
	private long id;
	
	@Column(name="Latitud _ Longitud")
	@Convert(converter = PointToDoubleConverter.class)
	private Point ubicacion;
	
	@Transient
	private TipoPoi tipo;
	
	@ElementCollection
	private Set<String> tagsPoi = new HashSet<String>();
	
	private String nombre;
	private String calle;
	private Integer direccion;

	//Es necesario el constructor vacio.
	@SuppressWarnings("unused")
	private Poi(){}
	
	public Poi(TipoPoi tipo, Point ubicacion, String nombre, Set<String> tags) {
		this.tipo = tipo;
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.tagsPoi = tags;
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
	public boolean estaCerca(Point ubicacion) {
		return tipo.estaCerca(this.ubicacion, ubicacion);
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

	public void setTags(Set<String> palabrasClave) {
		this.tagsPoi = palabrasClave;
		
	}
	
	public long getId(){
		return id;
	}

	public boolean esDeId(long id) {
		return this.id==id;
	}

	public TipoPoi getTipo() {
		return tipo;
	}

	public Set<String> getTagsPoi() {
		return tagsPoi;
	}

	public String getCalle() {
		return calle;
	}

	public Integer getDireccion() {
		return direccion;
	}

	// TODO: solo para testear, habria que sacarlo y usar las IDs autogeneradas
	public void setId(long id) {
		this.id = id;
	}

}