package tpAnual.bd.persistencia.mysql;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Horario;

public class TestPersistenciaHorarios extends TestPersistenciaRelacional {
	private DayOfWeek lunes = DayOfWeek.MONDAY;
	private DayOfWeek miercoles = DayOfWeek.WEDNESDAY;
	private DayOfWeek viernes = DayOfWeek.FRIDAY;
	
	private Horario horarioManana = Horario.nuevoHorarioParaFranja(lunes,viernes,LocalTime.parse("10:00:30"), LocalTime.parse("12:00"));
	private Horario horarioUnico = Horario.nuevoHorarioParaDia(miercoles,LocalTime.parse("09:00"),LocalTime.parse("12:00"));
	
	@Before
	public void init() {
		super.init();
		
		entityManager().persist(horarioUnico);
		entityManager().persist(horarioManana);
	}
		
	@Test
	public void testLosIdsSonIncrementales(){
		long id1 = horarioUnico.getId();
		long id2 = horarioManana.getId();
		
		Assert.assertEquals(id2, id1+1);
	}
	
	@Test
	public void sePersisteElHorario(){
		@SuppressWarnings("unchecked")
		List<Horario> terminales = entityManager().createQuery("FROM Horario").getResultList();
		Assert.assertFalse(terminales.isEmpty());		
	}	
}