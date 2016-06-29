package tpAnual;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.procesos.IPlanificador;
import tpAnual.procesos.Lanzador;
import tpAnual.procesos.ProcesoActivadorAcciones;
import tpAnual.procesos.ManejadorDeErrores;

public class TestLanzador {
	private Lanzador lanzador;	
	Set<Terminal> terminales = new HashSet<>();
	Set<String> activar = new HashSet<>();
	Set<String> desactivar = new HashSet<>();
		
	private ProcesoActivadorAcciones proceso1;
	private LocalComercialAdapter proceso2;
		
	private IPlanificador planificador; 
	
	@Before
	public void init(){
		ManejadorDeErrores.resetSingleton();
		Lanzador.resetSingleton();
		lanzador = Lanzador.getInstance();
		
		terminales.add(new Terminal(0));
		activar.add("hola");
		desactivar.add("chau");
				
		proceso1 = ProcesoActivadorAcciones.EnComuna(0, activar, desactivar);
		proceso2 = new LocalComercialAdapter();
		
		planificador = Mockito.mock(IPlanificador.class);
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
	
	@Test
	public void elProcesoSeEjecutaBien(){
		lanzador.ejecutarProceso(proceso1);
		Assert.assertEquals(0,proceso1.getIntentos(),0);
	}
		
	@Test
	public void elProcesoSeEjecutaConExcepcion(){
		//TODO realizar test
	}	
	
	@Test
	public void elProcesoSeEjecutaALaHoraIndicada(){
		//TODO realizar test
	}	
	
	@Test
	public void seEjecutaUnProcesoALaVez(){
		//TODO realizar test
	}	
}