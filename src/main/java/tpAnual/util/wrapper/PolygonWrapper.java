package tpAnual.util.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

@Entity
@Embeddable
public class PolygonWrapper{
	@Transient //@OneToMany @Cascade({CascadeType.ALL})
	private List<PointWrapper> puntos = new ArrayList<PointWrapper>();
	
	
	@SuppressWarnings("unused")
	private PolygonWrapper(){}
	
	public PolygonWrapper(List<PointWrapper> puntos){
		this.puntos=puntos;
	}
	
	
	public Polygon toPolygon(){
		return new Polygon(this.toPoints());
	}
	
	public List<Point> toPoints(){
		List<Point> aux = new ArrayList<Point>();
		this.puntos.forEach(pw-> aux.add(new Point(pw.latitude(),pw.longitude())));
		return aux;
	}
	
	public Boolean isInside(PointWrapper punto){
		return this.toPolygon().isInside(punto.toPoint());
	}
	
	
	public List<PointWrapper> getPuntos(){
		return puntos;
	}
	
	public void setPuntos(List<PointWrapper> puntos){
		this.puntos=puntos;
	}

}
