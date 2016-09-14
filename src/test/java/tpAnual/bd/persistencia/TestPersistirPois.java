package tpAnual.bd.persistencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hsqldb.util.DatabaseManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.SingletonReseter;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;

public class TestPersistirPois {
	
	private static EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	private static Point punto = new Point(10,10);
	private static Set<String> tags = new HashSet<String>();
	private static List<Poi> poisBd = new ArrayList<Poi>();
	
	static long id1, id2;
	
	@BeforeClass
	public static void init() {
		SingletonReseter.resetAll();
		tags.add("cole");
		Poi poiPrueba = new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		Poi poiPrueba2 = new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		
		em.getTransaction().begin();
		em.persist(poiPrueba);
		em.persist(poiPrueba2);
//		entityManager.getTransaction().rollback();
		
		id1 = poiPrueba.getId();
		id2 = poiPrueba2.getId();
		
		poisBd = em.createQuery("FROM Poi").getResultList();
		
//		DatabaseManager.threadedDBM();
		
	}
	
	@AfterClass
	public static void clear() {
		em.getTransaction().rollback();
	}
	
	@Test
	public void testId(){
		Assert.assertEquals(id2, id1+1, 0);
	}
	@Test
	public void hayObjetosEnLaBase(){
		Assert.assertTrue(poisBd.size()>0);
	}
	
}
