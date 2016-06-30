package tpAnual;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import tpAnual.procesos.HorarioProceso;
import tpAnual.procesos.Planificador;
import tpAnual.procesos.ProcesoActivadorAcciones;

public class TestPlanificador extends TestSetup{

	private Planificador planificador = new Planificador();
	private HorarioProceso planificacion1;
	
	private ProcesoActivadorAcciones proceso1;
	
	private Set<String> procesosActivar = new HashSet<>();
	private Set<String> procesosDesactivar = new HashSet<>();
	
	private LocalDate fecha;
	private LocalTime hora;
	
	@Before
	public void init(){
	
		procesosActivar.add("notificar");
		proceso1 = ProcesoActivadorAcciones.EnTodos(procesosActivar, procesosDesactivar);
		
		fecha = LocalDate.parse("2016-05-05");
		hora =  LocalTime.parse("10:30");
		planificacion1 = HorarioProceso.horarioEspecifico(proceso1, fecha,hora);
		
		planificador.programarProceso(proceso1, fecha,hora);
	}
	
	@Test
	public void elProcesoSeEjecutaEnLaFechaYHoraDada(){
		
		Assert.assertTrue(planificador.tieneQueEjecutarse(planificacion1, fecha,hora));
	}
	
	@Test
	public void elProcesoNoSeEjecutaEnLaFechaDada(){
		fecha = LocalDate.parse("2017-05-05");
		hora =  LocalTime.parse("10:30");
		Assert.assertFalse(planificador.tieneQueEjecutarse(planificacion1, fecha,hora));
	}
	
	@Test
	public void elProcesoNoSeEjecutaEnLaHoraDada(){
		fecha = LocalDate.parse("2016-05-05");
		hora =  LocalTime.parse("13:56");
		Assert.assertFalse(planificador.tieneQueEjecutarse(planificacion1,  fecha,hora));
	}
	
}
