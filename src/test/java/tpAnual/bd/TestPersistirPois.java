package tpAnual.bd;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;

public class TestPersistirPois {
	
	private static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	private static EstacionDeColectivo colectivo = new EstacionDeColectivo(60, "pilar");
	private static Point punto = new Point(10,10);
	private static Set<String> tags = new HashSet<String>();
	
	static long id1;
	
	@BeforeClass
	public static void init() {
		tags.add("cole");
		Poi poiPrueba = new Poi(colectivo,punto,"Parada del 60",tags);
		
		entityManager.getTransaction().begin();
		entityManager.persist(poiPrueba);
		//entityManager.merge(poiPrueba);
		entityManager.flush();
		entityManager.getTransaction().rollback();
		
		id1 = poiPrueba.getId();
		
	}
	
	@Test
	public void testId1(){
		Assert.assertEquals(1, id1, 0);
	}
	
}
