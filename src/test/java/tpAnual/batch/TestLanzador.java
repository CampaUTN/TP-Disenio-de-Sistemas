package tpAnual.batch;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Terminal;
import tpAnual.batch.Lanzador;

import tpAnual.batch.procesos.FinEjecucion;
import tpAnual.batch.procesos.ProcesoActivadorAcciones;
import tpAnual.batch.procesos.ProcesoActualizarLocales;

public class TestLanzador {
	private Lanzador lanzador;	
	Set<Terminal> terminales = new HashSet<>();
	Set<String> activar = new HashSet<>();
	Set<String> desactivar = new HashSet<>();
		
	private ProcesoActivadorAcciones proceso1;
	private ProcesoActualizarLocales proceso2;
			
	@Before
	public void init(){
		Lanzador.resetSingleton();
		lanzador = Lanzador.getInstance();
		
		terminales.add(new Terminal(0));
		activar.add("Mail");
		desactivar.add("Registro");
				
		proceso1 = ProcesoActivadorAcciones.EnComuna(0, activar, desactivar);
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
		//TODO agregar otro tipo de proceso
		Assert.assertEquals(2,lanzador.getPendientes().size(),0);
	}
	
	// FALLA Y NO SE POR QUE
	@Test
	public void elProcesoSeEjecutaBien(){
		lanzador.ejecutarProceso(proceso1);
		Assert.assertEquals(FinEjecucion.CORRECTO,proceso1.getEstado());
	}
		
	@Test
	public void seEjecutaUnProcesoALaVez(){
		//TODO testear
	}	
}