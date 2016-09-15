package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Mapa;
import tpAnual.SingletonReseter;
import tpAnual.Terminal;

public class TestProcesoActivadorAcciones {
	private List<Terminal> terminales = new ArrayList<Terminal>();
	private List<AccionTerminal> acciones = new ArrayList<AccionTerminal>();
	
	private Terminal terminal0 = new Terminal(0);
	private Terminal terminal1 = new Terminal(99);
	private Terminal terminal2 = new Terminal(2);
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	private Mapa mapa;
	
	
	@Before
	public void init(){
		SingletonReseter.resetAll();
		entityManager.getTransaction().begin();
		mapa = Mapa.getInstance();

		terminal0.setNumeroComuna(5);
		terminal1.setNumeroComuna(3);
		terminal2.setNumeroComuna(5);
		
		mapa.agregarTerminal(terminal0);
		mapa.agregarTerminal(terminal1);
		mapa.agregarTerminal(terminal2);
		
		acciones.add(new ActivarMails());
		acciones.add(new DesactivarRegistros());
	}
	
	@After
	public void clean(){
		entityManager.getTransaction().rollback();
	}
	
	@Test
	public void testActivadorEnComunasActivadMails(){
		terminal0.desactivarMails();
		
		ActivacionPorComuna activador = new ActivacionPorComuna(5, acciones);
		activador.ejecutar();
		
		Assert.assertTrue(terminal0.tieneMailsActivados());
	}
	
	@Test
	public void testActivadorEnComunasNoCambiaSiNoEsComuna(){
		terminal1.desactivarMails();
		
		ActivacionPorComuna activador = new ActivacionPorComuna(5, acciones);
		activador.ejecutar();
		
		Assert.assertFalse(terminal1.tieneMailsActivados());
	}
	
	@Test
	public void testActivadorEnComunasDesactivaRegistro(){
		terminal0.activarRegistros();
		
		ActivacionPorComuna activador = new ActivacionPorComuna(5, acciones);
		activador.ejecutar();
		
		Assert.assertFalse(terminal0.tieneRegistrosActivados());
	}
	
	@Test
	public void testActivadorEnComunasNoDesactivaRegistroSiNoComuna(){
		terminal1.activarRegistros();
		
		ActivacionPorComuna activador = new ActivacionPorComuna(5, acciones);
		activador.ejecutar();
		
		Assert.assertTrue(terminal1.tieneRegistrosActivados());
	}
	
	@Test
	public void testActivadorEnAlgunosLosDesactiva(){
		List<Terminal> algunos = new ArrayList<Terminal>();
	
		algunos.add(terminal0);
		terminal0.activarRegistros();
		
		ActivacionSeleccion activador = new ActivacionSeleccion(algunos, acciones);
		activador.ejecutar();
		
		Assert.assertFalse(terminal0.tieneRegistrosActivados());
	}
	
	@Test
	public void testActivadorEnAlgunosNoDesactivaSiNoEsta(){
		List<Terminal> algunos = new ArrayList<Terminal>();
	
		algunos.add(terminal0);
		terminal0.activarRegistros();
		terminal1.activarRegistros();
		
		ActivacionSeleccion activador = new ActivacionSeleccion(algunos, acciones);
		activador.ejecutar();
		
		Assert.assertTrue(terminal1.tieneRegistrosActivados());
	}
	
	@Test
	public void testActivadorEnTodosActiva(){
		terminal0.desactivarMails();
		terminal1.desactivarMails();
		terminal2.desactivarMails();
		
		terminal0.activarRegistros();
		terminal1.activarRegistros();
		terminal2.activarRegistros();
		
		ActivacionEnTodas activador = new ActivacionEnTodas(acciones);
		activador.ejecutar();
		
		Assert.assertTrue(terminal0.tieneMailsActivados());
		Assert.assertTrue(terminal1.tieneMailsActivados());
		Assert.assertTrue(terminal2.tieneMailsActivados());
	}
	
	@Test
	public void testActivadorEnTodosDesactiva(){
		terminal0.desactivarMails();
		terminal1.desactivarMails();
		terminal2.desactivarMails();
		
		terminal0.activarRegistros();
		terminal1.activarRegistros();
		terminal2.activarRegistros();
		
		ActivacionEnTodas activador = new ActivacionEnTodas(acciones);
		activador.ejecutar();
		
		Assert.assertFalse(terminal0.tieneRegistrosActivados());
		Assert.assertFalse(terminal1.tieneRegistrosActivados());
		Assert.assertFalse(terminal2.tieneRegistrosActivados());		
	}
	
	@Test
	public void testActivadorEnComunaSoloActivoRegistro(){
		terminal0.desactivarRegistros();
		terminal0.desactivarMails();
		

		List<AccionTerminal> acciones2 = new ArrayList<AccionTerminal>();
		acciones2.add(new ActivarRegistros());
		
		ActivacionEnTodas activador = new ActivacionEnTodas(acciones2);
		activador.ejecutar();
		
		Assert.assertTrue(terminal0.tieneRegistrosActivados());
		Assert.assertFalse(terminal0.tieneMailsActivados());
	}
	
}
