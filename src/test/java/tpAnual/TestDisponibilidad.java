package tpAnual;

import java.time.DayOfWeek;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDisponibilidad {

	private DayOfWeek lunes = DayOfWeek.MONDAY;
	private DayOfWeek miercoles = DayOfWeek.WEDNESDAY;
	private DayOfWeek viernes = DayOfWeek.FRIDAY;
	private DayOfWeek domingo = DayOfWeek.SUNDAY;

	private Cgp centro;
	
	private Servicio rentas = new Servicio("Rentas");
	private Servicio creditos = new Servicio("Creditos");
	
	private Horario horarioManana = Horario.nuevoHorarioParaFranja(lunes,viernes,"10:00:30", "12:00");
	private Horario horarioTarde = Horario.nuevoHorarioParaFranja(lunes,viernes,"14:00", "18:00");
	private Horario horarioUnico = Horario.nuevoHorarioParaDia(miercoles,"09:00","12:00");
	
	
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
		
		Assert.assertTrue(centro.estaDisponible("Rentas",lunes, "10:01"));
	}
	
	@Test
	public void cuentanSegundos(){
		Assert.assertFalse(centro.estaDisponible("Rentas", lunes, "10:00"));
	}
	
	@Test
	public void estaEnElDiaUnico()	{
		Assert.assertTrue(centro.estaDisponible("Creditos",miercoles,"09:43"));
	}
	
	@Test
	public void estaDentroDeLaOtraFranjaHoraria(){
		
		Assert.assertTrue(centro.estaDisponible("Rentas",lunes, "15:01"));
	}
	
	@Test
	public void hayUnServicioDisponibleSinEscribirValor(){
		
		Assert.assertTrue(centro.estaDisponible(lunes, "10:01"));
	}
	
	@Test
	public void estaFueraPorLaHora(){
		Assert.assertFalse(centro.estaDisponible("Rentas",miercoles, "12:30"));
	}
	
	@Test
	public void estaFueraPorElDia(){
		Assert.assertFalse(centro.estaDisponible(domingo, "11:00"));
	}
}

