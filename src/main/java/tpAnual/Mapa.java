package tpAnual;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.Point;

public class Mapa {
	
	private BuscadorTexto buscador = new BuscadorTexto();
	private List<Poi> pois = new ArrayList<Poi>();

	
	// Busqueda de texto libre de pois
	
	public List<Poi> buscarPoi(String tags) {
		return buscador.BuscameSegunTags(tags, pois);
	}

	// Cercania de poi
	
	public boolean estaCerca(Poi poi, Point unPunto) {
		return poi.estaCerca(unPunto);
	}
	
	
	// Disponibilidad de poi
	
	public boolean estaDisponible(Poi poi,DayOfWeek dia,String hora) {
		return poi.estaDisponible(dia, hora);
	}

	public boolean estaDisponible(Poi poi, String servicio,DayOfWeek dia,String hora) {
		return poi.estaDisponible(servicio,dia,hora);
	}

	// Manejo de lista de pois
	public void agregarPoi(Poi poi) {
		pois.add(poi);
	}

	public void sacarPoi(Poi poi) {
		pois.remove(poi);
	}

	public int cantidadPois() {
		return pois.size();
	}

	// Cuando hagamos UI esto apareceria en una ventana y no por consola.
	public void mostrar(List<Poi> list) {
		list.forEach(poi-> System.out.println(poi.nombre));
	}

}
