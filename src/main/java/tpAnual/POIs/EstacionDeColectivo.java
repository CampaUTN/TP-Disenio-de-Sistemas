package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.uqbar.geodds.Point;

public class EstacionDeColectivo extends Poi {
	private Integer linea;
	private String ramal;

	
	public EstacionDeColectivo(Point ubicacion, String nombre, Set<String> tags, int linea, String ramal) {
		super(ubicacion, nombre, tags);
		this.linea = linea;
		this.ramal = ramal;
	}
	
	public boolean estaDisponible(DayOfWeek dia,LocalTime hora) {
		return true;
	}

	@Override
	public boolean estaCerca(Point ubicacion) {
		return this.getUbicacion().distance(ubicacion) < 0.1; // 0.1 km = 100 m = 1 cuadra;
	}
	
	//Busqueda
	public boolean cumpleBusqueda(List<String> palabras){
		return palabras.stream()
				.anyMatch(palabra -> palabra.equalsIgnoreCase(linea.toString())
								  || palabra.equalsIgnoreCase(ramal));
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
}
