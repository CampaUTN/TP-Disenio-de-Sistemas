package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import tpAnual.util.wrapper.PointWrapper;

@Entity
@Table(name="Poi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Poi {
	
	@Id @GeneratedValue
	@Column(name = "poi_id", unique = true)
	private long id;
	
	@Embedded
	private PointWrapper ubicacion;
	
	@ElementCollection @Cascade({CascadeType.ALL})
	private Set<String> tags = new HashSet<String>();
	
	@Column(name = "poi_nombre")
	private String nombre;
	
	private String calle;
	private Integer direccion;

	protected Poi(){}
	
	public Poi(PointWrapper ubicacion, String nombre, Set<String> tags) {
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.tags = tags;
	}
	
	public boolean cumpleCondicionBusqueda(List<String> palabras){
		return this.tieneAlgunTag(palabras) || this.cumpleBusqueda(palabras); 
	}
	
	public abstract boolean estaDisponible(DayOfWeek dia, LocalTime hora);

	public boolean estaDisponibleConServicio(String servicio,LocalDate fecha,LocalTime hora) {
		DayOfWeek dia = fecha.getDayOfWeek();
		return this.estaDisponibleConServicio(servicio, dia, hora);
	}

	public boolean estaDisponibleConServicio(String servicio, DayOfWeek dia, LocalTime hora) {
		return false;
	}
	
	public abstract boolean cumpleBusqueda(List<String> palabras);
	
		
	// Distancia:
	public boolean estaCerca(PointWrapper ubicacion) {
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
		tags.add(nuevoTag.toLowerCase());
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
	
	public void setUbicacion(PointWrapper ubicacion){
		this.ubicacion = ubicacion;
	}
	
	// Getters:
	public String getNombre(){
		return this.nombre;
	}
	
	public PointWrapper getUbicacion() {
		return ubicacion;
	}

	public long getId(){
		return id;
	}

	public boolean esDeId(long id) {
		return this.id==id;
	}

	public Set<String> getTags() {
		return tags;
	}

	public String getCalle() {
		return calle;
	}

	public Integer getDireccion() {
		return direccion;
	}

	public void setId(long id) {
		this.id = id;
	}	

	public Set<String> getServicios() {
		return new HashSet<String>();
	}

	public void setTags(Set<String> tagsPoi) {
		this.tags = tagsPoi;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setDireccion(Integer direccion) {
		this.direccion = direccion;
	}

	public void removerTags() {
		this.tags.removeAll(tags);
	}
}