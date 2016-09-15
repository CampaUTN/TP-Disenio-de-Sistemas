package tpAnual.utils;

import java.util.List;

import javax.persistence.Entity;

import org.uqbar.geodds.Point;


public class Polygon extends org.uqbar.geodds.Polygon {
	
	public Polygon(List<Point> puntos){
		super(puntos);
	}
	
}
