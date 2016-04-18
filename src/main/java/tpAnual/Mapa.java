package tpAnual;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Mapa {
	
	private BuscadorTexto buscador = new BuscadorTexto();
	private List<Poi> pois = new ArrayList<Poi>();

	public List<Poi> buscarPoi(String tags) {
		return buscador.BuscameSegunTags(tags, pois);
	}

	public boolean estaCerca(Poi poi, Point unPunto) {
		return poi.estaCerca(unPunto);
	}

	public boolean estaDisponible(Poi poi,DayOfWeek dia,String hora) {
		return poi.estaDisponible(dia, hora);
	}

	public boolean estaDisponible(Poi poi, String servicio,DayOfWeek dia,String hora) {
		return poi.estaDisponible(servicio,dia,hora);
	}

	// Manejo de lista
	public void agregarPoi(Poi poi) {
		pois.add(poi);
	}

	public void sacarPoi(Poi poi) {
		pois.remove(poi);
	}

	public int cantidadPois() {
		return pois.size();
	}

	public void mostrar(List<Poi> list) {
		// list.forEach(System.out.println();); Imprimir en pantalla la lista
		// que quedo con los tags
	}

}
