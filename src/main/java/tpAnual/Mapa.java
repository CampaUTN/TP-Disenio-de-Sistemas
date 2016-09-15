package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.POIs.Poi;
import tpAnual.util.wrapper.PointWrapper;

public class Mapa {
	
	private Set<Terminal> terminales = new HashSet<Terminal>();
	@SuppressWarnings("unused")
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	private static Mapa instance = null;
	

	private Mapa(){}
	
	public static Mapa getInstance(){
		if(instance==null){
			instance = new Mapa();
		}
		return instance;
	}
	
	public static void resetSingleton(){
		eliminarTodosLosPois();
	    instance = null;
	}
	
	//Altas y bajas
	public void alta(Poi poi){
		//pois.add(poi);
		entityManager.persist(poi);
	}
	
	public void baja(Poi poi){
		//pois.remove(poi);
		entityManager.remove(entityManager.contains(poi) ? poi : entityManager.merge(poi)); //em.merge(poi) retorna el poi que 'mergea'.
	}
	
	private static void eliminarTodosLosPois(){
		List<Poi> poisAEliminar = new ArrayList<Poi>();
		poisAEliminar.addAll(Mapa.getInstance().getPois());
		poisAEliminar.forEach(poi -> Mapa.getInstance().baja(poi)); //Doy de baja los POIs de la BD
	}
	
	// Cercania de poi
	public boolean estaCerca(Poi poi, PointWrapper unPunto) {
		return poi.estaCerca(unPunto);
	}
	
	private double distanciaEntrePois(Poi poi1, Poi poi2){
		return poi1.getUbicacion().distance(poi2.getUbicacion());
	}
	
	public boolean estaAMenosDe(Poi poi1, Poi poi2, double distancia){
		return distanciaEntrePois(poi1,poi2) < distancia;
	}
	
	// Manejo de lista de pois
	public List<Poi> getPois(){
		//return pois;
		// TODO descomentar al desbugear todo:
		return entityManager.createQuery("FROM Poi").getResultList();
	}

	public int cantidadPois() {
		return getPois().size();
	}
	
	// Setters y getters
	public void agregarTerminal(Terminal terminal){
		this.terminales.add(terminal);
	}
	
	public Set<Terminal> terminales(){
		return this.terminales;
	}
	
	public Set<Terminal> getTerminales() {
		return terminales;
	}

	public void setTerminales(Set<Terminal> terminales) {
		this.terminales = terminales;
	}

	public Poi poisPendientesDeModificar(String nombre){
		Poi poiAModificar = this.getPois().stream()
				.filter(poi->poi.getNombre().equals(nombre))
				.collect(Collectors.toList())
				.get(0);
		return poiAModificar;
	}

}
