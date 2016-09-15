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

public class TestPersistirPois {
	
	private static EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	private static Point punto = new Point(10,10);
	private static Set<String> tags = new HashSet<String>();
	private static List<Poi> poisBd = new ArrayList<Poi>();
	static long id1, id2;
	
	@BeforeClass
	public static void init() {
		SingletonReseter.resetAll();
		em.getTransaction().begin();
		
		tags.add("cole");
		Poi poiPrueba = new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		Poi poiPrueba2 = new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		

		em.persist(poiPrueba);
		em.persist(poiPrueba2);

		
		id1 = poiPrueba.getId();
		id2 = poiPrueba2.getId();
		
		poisBd = em.createQuery("FROM Poi").getResultList();
		
	}
	
	@AfterClass
	public static void clear() {
		em.getTransaction().rollback();
	}
	
	
	@Test
	public void lasIdsSeAutogeneranSecuencialmente(){
		Assert.assertEquals(id2, id1+1, 0);
	}
	
	@Test
	public void todosLosPoisSePersisten(){
		Assert.assertEquals(2,poisBd.size(),0);
	}
	
	@Test
	public void losPoisConServicioSePersisten(){
		Poi negocio = new Negocio(punto,"mueblesSA",tags,"muebleria",10);
		Point puntoComunaA = new Point(0,0);
		Point puntoComunaB = new Point(20,60);
		Point puntoComunaC = new Point(50,0);
		List<Point> puntosComuna =  new ArrayList<Point>();
		puntosComuna.add(puntoComunaA);
		puntosComuna.add(puntoComunaB);
		puntosComuna.add(puntoComunaC);
		Poi cgp = new Cgp(punto, "Asistencia social", tags,puntosComuna);
		em.persist(negocio);
		em.persist(cgp);
		List<Poi> poisBd2 = em.createQuery("FROM Poi").getResultList();
		Assert.assertEquals(4,poisBd2.size(),0);
		em.remove(em.contains(negocio) ? negocio : em.merge(negocio));
		em.remove(em.contains(cgp) ? cgp : em.merge(cgp));
	}
	
	// TODO
	// el problema no esta en la condicion del where sino en la columna. La cantidad de pois es 2,
	// pero siempre me trae 2, asi pida id 0, 1, 2, o 99999. Es decir, el where 'esta retornando siempre true'
	// pareciera ser un problema en el nombre de la columna.
//	@Test
//	public void lasIdsNoSeRepiten(){
//		List<Poi> poisBd2 = em.createQuery("FROM Poi p where p.id= :unId", Poi.class)
//				.setParameter("unId", 1l).getResultList();
//		Assert.assertEquals(1,poisBd.size(),0);
//	}
	
}
