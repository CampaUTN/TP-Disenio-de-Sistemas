package tpAnual;

import org.uqbar.geodds.Point;

public class Colectivo extends TipoPoi {

	@Override
	public boolean estaDisponible() {
		return true;
	}

	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return unPunto.distance(puntoPoi) < 0.1; // 0.1 km = 100 m = 1 cuadra;
	}

}
