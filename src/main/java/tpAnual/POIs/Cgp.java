package tpAnual.POIs;

import java.util.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import tpAnual.util.wrapper.PointWrapper;
import tpAnual.util.wrapper.PolygonWrapper;

@Entity
public class Cgp extends PoiConServicios {
	
	@Embedded
	private PolygonWrapper comuna;

	public Cgp(){super();}
	
	public Cgp(PointWrapper ubicacion, String nombre, Set<String> tags, List<PointWrapper> puntosComuna) {
		super(ubicacion, nombre, tags);
		comuna = new PolygonWrapper(puntosComuna);
	}
	
	@Override
	public boolean estaCerca(PointWrapper ubicacion) {
		return comuna.isInside(ubicacion) && comuna.isInside(this.getUbicacion());
	}

	public PolygonWrapper getComuna() {
		return comuna;
	}

	public void setComuna(PolygonWrapper comuna) {
		this.comuna = comuna;
	}
	
}
