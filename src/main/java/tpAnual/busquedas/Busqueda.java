package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import tpAnual.POIs.Poi;



@Entity
public class Busqueda {
	public String getPalabrasBuscadas() {
		return palabrasBuscadas;
	}

	public void setPalabrasBuscadas(String palabrasBuscadas) {
		this.palabrasBuscadas = palabrasBuscadas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Poi> getPois() {
		return pois;
	}

	public void setPois(List<Poi> pois) {
		this.pois = pois;
	}

	private String palabrasBuscadas;
	@SuppressWarnings("unused")
	private int id;
	@Embedded
	private List<Poi> pois = new ArrayList<Poi>();
	private Date fecha = new Date();
	
	@SuppressWarnings("unused")
	private Busqueda(){}
	
	private int cantidadPois;
	
	@SuppressWarnings("deprecation")
	public Busqueda(String palabrasBuscadas, List<Poi> pois){
		this.palabrasBuscadas=palabrasBuscadas;
		this.pois.addAll(pois);
		this.cantidadPois = pois.size();
		this.fecha = new Date();
		fecha.setHours(0);
		fecha.setMinutes(0);
		fecha.setSeconds(0);
		id=this.hashCode();
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
