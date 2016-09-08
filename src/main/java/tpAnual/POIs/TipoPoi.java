package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.uqbar.geodds.*;
import java.util.*;

public abstract class TipoPoi {

	public abstract boolean estaDisponible(DayOfWeek dia, LocalTime hora);

	public abstract boolean cumpleBusqueda(List<String> palabras);
	
	public boolean estaCerca(Point ubicacion1, Point ubicacion2) {
		return ubicacion1.distance(ubicacion2) <= 0.5;
	}
	
	public Set<String> getServicios(){
		return new HashSet<String>();
	}

	//Este metodo en un futuro se va a cambiar por un throwException.
	public boolean estaDisponibleConServicio(String servicio, DayOfWeek dia, LocalTime hora) {
		return false;
	}
}