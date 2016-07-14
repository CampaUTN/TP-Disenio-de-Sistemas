package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Mapa;
import tpAnual.Terminal;

public class TestProcesoActivadorAcciones {
	Set<Terminal> terminales = new HashSet<Terminal>();
	Set<AccionTerminal> acciones = new HashSet<AccionTerminal>();
	
	Terminal terminal0 = new Terminal(0);
	Terminal terminal1 = new Terminal(1);
	Terminal terminal2 = new Terminal(2);
	
	Mapa mapa;
	
	
	@Before
	public void init(){
		Mapa.resetSingleton();
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
		Set<Terminal> algunos = new HashSet<Terminal>();
	
		algunos.add(terminal0);
		terminal0.activarRegistros();
		
		ActivacionSeleccion activador = new ActivacionSeleccion(algunos, acciones);
		activador.ejecutar();
		
		Assert.assertFalse(terminal0.tieneRegistrosActivados());
	}
	
	@Test
	public void testActivadorEnAlgunosNoDesactivaSiNoEsta(){
		Set<Terminal> algunos = new HashSet<Terminal>();
	
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
		

		Set<AccionTerminal> acciones2 = new HashSet<AccionTerminal>();
		acciones2.add(new ActivarRegistros());
		
		ActivacionEnTodas activador = new ActivacionEnTodas(acciones2);
		activador.ejecutar();
		
		Assert.assertTrue(terminal0.tieneRegistrosActivados());
		Assert.assertFalse(terminal0.tieneMailsActivados());
	}
	
}
