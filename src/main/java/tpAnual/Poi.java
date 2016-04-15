package tpAnual;

import java.util.*;
import org.uqbar.geodds.Point;

public class Poi {
	
	private Point ubicacion;
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
	public boolean estaCerca(Point unPunto) {
		return tipo.estaCerca(unPunto, this.ubicacion); 
	}//TODO Ver si cambiar por una clase que agrupe los datos

	
	// Tags:
	public boolean tieneTag(String clave) {
		return tags.stream()
				.anyMatch(tag->tag.toLowerCase().contains(clave.toLowerCase()));
	}
	
	public void agregarTag(String nuevoTag){
		tags.add(nuevoTag);
	}
}
