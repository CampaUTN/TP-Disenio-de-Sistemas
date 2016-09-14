package tpAnual.bd.persistencia;

import java.time.DayOfWeek;
import java.time.LocalTime;
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

import tpAnual.BuscadorTexto;
import tpAnual.Busqueda;
import tpAnual.Horario;
import tpAnual.Terminal;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
 	
public class TestPersistirBusquedas {
	
	private static EntityManager em = PerThreadEntityManagers.getEntityManager();
	static long id1;
	static long id2;
	
	private static List<Poi> result = new ArrayList<Poi>();
	private static Point punto = new Point(10,10);
	private static Set<String> tags = new HashSet<String>();
	private static Terminal terminal = new Terminal(1);
	private static BuscadorTexto buscador = new BuscadorTexto();
	
	@BeforeClass
	public static void init() {
		terminal.desactivarMails();
		tags.add("cole");
		Poi poi= new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		result.add(poi);

		Busqueda busqueda1 = new Busqueda("colectivo",result);
		Busqueda busqueda2 = new Busqueda("colectivo",result);
		em.getTransaction().begin();
		em.persist(busqueda1);
		em.persist(busqueda2); 
		
		id1 = busqueda1.getId();
		id2 = busqueda2.getId();
	}
	
	@AfterClass
	public static void clear() {
		em.getTransaction().rollback();
	}
		
	
	@Test
	public void testId2(){
		Assert.assertEquals(id1+1, id2, 0);
	}
	
}
