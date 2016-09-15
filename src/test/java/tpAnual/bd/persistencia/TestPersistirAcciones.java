package tpAnual.bd.persistencia;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.Mapa;
import tpAnual.SingletonReseter;
import tpAnual.Terminal;
import tpAnual.batch.procesos.AccionTerminal;
import tpAnual.batch.procesos.ActivacionPorComuna;
import tpAnual.batch.procesos.ActivacionSeleccion;
import tpAnual.batch.procesos.DesactivarMails;

public class TestPersistirAcciones {
	private static EntityManager em = PerThreadEntityManagers.getEntityManager();
	static long id1;
	static long id2;
	
	private static Terminal terminal = new Terminal(1);
	
	@BeforeClass
	public static void init() {
		SingletonReseter.resetAll();
		em.getTransaction().begin();
		
		terminal.desactivarMails();
		Set<AccionTerminal> acciones = new HashSet<AccionTerminal>();
		acciones.add(new DesactivarMails());
		Set<Terminal> terminales = new HashSet<Terminal>();
		terminales.add(terminal);
		Mapa.getInstance().agregarTerminal(terminal);
		
		ActivacionPorComuna activadorComuna = new ActivacionPorComuna(1,acciones);
		ActivacionSeleccion activadorSeleccion = new ActivacionSeleccion(terminales, acciones);
		
		em.persist(activadorComuna);
		em.persist(activadorSeleccion);
		
		id1 = activadorComuna.getId();
		id2 = activadorSeleccion.getId();
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
