package tpAnual.batch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.*;

import tpAnual.SingletonReseter;
import tpAnual.batch.PlanificacionProceso;
import tpAnual.batch.Planificador;
import tpAnual.batch.procesos.ActivacionEnTodas;
import tpAnual.batch.procesos.Proceso;
import tpAnual.batch.procesos.ProcesoActualizarLocales;

public class TestPlanificador{

	private Planificador planificador = new Planificador();
	
	private Proceso proceso1;
	private ProcesoActualizarLocales proceso2;
	
//	private Set<String> procesosActivar = new HashSet<>();
//	private Set<String> procesosDesactivar = new HashSet<>();
	
	private LocalDateTime fechaYHora;
	private LocalTime hora;
	
	@Before
	public void init(){

		SingletonReseter.resetAll();
		proceso1 = new ActivacionEnTodas(null);
		proceso2 = new ProcesoActualizarLocales();
		fechaYHora = LocalDateTime.parse("2016-05-05T10:30");			
	}
	
	@After
	public void after(){
		Lanzador.resetSingleton();
	}
	
	@Test
	public void elProcesoSePlanifica(){
		planificador.programarProceso(proceso1, fechaYHora);
		Assert.assertFalse(planificador.getHorarios().isEmpty());
	}
	
	
	@Test
	public void noHayNingunProcesoParaEjecutar(){
		Assert.assertTrue(planificador.getHorarios().isEmpty());
	}
	
	@Test
	public void noHayNingunProcesoParaEjecutarAEstaHora(){
		LocalTime hora = LocalTime.parse("10:30");
		
		planificador.programarProceso(proceso1, fechaYHora);
		planificador.programarProcesoRutinario(proceso2, hora);
		
		List<PlanificacionProceso> horariosDisponibles = planificador.filtrarProcesos(LocalDateTime.parse("2016-07-07T23:10"));
		
		Assert.assertTrue(horariosDisponibles.isEmpty());
	}
	
		
	@Test
	public void seEjecutaUnProcesoALaFechaDada(){
		planificador.programarProceso(proceso1, fechaYHora);

		List<PlanificacionProceso> horariosDisponibles = planificador.filtrarProcesos(fechaYHora);
		Assert.assertTrue(horariosDisponibles.get(0).getProceso().equals(proceso1));
	}
	
	@Test
	public void seEjecutaUnProcesoALaHoraDada(){
		hora =  LocalTime.parse("10:30");
		planificador.programarProcesoRutinario(proceso2, hora);
		
		List<PlanificacionProceso> horariosDisponibles = planificador.filtrarProcesos(fechaYHora);
		
		Assert.assertTrue(horariosDisponibles.get(0).getProceso().equals(proceso2));
	}
	
	@Test
	public void seEjecutanDosProcesosEnLaMismaHora(){
		LocalTime hora = LocalTime.parse("10:30");
		
		planificador.programarProceso(proceso1, fechaYHora);
		planificador.programarProcesoRutinario(proceso2, hora);
		
		Assert.assertEquals(2, planificador.filtrarProcesos(fechaYHora).size());	
	}
}
