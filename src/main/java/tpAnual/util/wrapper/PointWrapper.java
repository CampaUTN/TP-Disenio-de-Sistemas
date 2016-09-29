package tpAnual.util.wrapper;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import org.uqbar.geodds.Point;

@Entity
@Embeddable
public class PointWrapper {
	private double latitude;
	private double longitude;
	
	
	@SuppressWarnings("unused")
	private PointWrapper(){}
	
	public PointWrapper(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double latitude(){
		return latitude;
	}
	
	public double longitude(){
		return longitude;
	}
	
	public double distance(PointWrapper anotherPoint){
		return this.toPoint().distance(anotherPoint.toPoint());
	}
	
	public Point toPoint(){
		return new Point(latitude, longitude);
	}
	
	@Override
	public String toString(){
		return this.toPoint().toString();
	}
	
	public boolean equals(PointWrapper punto){
		return this.latitude==punto.getLatitude() && this.longitude==punto.getLongitude();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
