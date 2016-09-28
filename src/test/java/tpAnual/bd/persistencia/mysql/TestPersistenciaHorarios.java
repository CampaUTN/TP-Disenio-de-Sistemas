package tpAnual.bd.persistencia.mysql;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.Horario;
import tpAnual.util.Reseter;


public class TestPersistenciaHorarios {
	private static DayOfWeek lunes = DayOfWeek.MONDAY;
	private static DayOfWeek miercoles = DayOfWeek.WEDNESDAY;
	private static DayOfWeek viernes = DayOfWeek.FRIDAY;
	
	private static Horario horarioManana = Horario.nuevoHorarioParaFranja(lunes,viernes,LocalTime.parse("10:00:30"), LocalTime.parse("12:00"));
	private static Horario horarioUnico = Horario.nuevoHorarioParaDia(miercoles,LocalTime.parse("09:00"),LocalTime.parse("12:00"));
	
	private static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	@BeforeClass
	public static void init() {
		Reseter.resetSingletons();
		entityManager.getTransaction().begin();
		
		entityManager.persist(horarioUnico);
		entityManager.persist(horarioManana);
	}
	
	@AfterClass
	public static void clear() {
		entityManager.getTransaction().rollback();
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
		List<Horario> terminales = entityManager.createQuery("FROM Horario").getResultList();
		Assert.assertFalse(terminales.isEmpty());		
	}	
}
