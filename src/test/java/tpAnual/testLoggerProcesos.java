package tpAnual;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;

import tpAnual.batch.Lanzador;
import tpAnual.batch.ManejadorDeErrores;
import tpAnual.batch.observers.LoggerProcesos;
import tpAnual.batch.procesos.ProcesoActivadorAcciones;

public class testLoggerProcesos {
	private Set<Terminal> terminales;
	private Set<String> activar;
	private Set<String> desactivar;
	private int limite;
	private ProcesoActivadorAcciones proceso;
	
	@Before
	public void init(){
		ManejadorDeErrores.resetSingleton();
		Lanzador.resetSingleton();
		LoggerProcesos.resetSingleton();
		terminales = new HashSet<>();
		activar = new HashSet<>();
		desactivar = new HashSet<>();
		limite=2;
		ManejadorDeErrores.getInstance().setLimite(limite);
		ManejadorDeErrores.getInstance().desactivarAvisoPorMail();
		terminales.add(new Terminal(0));
		activar.add("hola");
		desactivar.add("chau");
				
		proceso = ProcesoActivadorAcciones.EnComuna(0, activar, desactivar);
	}
	
	@After
	public void finalizar(){
		ManejadorDeErrores.resetSingleton();
		Lanzador.resetSingleton();
		LoggerProcesos.resetSingleton();
	}
	
	// TODO 
	//@Test
	public void registraEventosFallidos(){
		proceso = ProcesoActivadorAcciones.EnTodos(activar, new HashSet<>());
		ManejadorDeErrores.getInstance().setLimite(2);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(3, LoggerProcesos.getInstance().getResultados().size(),0);
	}
	
	//@Test
	public void registraEventosExitosos(){
		proceso = ProcesoActivadorAcciones.EnTodos(activar, desactivar);
		ManejadorDeErrores.getInstance().setLimite(2);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(1, LoggerProcesos.getInstance().getResultados().size(),0);
	}
}
