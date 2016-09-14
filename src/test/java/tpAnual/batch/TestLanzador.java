package tpAnual.batch;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.SingletonReseter;
import tpAnual.Terminal;
import tpAnual.batch.Lanzador;
import tpAnual.batch.procesos.AccionTerminal;
import tpAnual.batch.procesos.ActivacionPorComuna;
import tpAnual.batch.procesos.ActivarMails;
import tpAnual.batch.procesos.DesactivarRegistros;
import tpAnual.batch.procesos.FinEjecucion;
import tpAnual.batch.procesos.Proceso;
import tpAnual.batch.procesos.ProcesoActualizarLocales;

public class TestLanzador{
	private Lanzador lanzador;	
	private Set<Terminal> terminales = new HashSet<>();
	private Set<String> activar = new HashSet<>();
	private Set<String> desactivar = new HashSet<>();
	private Proceso proceso1;
	private Proceso proceso2;
	private Proceso proceso3;
			
	@Before
	public void init(){
		SingletonReseter.resetAll();
		lanzador = Lanzador.getInstance();
		
		terminales.add(new Terminal(0));
		activar.add("Mail");
		desactivar.add("Registro");
		proceso1 = new ActivacionPorComuna(0, null);
		proceso2 = new ProcesoActualizarLocales();
	}
	
	@After
	public void after(){
		Lanzador.resetSingleton();
	}
	
	@Test
    public void prueboQueTodasLasInstanciasSeanLaMisma() {
       	Lanzador instance1 = Lanzador.getInstance();
       	Lanzador instance2 = Lanzador.getInstance();
        Assert.assertEquals(instance1,instance2);
    }
		
	@Test
	public void agregoProcesosPolimorficamente(){
		lanzador.agregaAPendientes(proceso1);
		lanzador.agregaAPendientes(proceso2);
		lanzador.agregaAPendientes(proceso3);
		Assert.assertEquals(3,lanzador.getPendientes().size());
	}
	

	@Test
	public void elProcesoSeEjecutaBien(){
		Set<AccionTerminal> acciones = new HashSet<AccionTerminal>();
		acciones.add(new ActivarMails());
		acciones.add(new DesactivarRegistros());
		proceso1 = new ActivacionPorComuna(5, acciones);
		lanzador.ejecutarProceso(proceso1);
		Assert.assertEquals(FinEjecucion.CORRECTO,proceso1.getEstado());
	}
	
	@Test
	public void elProcesoSeEjecutaMal(){
		proceso1 = new ActivacionPorComuna(0, null);
		lanzador.ejecutarProceso(proceso1);
		Assert.assertEquals(FinEjecucion.FALLIDO,proceso1.getEstado());
	}
	
	@Test
	public void seEjecutaUnProcesoALaVez(){
		lanzador.solicitudEjecucion(proceso1);
		Assert.assertFalse(lanzador.estaEjecutando());
	}	
	
	@Test
	public void seAgreganProcesosPendientes(){
		lanzador.agregaAPendientes(proceso1);
		lanzador.agregaAPendientes(proceso2);

		Assert.assertFalse(lanzador.getPendientes().isEmpty());
	}
	
	@Test
	public void seEjecutanProcesosPendientes(){
		lanzador.agregaAPendientes(proceso1);		
		lanzador.solicitudEjecucion(proceso2);
		Assert.assertTrue(lanzador.getPendientes().isEmpty());
	}
}