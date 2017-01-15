package tpAnual.busquedas;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Transient;

import tpAnual.POIs.Poi;



@Entity
public class Busqueda {
	private String palabrasBuscadas;
	
	@Embedded
	private List<Poi> pois = new ArrayList<Poi>();
	@SuppressWarnings("unused")
	private Date fecha = new Date();
	
	@SuppressWarnings("unused")
	private Busqueda(){}
	
	private int cantidadPois;
	
	public Busqueda(String palabrasBuscadas, List<Poi> pois){
		this.palabrasBuscadas=palabrasBuscadas;
		this.pois.addAll(pois);
		this.cantidadPois = pois.size();
	}
	
	public String getParametros() {
		return palabrasBuscadas;
	}

	public void setParametros(String parametros) {
		this.palabrasBuscadas = parametros;
	}

	public List<Poi> getResultado() {
		return pois;
	}

	public void setResultado(List<Poi> resultado) {
		this.pois = resultado;
	}
	
	public void setFecha( Date fecha){
		this.fecha = fecha;
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public int getCantidadPois(){
		return cantidadPois;
	}
    
	public boolean esFechaAnterior(Date otraFecha){
		return (fecha.compareTo(otraFecha) < 0);
	}
	
	public boolean esFechaPosterior(Date otraFecha){
		return (fecha.compareTo(otraFecha) > 0);
	}

	public void setCantidadPois(int cantidadPois) {
		this.cantidadPois = cantidadPois;
	}
	
	
}
