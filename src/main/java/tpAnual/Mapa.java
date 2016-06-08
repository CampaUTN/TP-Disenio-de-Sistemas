package tpAnual;

import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.Point;

public class Mapa {
	
	private BuscadorTexto buscador = new BuscadorTexto();
	private List<Poi> pois = new ArrayList<Poi>();
	
	public void main(){
		Mapa mapa = new Mapa();
		
		Negocio negocio = new Negocio("mueble");
		Poi poi = new Poi(negocio,new Point(1,1),"A",null);
		mapa.alta(poi);
		mapa.buscador.buscarSegunTexto("mueble", mapa.pois, new Terminal(1));
		
		ReporteFecha reporteFecha = new ReporteFecha();
		reporteFecha.reportar(mapa.buscador.getRegistros());
	}
	
	// Busqueda de texto libre de pois
	
	public void alta(Poi poi){
		pois.add(poi);
	}
	
	public void baja(Poi poi){
		pois.remove(poi);
	}
	
	public List<Poi> buscar(String tags, Terminal terminal){
		return buscador.buscarSegunTexto(tags, pois, terminal);
	}

	public BuscadorTexto getBuscador(){
		return buscador;
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
