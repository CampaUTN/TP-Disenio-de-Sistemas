package tpAnual.util.bd.mongo;

import java.util.HashSet;
import java.util.Set;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import tpAnual.POIs.Poi;
import tpAnual.util.wrapper.PointWrapper;

@Entity
public class PoiDTO {
	
	@Embedded
	private PointWrapper ubicacion;
	
	@Embedded
	private Set<String> tags = new HashSet<String>();
	
	private String nombre;
	
	private String calle;
	private Integer direccion;

	
	private PoiDTO(){}
	
	public PoiDTO(PointWrapper ubicacion, String nombre, Set<String> tags, String calle, Integer direccion) {
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.tags = tags;
		this.calle = calle;
		this.direccion = direccion;
	}
	
	public static PoiDTO nuevoDesdePoi(Poi poi){
		return new PoiDTO(poi.getUbicacion(),poi.getNombre(),poi.getTags(),poi.getCalle(),poi.getDireccion());
	}

	public PointWrapper getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(PointWrapper ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Set<String> getTagsPoi() {
		return tags;
	}

	public void setTagsPoi(Set<String> tagsPoi) {
		this.tags = tagsPoi;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getDireccion() {
		return direccion;
	}

	public void setDireccion(Integer direccion) {
		this.direccion = direccion;
	}
}