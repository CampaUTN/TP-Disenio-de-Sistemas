package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import tpAnual.util.bd.mongo.PoiDTO;

@Entity
public class Busqueda {
	private String palabrasBuscadas;
	
	@Embedded
	private List<PoiDTO> pois = new ArrayList<PoiDTO>();
	@SuppressWarnings("unused")
	private Date fecha = new Date();
	
	@SuppressWarnings("unused")
	private Busqueda(){}
	
	public Busqueda(String palabrasBuscadas, List<PoiDTO> pois){
		this.palabrasBuscadas=palabrasBuscadas;
		this.pois.addAll(pois);
	}
	
	public String getParametros() {
		return palabrasBuscadas;
	}

	public void setParametros(String parametros) {
		this.palabrasBuscadas = parametros;
	}

	public List<PoiDTO> getResultado() {
		return pois;
	}

	public void setResultado(List<PoiDTO> resultado) {
		this.pois = resultado;
	}
	
}
