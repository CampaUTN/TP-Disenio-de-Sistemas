package tpAnual.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tpAnual.Terminal;
import tpAnual.POIs.Poi;

public class ParametroBusqueda implements WithGlobalEntityManager{
	private String nombre;
	private String tipo;
	private String calle;
	private String palabras;
	private Terminal terminal;
	
	public void setPalabras(String palabras) {
		this.palabras = palabras;
	}

	private List<String> tags = new ArrayList<>();
	
	public ParametroBusqueda(String nombre, String tipo, String calle, Terminal terminal, String tags){
		this.nombre = nombre;
		this.tipo = tipo;
		this.calle = calle;	
		this.palabras = tags;
		this.terminal = terminal;

		if(tags.length() != 0){
			this.tags =  Arrays.asList(tags.split(" "));
		}
	}	
	
	public static ParametroBusqueda tags(String tags){
		return new ParametroBusqueda("", "", "", null, tags);
	}
	
	public static ParametroBusqueda busqueda(String tags){
		return new ParametroBusqueda("","","",null,tags);
	}
	
	public TypedQuery<Poi> armarQuery(){
		String query = "FROM Poi WHERE poi_nombre LIKE CONCAT('%',:nombre,'%') AND calle LIKE CONCAT('%',:calle,'%') ";
		System.out.println(tipo);
				
		if( ! tipo.equals("Todos")){

			String extra = "and poi_tipo = :tipo";

			return entityManager().createQuery(query+extra, Poi.class).
					setParameter("nombre", nombre).setParameter("calle", calle).setParameter("tipo", tipo);
		}else{
			return entityManager().createQuery(query, Poi.class).
					setParameter("nombre", nombre).setParameter("calle", calle);
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public String getPalabras() {
		return palabras;
	}
}
