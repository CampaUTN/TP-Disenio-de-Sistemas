package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Cgp;
public class TestDisponibilidad extends TestSetup {

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
	private Banco frances = new Banco(ubicacion,"frances",new HashSet<String>());
	@Before
	public void init()	{	
		super.init();
		
		frances.agregarServicio(creditos);
		
		rentas.agregarHorario(horarioManana);
		rentas.agregarHorario(horarioTarde);
		rentas.agregarHorario(horarioUnico);
		
		creditos.agregarHorario(horarioUnico);
		
		centro.agregarServicio(rentas);
		centro.agregarServicio(creditos);
		
		negocio.agregarHorario(horarioManana);
		negocio.agregarHorario(horarioTarde);
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
	
	/*----Testeo horarios de Negocio------*/
	
	@Test
	public void negocioEstaDisponible(){
		Assert.assertTrue(negocio.estaDisponible(lunes,LocalTime.parse("10:01")));
	}
	
	@Test
	public void negocioNoEstaDisponibleMientrasCierra(){
		Assert.assertFalse(negocio.estaDisponible(lunes,LocalTime.parse("13:00")));
	}
	
	/*----Testeo horarios de Colectivo------*/
	@Test
	public void testColectivoDisponibleEnFinDeSemanaACualquierHora(){
		Assert.assertTrue(poi.estaDisponible(domingo,LocalTime.parse("23:59")));
	}
	
	@Test
	public void testColectivoDisponibleEnDiaDeSemanaACualquierHora(){
		Assert.assertTrue(poi.estaDisponible(lunes,LocalTime.parse("00:00")));
	}
	
	
	/*----Testeo horarios de Banco------*/
	@Test 
	public void noEstaDisponibleEnFinDeSemana(){
		Assert.assertFalse(frances.estaDisponible(domingo,LocalTime.parse("10:01")));
	}
	
	@Test 
	public void estaDisponibleEnDiaDeSemanaDespuesDeLasDiez(){
		Assert.assertTrue(frances.estaDisponible(miercoles,LocalTime.parse("10:01")));
	}

	@Test 
	public void noEestaDisponibleEnDiaDeSemanaAntesDeLasDiez(){
		Assert.assertFalse(frances.estaDisponible(lunes,LocalTime.parse("09:59")));
	}
	
	@Test 
	public void noEestaDisponibleEnDiaDeSemanaDespuesDeLasQuince(){
		Assert.assertFalse(frances.estaDisponible(viernes,LocalTime.parse("15:01")));
	}
	
}