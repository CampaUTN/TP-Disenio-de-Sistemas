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
import tpAnual.POIs.Cgp;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.utils.PointWrapper;

public class TestPersistirPois {
	
	private static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	private static PointWrapper punto = new PointWrapper(10,10);
	private static Set<String> tags = new HashSet<String>();
	static long id1, id2;
	
	@BeforeClass
	public static void init() {
		SingletonReseter.resetAll();
		entityManager.getTransaction().begin();
		
		tags.add("cole");
		Poi poiPrueba = new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		Poi poiPrueba2 = new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		

		entityManager.persist(poiPrueba);
		entityManager.persist(poiPrueba2);

		
		id1 = poiPrueba.getId();
		id2 = poiPrueba2.getId();		
	}
	
	@AfterClass
	public static void clear() {
		entityManager.getTransaction().rollback();
	}	
	
	@Test
	public void lasIdsSeAutogeneranSecuencialmente(){
		Assert.assertEquals(id2, id1+1, 0);
	}
	
	@Test
	public void todosLosPoisSePersisten(){
		List<Poi> poisBd = entityManager.createQuery("FROM Poi").getResultList();
		Assert.assertEquals(2,poisBd.size(),0);
	}
	
	@Test
	public void losPoisConServicioSePersisten(){
		Poi negocio = new Negocio(punto,"mueblesSA",tags,"muebleria",10);
		
		PointWrapper puntoComunaA = new PointWrapper(0,0);
		PointWrapper puntoComunaB = new PointWrapper(20,60);
		PointWrapper puntoComunaC = new PointWrapper(50,0);
		
		List<PointWrapper> puntosComuna =  new ArrayList<PointWrapper>();
		
		puntosComuna.add(puntoComunaA);
		puntosComuna.add(puntoComunaB);
		puntosComuna.add(puntoComunaC);
		
		Poi cgp = new Cgp(punto, "Asistencia social", tags,puntosComuna);
		
		entityManager.persist(negocio);
		entityManager.persist(cgp);
		
		List<Poi> poisBd2 = entityManager.createQuery("FROM Poi").getResultList();
		
		Assert.assertEquals(4,poisBd2.size(),0);
		
		entityManager.remove(entityManager.contains(negocio) ? negocio : entityManager.merge(negocio));
		entityManager.remove(entityManager.contains(cgp) ? cgp : entityManager.merge(cgp));
	}
	
	@Test
	public void lasIdsNoSeRepiten(){
		List<Poi> poisBd = entityManager.createQuery("FROM Poi where id= :unId", Poi.class)
				.setParameter("unId", 1l).getResultList();
		
		Assert.assertEquals(1,poisBd.size());
	}
	
	@Test
	public void buscoPoiInexistenteYGeneraListaVacia(){
		List<Poi> poisBd = entityManager.createQuery("FROM Poi where poi_id= :unId", Poi.class)
				.setParameter("unId", 56l).getResultList();
		
		Assert.assertTrue(poisBd.isEmpty());
	}	
}