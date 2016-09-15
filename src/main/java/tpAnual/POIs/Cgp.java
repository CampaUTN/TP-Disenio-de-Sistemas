package tpAnual.POIs;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import org.uqbar.geodds.*;

import tpAnual.utils.PointWrapper;
import tpAnual.utils.PolygonWrapper;

@Entity
@DiscriminatorValue("nro_cgp")
public class Cgp extends PoiConServicios {


	//@Transient //TODO: sacar y q la comuna sea un nro!
	@GeneratedValue
	@Column(name = "nro_cgp")
	private long numero;
	
	
//	@Transient
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

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public PolygonWrapper getComuna() {
		return comuna;
	}

	public void setComuna(PolygonWrapper comuna) {
		this.comuna = comuna;
	}
	
}
