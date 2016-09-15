package tpAnual.bd.persistencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.SingletonReseter;
import tpAnual.Terminal;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.busquedas.Busqueda;
import tpAnual.util.wrapper.PointWrapper;
 	
public class TestPersistirBusquedas {
	
	private static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	private static List<Poi> result = new ArrayList<Poi>();
	private static PointWrapper punto = new PointWrapper(10,10);
	private static Set<String> tags = new HashSet<String>();
	private static Terminal terminal = new Terminal(1);
	
	private static Busqueda busqueda1;
	private static Busqueda busqueda2;

	
	@BeforeClass
	public static void init() {
		SingletonReseter.resetAll();
		entityManager.getTransaction().begin();
		
		terminal.desactivarMails();
		tags.add("cole");
		Poi poi= new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		result.add(poi);

		busqueda1 = new Busqueda("colectivo",result);
		busqueda2 = new Busqueda("colectivo",result);

		entityManager.persist(busqueda1);
	}
	
	@AfterClass
	public static void clear() {
		entityManager.getTransaction().rollback();
	}
	
	@Test
	public void lasIdsSonIncrementales(){
		long id1 = busqueda1.getId();
		long id2 = busqueda2.getId();
		
		Assert.assertEquals(id1+1, id2, 0);
	}

	@Test
	public void sePersistenLasBusquedas(){
		entityManager.persist(busqueda2); 
		
		List<Busqueda> busquedas = entityManager.createQuery("FROM Busqueda", Busqueda.class).getResultList();
		
		Assert.assertFalse(busquedas.isEmpty());
	}
	
	@Test
	public void obtengoBusquedaPorID(){		
		
		long id1 = busqueda1.getId();
		
		List<Busqueda> busquedas = entityManager.createQuery("FROM Busqueda where busq_id= :unId ", Busqueda.class).
				setParameter("unId", id1).getResultList();
		
		Assert.assertEquals(busqueda1,busquedas.get(0));
	}
	
	@Test
	public void buscoIDInexistenteYTengoListaVacia(){
		List<Busqueda> busquedas = entityManager.createQuery("FROM Busqueda where busq_id= :unId ", Busqueda.class).
				setParameter("unId", 250l).getResultList();
		
		Assert.assertTrue(busquedas.isEmpty());
	}

}
