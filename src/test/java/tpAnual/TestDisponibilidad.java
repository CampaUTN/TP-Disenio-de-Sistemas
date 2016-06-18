package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.POIs.Cgp;

public class TestDisponibilidad {

	private DayOfWeek lunes = DayOfWeek.MONDAY;
	private DayOfWeek miercoles = DayOfWeek.WEDNESDAY;
	private DayOfWeek viernes = DayOfWeek.FRIDAY;
	private DayOfWeek domingo = DayOfWeek.SUNDAY;

	private Cgp centro;
	
	private Servicio rentas = new Servicio("Rentas");
	private Servicio creditos = new Servicio("Creditos");
	
	private Horario horarioManana = Horario.nuevoHorarioParaFranja(lunes,viernes,LocalTime.parse("10:00:30"), LocalTime.parse("12:00"));
	private Horario horarioTarde = Horario.nuevoHorarioParaFranja(lunes,viernes,LocalTime.parse("14:00"), LocalTime.parse("18:00"));
	private Horario horarioUnico = Horario.nuevoHorarioParaDia(miercoles,LocalTime.parse("09:00"),LocalTime.parse("12:00"));
	
	
	@Before
	public void init()
	{
		centro = new Cgp(null);
		
		rentas.agregarHorario(horarioManana);
		rentas.agregarHorario(horarioTarde);
		rentas.agregarHorario(horarioUnico);
		
		creditos.agregarHorario(horarioUnico);
		
		centro.agregarServicio(rentas);
		centro.agregarServicio(creditos);
	}
	
	@Test
	public void estaDentroDeLaFranjaHoraria(){
		
		Assert.assertTrue(centro.estaDisponibleConServicio("Rentas",lunes, LocalTime.parse("10:01")));
	}
	
	@Test
	public void cuentanSegundos(){
		Assert.assertFalse(centro.estaDisponibleConServicio("Rentas", lunes, LocalTime.parse("10:00")));
	}
	
	@Test
	public void estaEnElDiaUnico()	{
		Assert.assertTrue(centro.estaDisponibleConServicio("Creditos",miercoles,LocalTime.parse("09:43")));
	}
	
	@Test
	public void estaDentroDeLaOtraFranjaHoraria(){
		
		Assert.assertTrue(centro.estaDisponibleConServicio("Rentas",lunes, LocalTime.parse("15:01")));
	}
	
	@Test
	public void hayUnServicioDisponibleSinEscribirValor(){
		
		Assert.assertTrue(centro.estaDisponible(lunes,LocalTime.parse("10:01")));
	}
	
	@Test
	public void estaFueraPorLaHora(){
		Assert.assertFalse(centro.estaDisponibleConServicio("Rentas",miercoles, LocalTime.parse("12:30")));
	}
	
	@Test
	public void estaFueraPorElDia(){
		Assert.assertFalse(centro.estaDisponible(domingo, LocalTime.parse("11:00")));
	}
}

