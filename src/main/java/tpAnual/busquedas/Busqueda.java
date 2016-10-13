package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

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
	
	public Busqueda(String palabrasBuscadas, List<Poi> pois){
		this.palabrasBuscadas=palabrasBuscadas;
		this.pois.addAll(pois);
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
}
