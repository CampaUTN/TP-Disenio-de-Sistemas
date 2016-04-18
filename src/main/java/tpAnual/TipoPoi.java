package tpAnual;

import java.time.DayOfWeek;

import org.uqbar.geodds.*;

public abstract class TipoPoi {

	public abstract boolean estaDisponible(DayOfWeek dia,String hora);

	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return unPunto.distance(puntoPoi) <= 0.5;
	}

	public boolean brinda(String servicio) {
		return false;
	}

	public boolean estaDisponible(String servicio,DayOfWeek dia,String hora) {
		// como banco y GCP manejan distinto los tramites, implementan de
		// diferente forma este metodo
		// entoces cada una de esas subclases lo redefine a gusto. Las demas,
		// por default, tiran excepcion, porque no tienen tramites.
		return false; // TODO
	}

}
