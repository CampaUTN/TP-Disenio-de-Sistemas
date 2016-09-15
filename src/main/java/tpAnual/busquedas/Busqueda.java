package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tpAnual.POIs.Poi;

@Entity
@Table(name = "Busqueda")
public class Busqueda {
	@Id
	@GeneratedValue
	@Column(name="busq_id")
	long id;
	
	@Column(name="busq_parametros")
	String parametros;
	
	//@NotNull
	@Column(name="busq_resultado")
	@OneToMany
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
