	package tpAnual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.Session;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import tpAnual.POIs.Banco;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.util.Reseter;
import tpAnual.util.wrapper.PointWrapper;

public class Mapa  implements WithGlobalEntityManager, TransactionalOps{
	private List<Terminal> terminales = new ArrayList<Terminal>();
	private static Mapa instance = null;
	

	private Mapa(){}
	
	// TODO sacar esto que es para testear!
	public void agregarPoisPrueba(){
		Set<String> tags = new HashSet<String>();
		PointWrapper ubicacion = new PointWrapper(54, 10);
		Poi poi = new EstacionDeColectivo(ubicacion, "107", tags, 0, "");
		Mapa.getInstance().alta(poi);
		
		Poi poi1 = (Poi)new Negocio(new PointWrapper(54, 10),"mueblesSA",tags,"muebleria",10);
		Poi poi2 = (Poi) new Banco(new PointWrapper(2, 2), "Banco Santander" , null);
		poi1.agregarTag("negocio");
		poi1.agregarTag("compras");
		poi1.setCalle("Strangford");
		poi1.setDireccion(1857);
		poi1.setNumeroComuna(1);
		poi2.setCalle("Avenida Rivadavia");
		poi2.setDireccion(458);
		poi1.setNumeroComuna(2);
		Mapa.getInstance().alta(poi1);
		Mapa.getInstance().alta(poi2);
	}
	public static Mapa getInstance(){
		if(instance==null){
			instance = new Mapa();
			instance.agregarPoisPrueba();
		}
		return instance;
	}
	
	public static void resetSingleton(){
		Mapa.getInstance().eliminarTodosLosPois();
	    instance = null;
	}
	
	//Altas y bajas
	public void alta(Poi poi){
		entityManager().persist(poi);
		if(entityManager().getTransaction().isActive()){
			entityManager().flush();			
		}else{
			entityManager().getTransaction().begin();
			entityManager().flush();
			entityManager().getTransaction().commit();
		}
		//entityManager().merge(poi); // TODO o refresh?
	}
	
	public void baja(Poi poi){
		entityManager().remove(entityManager().contains(poi) ? poi : entityManager().merge(poi)); //em.merge(poi) retorna el poi que 'mergea'.
	}
	
	private void eliminarTodosLosPois(){
		withTransaction(() ->{
			List<Poi> poisAEliminar = new ArrayList<Poi>();
			poisAEliminar.addAll(Mapa.getInstance().getPois());
			poisAEliminar.forEach(poi -> Mapa.getInstance().baja(poi)); //Doy de baja los POIs de la BD
		});
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
	public List<Poi> getPois(){ //TODO BORRAR TODO MENOS LA LISTA Q ES LO Q HAY Q RETORNAR.
				
		List<Poi> resultado = entityManager().createQuery("FROM Poi").getResultList();
		
		return resultado;
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
		return this.buscarPoi(nombre, "", "");
	}
	
	public List<Poi> buscarPoi(String nombre, String tipo, String calle){
		
		List<Poi> resultados = new ArrayList<>();
		
		//TODO: ver tipo.
		String query = "FROM Poi WHERE poi_nombre LIKE CONCAT('%',:nombre,'%') AND calle LIKE CONCAT('%',:calle,'%')";
		System.out.println(tipo);
		if( ! tipo.equals("Todos")){

			String extra = "and poi_tipo = :tipo";

			resultados = entityManager().createQuery(query+extra, Poi.class).
					setParameter("nombre", nombre).setParameter("calle", calle).setParameter("tipo", tipo).getResultList();
		}else{
			resultados = entityManager().createQuery(query, Poi.class).
					setParameter("nombre", nombre).setParameter("calle", calle).getResultList();
		}

		return resultados;
	}

	public Poi poiDeId(long poiId){
		return entityManager().createQuery("FROM Poi WHERE poi_id = :id", Poi.class)
				.setParameter("id", poiId)
				.getResultList()
				.get(0);
	}
	
}
