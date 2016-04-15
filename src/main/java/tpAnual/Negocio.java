package tpAnual;

import org.uqbar.geodds.*;

public class Negocio extends TipoPoi {

	private int radio;

	@Override
	public boolean estaDisponible() {
		// TODO
		return false;
	}

	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {

		Double distancia = puntoPoi.distance(unPunto);
		return distancia < radio;
	}

}
