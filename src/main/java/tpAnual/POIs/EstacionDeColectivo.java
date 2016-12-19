package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import tpAnual.util.wrapper.PointWrapper;

@Entity
@DiscriminatorValue("EstacionDeColectivo")
public class EstacionDeColectivo extends Poi {
	
	@Column(name = "cole_linea")
	private Integer linea;
	
	@Column(name = "cole_ramal")
	private String ramal;

	
	public EstacionDeColectivo(){super();}
	
	public EstacionDeColectivo(PointWrapper ubicacion, String nombre, Set<String> tags, int linea, String ramal) {
		super(ubicacion, nombre, tags);
		this.linea = linea;
		this.ramal = ramal;
	}
	
	public boolean estaDisponible(DayOfWeek dia,LocalTime hora) {
		return true;
	}

	@Override
	public boolean estaCerca(PointWrapper ubicacion) {
		return this.getUbicacion().distance(ubicacion) < 0.1; // 0.1 km = 100 m = 1 cuadra;
	}
	
	//Busqueda
	public boolean cumpleBusqueda(List<String> palabras){
		return palabras.stream()
				.anyMatch(palabra -> palabra.equalsIgnoreCase(linea.toString())
								  || palabra.equalsIgnoreCase(ramal));
	}

	//Getters y Setters
	public Integer getLinea() {
		return linea;
	}

	public String getRamal() {
		return ramal;
	}	
	
	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
}
