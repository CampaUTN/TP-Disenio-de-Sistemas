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
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.BuscadorTexto;
import tpAnual.Busqueda;
import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.batch.procesos.AccionTerminal;
import tpAnual.batch.procesos.ActivacionEnTodas;
import tpAnual.batch.procesos.ActivacionPorComuna;
import tpAnual.batch.procesos.ActivacionSeleccion;
import tpAnual.batch.procesos.ActivarMails;
import tpAnual.batch.procesos.DesactivarMails;

public class TestPersistirAcciones {
	private static EntityManager em = PerThreadEntityManagers.getEntityManager();
	static long id1;
	static long id2;
	
	private static Terminal terminal = new Terminal(1);
	
	@BeforeClass
	public static void init() {
		terminal.desactivarMails();
		Set<AccionTerminal> acciones = new HashSet<AccionTerminal>();
		acciones.add(new DesactivarMails());
		Set<Terminal> terminales = new HashSet<Terminal>();
		terminales.add(terminal);
		Mapa.getInstance().agregarTerminal(terminal);
		
		ActivacionPorComuna activadorComuna = new ActivacionPorComuna(1,acciones);
		ActivacionSeleccion activadorSeleccion = new ActivacionSeleccion(terminales, acciones);
		
		em.getTransaction().begin();
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
