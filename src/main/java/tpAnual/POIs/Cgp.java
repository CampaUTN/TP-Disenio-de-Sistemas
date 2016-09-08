package tpAnual.POIs;

import java.util.*;

import javax.persistence.Transient;

import org.uqbar.geodds.*;

public class Cgp extends TipoPoiConServicio {

	@Transient //TODO: sacar y q la comuna sea un nro!
	private Polygon comuna;

	public Cgp(List<Point> puntosComu){
		comuna = new Polygon(puntosComu);
	}

	@Override
	public boolean estaCerca(Point ubicacion1, Point ubicacion2) {
		return comuna.isInside(ubicacion1) && comuna.isInside(ubicacion2);
	}
	
}
