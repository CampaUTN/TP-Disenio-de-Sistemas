package tpAnual;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import tpAnual.Banco;
import tpAnual.Cgp;
import tpAnual.Horario;
import tpAnual.Servicio;

public class TestDisponibilidad {


	DayOfWeek lunes = DayOfWeek.MONDAY;
	DayOfWeek martes = DayOfWeek.TUESDAY;
	DayOfWeek miercoles = DayOfWeek.WEDNESDAY;
	DayOfWeek jueves = DayOfWeek.THURSDAY;
	DayOfWeek viernes = DayOfWeek.FRIDAY;
	DayOfWeek sabado = DayOfWeek.SATURDAY;
	DayOfWeek domingo = DayOfWeek.SUNDAY;
	
	
	Banco frances= new Banco();

	private Point puntoComunaA = new Point(0,0);
	private Point puntoComunaB = new Point(20,60);
	private Point puntoComunaC = new Point(50,0);
	
	private Cgp centro = new Cgp(puntoComunaA,puntoComunaB,puntoComunaC);
	
	Servicio rentas = new Servicio("Rentas");
		
	Horario horarioMañana = new Horario(lunes, viernes, "10:00", "12:00");
	Horario horarioTarde = new Horario (lunes, viernes,"14:00","18:00");
	
	
	Servicio creditos = new Servicio("Creditos");
	Horario horarioUnico = new Horario(miercoles,"09:00","12:00");
	
	@Before
	public void init()
	{
		rentas.agregarHorario(horarioMañana);
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
	public void estaEnElDiaUnico()	{
		Assert.assertTrue(centro.estaDisponible("Creditos",miercoles,"09;43"));
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

