package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.uqbar.geodds.Point;

import tpAnual.POIs.TipoPoi;

public class EstacionDeColectivo extends TipoPoi {
	
	private String nroColectivo;
	
	@Override
	public boolean estaDisponible(DayOfWeek dia,LocalTime hora) {
		return true;
	}

	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return unPunto.distance(puntoPoi) < 0.1; // 0.1 km = 100 m = 1 cuadra;
	}
	
	//Busqueda
	
	public boolean cumpleBusqueda(List<String> palabras){
		return palabras.stream()
				.anyMatch(palabra -> palabra == nroColectivo);
	}
}
