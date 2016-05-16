package tpAnual;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.Point;
import java.util.Scanner;

public class Mapa {
	
	private BuscadorTexto buscador = new BuscadorTexto();
	private List<Poi> pois = new ArrayList<Poi>();
	BancoAdapter bancoAdap;
	CGPAdapter cgpAdap;
	
	// Busqueda de texto libre de pois
	
	public void altaPoi(Poi poi){
		pois.add(poi);
	}
	
	public void bajaPoi(Poi poi){
		pois.remove(poi);
	}
	
	public void modificarPoi(Poi poi){
		System.out.println("Modificar Poi");
		
		System.out.println(poi.getNombre());
		System.out.println("Ingrese nuevo nombre: ");
		String input = System.console().readLine();
		poi.setNombre(input);
		
		System.out.println(poi.getUbicacion());
		System.out.println("Ingrese nueva altitud: ");
		String altStr = System.console().readLine();
		System.out.println("Ingrese nueva latitud: ");
		String latStr = System.console().readLine();
		int altitud = Integer.parseInt(altStr);
		int latitud = Integer.parseInt(latStr);

	    Point nuevaUbicacion = new Point(altitud,latitud);
	    poi.setUbicacion(nuevaUbicacion);
		
	}
	
	public List<Poi> buscarPoi(String tags) {
		return buscador.buscarSegunTexto(tags, pois);
		
	}

	// Cercania de poi
	
	public boolean estaCerca(Poi poi, Point unPunto) {
		return poi.estaCerca(unPunto);
	}
	
	private double distanciaEntrePois(Poi poi1, Poi poi2){
		return poi1.getUbicacion().distance(poi2.getUbicacion());
	}
	
	public boolean estaAMenosDe(Poi poi1, Poi poi2, double distancia){
		return distanciaEntrePois(poi1,poi2) < distancia;
	}
	
	// Disponibilidad de poi
	
	public boolean estaDisponible(Poi poi,LocalDate fecha,String hora) {
		return poi.estaDisponible(fecha, hora);
	}

	public boolean estaDisponible(Poi poi, String servicio,LocalDate fecha,String hora) {
		return poi.estaDisponible(servicio,fecha,hora);
	}

	// Manejo de lista de pois


	public int cantidadPois() {
		return pois.size();
	}

	// Cuando hagamos UI esto apareceria en una ventana y no por consola.
	public void mostrar(List<Poi> list) {
		list.forEach(poi-> System.out.println(poi.getNombre()));
	}
	
	//Setters
	
	public void setBancoAdapter(BancoAdapter bancoAd){
		bancoAdap = bancoAd;
	}
	public void setCgpAdapter(CGPAdapter cgpAd){
		cgpAdap = cgpAd;
	}

}
