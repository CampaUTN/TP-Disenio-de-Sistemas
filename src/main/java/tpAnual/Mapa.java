	package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.util.Reseter;
import tpAnual.util.wrapper.PointWrapper;

public class Mapa implements WithGlobalEntityManager{
	private List<Terminal> terminales = new ArrayList<Terminal>();
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
		entityManager().persist(poi);
	}
	
	public void baja(Poi poi){
		entityManager().remove(entityManager().contains(poi) ? poi : entityManager().merge(poi)); //em.merge(poi) retorna el poi que 'mergea'.
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
	@SuppressWarnings("unchecked")
	public List<Poi> getPois(){
		return entityManager().createQuery("FROM Poi").getResultList();
	}

	public int cantidadPois() {
		return this.getPois().size();
	}
	
	// Setters y getters
	public void agregarTerminal(Terminal terminal){
		this.terminales.add(terminal);
	}
	
	public List<Terminal> getTerminales() {
		return terminales;
	}

	public void setTerminales(List<Terminal> terminales) {
		this.terminales = terminales;
	}

	public Poi poisPendientesDeModificar(String nombre){
		List<Poi> pois = this.getPois()
				.stream()
				.filter(poi->poi.getNombre().equals(nombre))
				.collect(Collectors.toList());
		return pois.isEmpty()? null : pois.get(0);
	}
	
	public Terminal buscarTerminalPorId(int id){
		return terminales.stream().filter(t->t.getNumeroTerminal()==id).collect(Collectors.toList()).get(0);
	}
	
	public List<Poi> buscarPoi(String nombre){
		return this.buscarPoi(nombre, "");
	}
	
	public List<Poi> buscarPoi(String nombre, String tipo){
		
		// TODO sacar esto que es para testear!
		Set<String> tags = new HashSet<String>();
		Poi poi1 = (Poi)new Negocio(new PointWrapper(54, 10),"mueblesSA",tags,"muebleria",10);
		
		Poi poi2 = (Poi) new Banco(new PointWrapper(2, 2), "Banco Santander" , null);
		poi1.agregarTag("negocio");
		poi1.agregarTag("compras");
		poi1.setCalle("Strangford");
		poi1.setDireccion(1857);
		
		poi2.setCalle("Avenida Rivadavia");
		poi2.setDireccion(458);
		Reseter.resetSingletons();
		entityManager().getTransaction().begin();
		Mapa.getInstance().alta(poi1);
		Mapa.getInstance().alta(poi2);
		
		//TODO: ver tipo.
		String query = "FROM Poi WHERE poi_nombre LIKE CONCAT('%',:nombre,'%') ";
		String extra = (tipo!="" && tipo!=null) ? "and poi_tipo = :tipo" : " ";
		
		List<Poi> resultados = entityManager().createQuery(query+extra, Poi.class).
				setParameter("nombre", nombre).getResultList();
				
		//TODO sacar
		entityManager().getTransaction().rollback();

		
		Reseter.resetSingletons();

		return resultados.stream().filter(t-> t.getClass().getSimpleName().equals(tipo)).collect(Collectors.toList());
	}

	public Poi poiDeId(long poiId){
		
		// TODO sacar esto que es para testear!
		Set<String> tags = new HashSet<String>();
		Poi poi1 = (Poi)new Negocio(new PointWrapper(54, 10),"mueblesSA",tags,"muebleria",10);
		
		Poi poi2 = (Poi) new Banco(new PointWrapper(2, 2), "Banco Santander" , null);
		poi1.agregarTag("negocio");
		poi1.agregarTag("compras");
		poi1.setCalle("Strangford");
		poi1.setDireccion(1857);
		
		poi2.setCalle("Avenida Rivadavia");
		poi2.setDireccion(458);
		Reseter.resetSingletons();
		entityManager().getTransaction().begin();
		Mapa.getInstance().alta(poi1);
		Mapa.getInstance().alta(poi2);
		
		
		Poi poi = entityManager().createQuery("FROM Poi WHERE poi_id = :id", Poi.class)
				.setParameter("id", poiId)
				.getResultList()
				.get(0);
				
		//TODO sacar
		entityManager().getTransaction().rollback();

		
		Reseter.resetSingletons();
		return poi;
	}
}
