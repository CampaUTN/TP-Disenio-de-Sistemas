package tpAnual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import tpAnual.POIs.Poi;

@Entity
@Table(name = "Busqueda")
public class Busqueda {
	@Id
	@GeneratedValue
	Long id;
	
	String parametros;
	
	//@NotNull
//	@OneToMany
	@Transient  //TODO  SACAR CUANDO ANDE BIEN LO DE PERSISTIR POIS, ESTO ME ESTA ROMPIENDO
	List<Poi> resultado = new ArrayList<Poi>();
	
	@SuppressWarnings("unused")
	private Busqueda(){}
	
	public Busqueda(String parametros, List<Poi> resultado){
		this.parametros=parametros;
		this.resultado.addAll(resultado);
	}
}
