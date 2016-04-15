package tpAnual;

import java.util.*;

public class Poi {
	
	private int altitudPoi, latitudPoi;
	@SuppressWarnings("unused")
	private String nombre;
	private TipoPoi tipo;
	private Set<String> tags = new HashSet<String>();
	
	public boolean estaDisponible(){
		return tipo.estaDisponible();
	}

	public boolean estaDisponible(String tramite) {
		return tipo.estaDisponible(tramite);
	}
	
	public boolean estaCerca(int altitud, int latitud) {
		return tipo.estaCerca(altitud,latitud,altitudPoi,latitudPoi); 
	}//TODO Ver si cambiar por una clase que agrupe los datos

	public boolean tieneTag(String clave) {
		return tags.contains(clave);
	}
	
	public void agregarTag(String nuevoTag){
		tags.add(nuevoTag);
	}
}
