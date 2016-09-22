package tpAnual.bd.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.batch.procesos.AccionTerminal;
import tpAnual.batch.procesos.ActivacionPorComuna;
import tpAnual.batch.procesos.ActivacionSeleccion;
import tpAnual.batch.procesos.DesactivarMails;
import tpAnual.batch.procesos.Proceso;
import tpAnual.util.SingletonReseter;

public class TestPersistirAcciones {
	private static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	static long id1;
	static long id2;
	
	private static ActivacionPorComuna activadorComuna;
	private static ActivacionSeleccion activadorSeleccion;

	private static Terminal terminal = new Terminal(1);
	
	@BeforeClass
	public static void init() {
		SingletonReseter.resetAll();
		entityManager.getTransaction().begin();
		
		terminal.desactivarMails();
		List<AccionTerminal> acciones = new ArrayList<AccionTerminal>();
		acciones.add(new DesactivarMails());
		List<Terminal> terminales = new ArrayList<Terminal>();
		terminales.add(terminal);
		Mapa.getInstance().agregarTerminal(terminal);
		
		activadorComuna = new ActivacionPorComuna(1,acciones);
		activadorSeleccion = new ActivacionSeleccion(terminales, acciones);
		
		entityManager.persist(activadorComuna);
		entityManager.persist(activadorSeleccion);
		
		id1 = activadorComuna.getId();
		id2 = activadorSeleccion.getId();
	}
		
	@AfterClass
	public static void clear() {
		entityManager.getTransaction().rollback();
	}
		
	@Test
	public void lasIdsSonIncrementales(){		
		long id1 = activadorComuna.getId();
		long id2 = activadorSeleccion.getId();
		
		Assert.assertEquals(id1+1, id2);
	}

	@SuppressWarnings("unchecked")
	@Test 
	public void sePersisteUnaAccion(){
		List<Proceso> busquedas = entityManager.createQuery("FROM Proceso").getResultList();
		
		Assert.assertFalse(busquedas.isEmpty());	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtengoDistintosTiposDeAcciones(){
		List<Proceso> busquedas = entityManager.createQuery("FROM Proceso").getResultList();
		
		Assert.assertEquals(busquedas.size(),2);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buscoAccionPorID(){
		
		long id = activadorComuna.getId();		
		
		List<Proceso> acciones = entityManager.createQuery("FROM Proceso WHERE id= :unId ").
				setParameter("unId", id).getResultList();
		
		Assert.assertEquals(activadorComuna,acciones.get(0));
	}

	@Test
	public void buscoAccionInexistente(){
			
		@SuppressWarnings("unchecked")
		List<ActivacionPorComuna> acciones = entityManager.createQuery("FROM Proceso WHERE id= :unId ").
				setParameter("unId", 1500l).getResultList();
		
		Assert.assertTrue(acciones.isEmpty());
	}

}
