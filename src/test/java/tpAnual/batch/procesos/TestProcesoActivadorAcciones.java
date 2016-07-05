package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.batch.procesos.ProcesoActivadorAcciones;

public class TestProcesoActivadorAcciones {
	Set<Terminal> terminales = new HashSet<>();
	Set<String> activar = new HashSet<>();
	Set<String> desactivar = new HashSet<>();
	
	private ProcesoActivadorAcciones activador;
	
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
		
		activar.add("Mail");
		desactivar.add("Registro");
	}
	
	@Test
	public void testActivadorEnComunasActivadMails(){
		terminal1.desactivarMails();
		
		activador = ProcesoActivadorAcciones.EnComuna(5, activar, desactivar);
		activador.realizarProceso();
		
		Assert.assertTrue(terminal0.tieneMailsActivados());
	}
	
	@Test
	public void testActivadorEnComunasNoCambiaSiNoEsComuna(){
		terminal1.desactivarMails();
		
		activador = ProcesoActivadorAcciones.EnComuna(5, activar, desactivar);
		activador.realizarProceso();
		
		Assert.assertFalse(terminal1.tieneMailsActivados());
	}
	
	@Test
	public void testActivadorEnComunasDesactivaRegistro(){
		terminal0.activarRegistros();
		
		activador = ProcesoActivadorAcciones.EnComuna(5, activar, desactivar);
		activador.realizarProceso();
		
		Assert.assertFalse(terminal0.tieneRegistrosActivados());
	}
	
	@Test
	public void testActivadorEnComunasNoDesactivaRegistroSiNoComuna(){
		terminal1.activarRegistros();
		
		activador = ProcesoActivadorAcciones.EnComuna(5, activar, desactivar);
		activador.realizarProceso();
		
		Assert.assertTrue(terminal1.tieneRegistrosActivados());
	}
	
	@Test
	public void testActivadorEnAlgunosLosDesactiva(){
		Set<Terminal> algunos = new HashSet<Terminal>();
	
		algunos.add(terminal0);
		terminal0.activarRegistros();
		
		activador = ProcesoActivadorAcciones.EnSeleccion(algunos, activar, desactivar);
		activador.realizarProceso();
		
		Assert.assertFalse(terminal0.tieneRegistrosActivados());
	}
	
	@Test
	public void testActivadorEnAlgunosNoDesactivaSiNoEsta(){
		Set<Terminal> algunos = new HashSet<Terminal>();
	
		algunos.add(terminal0);
		terminal0.activarRegistros();
		terminal1.activarRegistros();
		
		activador = ProcesoActivadorAcciones.EnSeleccion(algunos, activar, desactivar);
		activador.realizarProceso();
		
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
		
		activador = ProcesoActivadorAcciones.EnTodos(activar, desactivar);
		activador.realizarProceso();
		
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
		
		activador = ProcesoActivadorAcciones.EnTodos(activar, desactivar);
		activador.realizarProceso();
		
		Assert.assertFalse(terminal0.tieneRegistrosActivados());
		Assert.assertFalse(terminal1.tieneRegistrosActivados());
		Assert.assertFalse(terminal2.tieneRegistrosActivados());		
	}
	
	@Test
	public void testActivadorEnComunaSoloActivoRegistro(){
		terminal0.desactivarRegistros();
		terminal0.desactivarMails();
		
		activar.remove("Mail");
		desactivar.remove("Registro");
		activar.add("Registro");
		
		activador = ProcesoActivadorAcciones.EnComuna(5,activar, desactivar);
		activador.realizarProceso();
		
		Assert.assertTrue(terminal0.tieneRegistrosActivados());
		Assert.assertFalse(terminal0.tieneMailsActivados());

	}
}
