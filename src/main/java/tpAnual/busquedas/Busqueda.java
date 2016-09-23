package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.List;

import tpAnual.POIs.Poi;


public class Busqueda {
	long id;
	
	String parametros;
	
	List<Poi> resultado = new ArrayList<Poi>();
	
	@SuppressWarnings("unused")
	private Busqueda(){}
	
	public Busqueda(String parametros, List<Poi> resultado){
		this.parametros=parametros;
		this.resultado.addAll(resultado);
	}
	
	
	public long getId(){
		return this.id;
	}
	
	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public List<Poi> getResultado() {
		return resultado;
	}

	public void setResultado(List<Poi> resultado) {
		this.resultado = resultado;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
