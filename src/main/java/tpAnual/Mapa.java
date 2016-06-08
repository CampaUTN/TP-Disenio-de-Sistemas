package tpAnual;

import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.Point;

public class Mapa {
	
	public BuscadorTexto buscador = new BuscadorTexto();
	private List<Poi> pois = new ArrayList<Poi>();
	
	// Busqueda de texto libre de pois
	
	public void alta(Poi poi){
		pois.add(poi);
	}
	
	public void baja(Poi poi){
		pois.remove(poi);
	}
	
	public List<Poi> buscar(String tags){
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
	
	// Manejo de lista de pois

	public int cantidadPois() {
		return pois.size();
	}

}
