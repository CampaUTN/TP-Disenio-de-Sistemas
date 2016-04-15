package tpAnual;

import java.util.*;

public class Poi {
	
	private int altitudPoi, latitudPoi; //TODO
	@SuppressWarnings("unused")
	private String nombre;
	private TipoPoi tipo;
	private Set<String> tags = new HashSet<String>();
	
	public Poi(TipoPoi tipo, String nombre, Set<String> tags)
	{
		this.tipo=tipo;
		this.nombre=nombre;
		this.tags=tags;
		tags.add(nombre);
		//TODO cuando tengamos la abstraccion (de alguna libreria) para altitud y longitud, faltaria agregar eso en el constructor.
	}
	
	// Disponibilidad:
	public boolean estaDisponible(){
		return tipo.estaDisponible();
	}

	public boolean estaDisponible(String servicio) {
		return tipo.estaDisponible(servicio);
	}
	
	// Distancia:
	public boolean estaCerca(int altitud, int latitud) {
		return tipo.estaCerca(altitud,latitud,altitudPoi,latitudPoi); 
	}//TODO Ver si cambiar por una clase que agrupe los datos

	
	// Tags:
	public boolean tieneTag(String clave) {
		return tags.contains(clave);
	}
	
	public void agregarTag(String nuevoTag){
		tags.add(nuevoTag);
	}
}
