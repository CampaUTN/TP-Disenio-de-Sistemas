package tpAnual.bd.persistencia;

import java.time.DayOfWeek;
import java.time.LocalTime;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import tpAnual.Horario;


public class TestPersistenciaHorarios {
	private static DayOfWeek lunes = DayOfWeek.MONDAY;
	private static DayOfWeek miercoles = DayOfWeek.WEDNESDAY;
	private static DayOfWeek viernes = DayOfWeek.FRIDAY;
	
	private static Horario horarioManana = Horario.nuevoHorarioParaFranja(lunes,viernes,LocalTime.parse("10:00:30"), LocalTime.parse("12:00"));
	private static Horario horarioUnico = Horario.nuevoHorarioParaDia(miercoles,LocalTime.parse("09:00"),LocalTime.parse("12:00"));
	
	private static EntityManager em = PerThreadEntityManagers.getEntityManager();
	static long id1;
	static long id2;

	@BeforeClass
	public static void init() {
		em.getTransaction().begin();
		em.persist(horarioUnico);
		em.persist(horarioManana);

		id1 = horarioUnico.getId();
		id2 = horarioManana.getId();

	}
	
	@AfterClass
	public static void clear() {
		em.getTransaction().rollback();
	}
	
	
	@Test
	public void testId1(){
		Assert.assertEquals(1, id1, 0);
	}

	@Test
	public void testId2(){
		Assert.assertEquals(2, id2, 0);
	}
}
