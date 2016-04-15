package tpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa {
	
	//private int altitud, latitud; //TODO! Lo comento asi no tira warnings molesto
	
	private List<Poi> pois = new ArrayList<Poi>();
	
	public Mapa(List<Poi> pois)
	{
		this.pois=pois;
		//TODO falta lat y long. Leer comentario identico en tipoPoi
	}
	
	public void buscarPoi(String tag){
		mostrar( pois
				.stream()
				.filter(poi -> poi.tieneTag(tag))
				.collect(Collectors.toList()) ); 
	}
	
	public boolean estaCerca(Poi poi, int altitud, int latitud){
		return poi.estaCerca(altitud,latitud);
	}
	
	public boolean estaDisponible(Poi poi){
		return poi.estaDisponible();
	}
	
	public boolean estaDisponible(Poi poi, String servicio)
	{
		return poi.estaDisponible(servicio);
	}
	
	
	
	//Manejo de lista
	public void agregarPoi(Poi poi){
		pois.add(poi);
	}
	
	public void sacarPoi(Poi poi){
		pois.remove(poi);
	}
	
	public int cantidadPois(){
		return pois.size();
	}
	
	public void mostrar(List<Poi> list){
		//list.forEach(System.out.println();); Imprimir en pantalla la lista que quedo con los tags
	}
	
}
