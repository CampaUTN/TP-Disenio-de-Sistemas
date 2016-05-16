package tpAnual;

import java.util.*;

import org.uqbar.geodds.*;

public class Cgp extends PoiConServicio {

	private Polygon comuna;

	public Cgp(List<Point> puntosComu){
		comuna = new Polygon(puntosComu); 
	}

	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return comuna.isInside(unPunto) && comuna.isInside(puntoPoi);
	}
	
}
