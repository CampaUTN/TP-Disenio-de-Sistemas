package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.uqbar.geodds.Point;

public class EstacionDeColectivo extends TipoPoi {
	
	private Integer linea;
	private String ramal;
	
	public EstacionDeColectivo(int linea, String ramal) {
		this.linea = linea;
		this.ramal = ramal;
	}

	public boolean estaDisponible(DayOfWeek dia,LocalTime hora) {
		return true;
	}

	@Override
	public boolean estaCerca(Point ubicacion1, Point ubicacion2) {
		return ubicacion1.distance(ubicacion2) < 0.1; // 0.1 km = 100 m = 1 cuadra;
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
