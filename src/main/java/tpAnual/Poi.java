package tpAnual;

import java.util.ArrayList;
import java.util.List;

public class Poi {
	
	public int altitudPoi, latitudPoi;
	String nombre;
	TipoPoi tipo;
	private List<String> tags = new ArrayList<String>();
	
	
	public boolean estaDisponible(){
		return tipo.estaDisponible();
	}

	public boolean estaCerca(int altitud, int latitud) {
		return tipo.estaCerca(altitud,latitud,altitudPoi,latitudPoi); //Ver si cambiar por una clase que agrupe los datos
	}

	public boolean tieneTag(String clave) {
		return tags.contains(clave);
	}
	
	public void agregarTag(String nuevoTag){
		tags.add(nuevoTag); //Se podria verificar que no exista antes ese tag
	}
	
}
