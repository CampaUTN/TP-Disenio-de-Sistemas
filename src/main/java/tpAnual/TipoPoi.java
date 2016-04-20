package tpAnual;

import java.time.DayOfWeek;
import org.uqbar.geodds.*;
import java.util.*;

public abstract class TipoPoi {

	public abstract boolean estaDisponible(DayOfWeek dia, String hora);

	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return unPunto.distance(puntoPoi) <= 0.5;
	}
	
	public Set<String> getServicios(){
		return new HashSet<String>();
	}

	public boolean estaDisponible(String servicio, DayOfWeek dia, String hora) {
		return false;
	}
	//Este metodo en un futuro se va a cambiar por un throwException
}