package tpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa {
	
	public int altitud, latitud;
	
	private List<Poi> listaPois = new ArrayList<Poi>();
	
	
	
	public void buscarPoi(String clave){
		listarPantalla( this.listaPois
				.stream()
				.filter(poi -> poi.tieneTag(clave))
				.collect(Collectors.toList()) ); 
	}
	
	public boolean estaCerca(Poi poi, int altitud, int latitud){
		return poi.estaCerca(altitud,latitud);
	}
	
	public boolean estaDisponible(Poi poi){
		return poi.estaDisponible();
	}
	
	
	
	//Manejo de lista
	public void agregarPoi(Poi poi){
		listaPois.add(poi);
	}
	
	public void sacarPoi(Poi poi){
		listaPois.remove(poi);
	}
	
	public int cantidadPois(){
		return listaPois.size();
	}
	
	public void listarPantalla(List<Poi> list){
		//list.forEach(System.out.println();); Imprimir en pantalla la lista que quedo con los tags
	}
	
}
