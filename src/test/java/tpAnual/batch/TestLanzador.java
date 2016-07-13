package tpAnual.batch;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Terminal;
import tpAnual.batch.Lanzador;
import tpAnual.batch.procesos.ActivacionPorComuna;
import tpAnual.batch.procesos.FinEjecucion;
import tpAnual.batch.procesos.ProcesoActualizarLocales;
import tpAnual.batch.procesos.ProcesoBajaPoi;

public class TestLanzador{
	private Lanzador lanzador;	
	private Set<Terminal> terminales = new HashSet<>();
	private Set<String> activar = new HashSet<>();
	private Set<String> desactivar = new HashSet<>();
		
//	private ProcesoActivadorAcciones proceso1;
	private ActivacionPorComuna proceso1;
	private ProcesoActualizarLocales proceso2;
	private ProcesoBajaPoi proceso3;
			
	@Before
	public void init(){
		Lanzador.resetSingleton();
		lanzador = Lanzador.getInstance();
		
		terminales.add(new Terminal(0));
		activar.add("Mail");
		desactivar.add("Registro");
				
//		proceso1 = ProcesoActivadorAcciones.EnComuna(0, activar, desactivar);
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
	
	// FALLA Y NO SE POR QUE
	@Test
	public void elProcesoSeEjecutaBien(){
		lanzador.ejecutarProceso(proceso1);
		Assert.assertEquals(FinEjecucion.CORRECTO,proceso1.getEstado());
	}
	
//	@Test PORQUE TIENE QUE EJECUTARSE MAL???
//	public void elProcesoSeEjecutaMal(){
//		proceso1 = new ActivacionPorComuna(0, null);
//		lanzador.ejecutarProceso(proceso1);
//		Assert.assertEquals(FinEjecucion.FALLIDO,proceso1.getEstado());
//	}
	
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