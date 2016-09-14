package tpAnual.bd.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.Terminal;

public class TestPersistenciaTerminal {
	private static EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	static long numTerminal1, numTerminal2;
	static Integer numComuna;
	
	private static List<Terminal> terminalBd = new ArrayList<Terminal>();
	
	@BeforeClass
	public static void init() {
		em.getTransaction().begin();
		
		Terminal terminalPrueba1 = new Terminal();
		Terminal terminalPrueba2 = new Terminal();
		terminalPrueba1.activarRegistros();
		terminalPrueba1.activarMails();
		terminalPrueba2.activarRegistros();
		terminalPrueba2.activarMails();
		em.persist(terminalPrueba1);
		em.persist(terminalPrueba2);
        numTerminal1 = terminalPrueba1.getNumeroTerminal();
		numTerminal2 = terminalPrueba2.getNumeroTerminal();
		terminalBd = em.createQuery("FROM Terminal").getResultList();
		
	}
	
	@AfterClass
	public static void clear() {
		em.getTransaction().rollback();
	}
	
	@Test
	public void lasIdsSeAutogeneranSecuencialmente(){
		Assert.assertEquals(numTerminal2, numTerminal1+1, 0);
		
		
	}
	
}
