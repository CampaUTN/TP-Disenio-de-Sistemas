package tpAnual.POIs;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.uqbar.geodds.Point;

import tpAnual.bd.PointToDoubleConverter;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Pois")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Poi {
	
	@Id @GeneratedValue
	//@Column(name = "poi_id")
	private long id;
	
//	@Column(name="Latitud _ Longitud")
//	@Convert(converter = PointToDoubleConverter.class)
	@Transient
	private Point ubicacion;
	
	@ElementCollection @Cascade({CascadeType.ALL})
	private Set<String> tagsPoi = new HashSet<String>();
	
	@Column(name = "poi_nombre")
	private String nombre;
	
	private String calle;
	private Integer direccion;

	protected Poi(){}
	
	public Poi(Point ubicacion, String nombre, Set<String> tags) {
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.tagsPoi = tags;
	}
	
	public boolean cumpleCondicionBusqueda(List<String> palabras){
		return this.tieneAlgunTag(palabras) || this.cumpleBusqueda(palabras); 
	}
	
	//saque el anterior porque era un pasamanos
	public abstract boolean estaDisponible(DayOfWeek dia, LocalTime hora);

	public boolean estaDisponibleConServicio(String servicio,LocalDate fecha,LocalTime hora) {
		DayOfWeek dia = fecha.getDayOfWeek();
		return this.estaDisponibleConServicio(servicio, dia, hora);
	}

	public boolean estaDisponibleConServicio(String servicio, DayOfWeek dia, LocalTime hora) {
		return false;
	}
	
		
	// Distancia:
	public boolean estaCerca(Point ubicacion) {
		return this.ubicacion.distance(ubicacion) <= 0.5;
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
		return tagsPoi;
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
	
	
	//agregado:
	public abstract boolean cumpleBusqueda(List<String> palabras);

	public Set<String> getServicios() {
		return new HashSet<String>();
	}
}