package tpAnual.POIs;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import org.uqbar.geodds.*;

@Entity
@DiscriminatorValue("nro_cgp")
public class Cgp extends PoiConServicios {


	//@Transient //TODO: sacar y q la comuna sea un nro!
	@GeneratedValue
	@Column(name = "nro_cgp")
	private long numero;
	
	
	@Transient
	private Polygon comuna;

	public Cgp(){super();}
	
	public Cgp(Point ubicacion, String nombre, Set<String> tags, List<Point> puntosComuna) {
		super(ubicacion, nombre, tags);
		comuna =new Polygon(puntosComuna);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean estaCerca(Point ubicacion) {
		return comuna.isInside(ubicacion) && comuna.isInside(this.getUbicacion());
	}
	
}
