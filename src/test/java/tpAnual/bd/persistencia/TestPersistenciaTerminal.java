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
import tpAnual.POIs.Poi;

public class TestPersistenciaTerminal {
	private static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	static long numTerminal1, numTerminal2;
	static Integer numComuna;
	
	private static List<Terminal> terminalBd = new ArrayList<Terminal>();
	
	private static Terminal terminalPrueba1 = new Terminal();
	private static Terminal terminalPrueba2 = new Terminal();
		
	@BeforeClass
	public static void init() {
		entityManager.getTransaction().begin();
		
		terminalPrueba1.activarRegistros();
		terminalPrueba1.activarMails();
		
		terminalPrueba2.activarRegistros();
		terminalPrueba2.activarMails();		
		

		entityManager.persist(terminalPrueba1);
	}
	
	@AfterClass
	public static void clear() {
		entityManager.getTransaction().rollback();
	}
	
	@Test
	public void sePersisteLaTerminal(){
		List<Terminal> terminales = entityManager.createQuery("FROM Terminal").getResultList();
	
		Assert.assertFalse(terminales.isEmpty());
		
	}
	
	@Test
	public void sePersisteMasDeUnaTerminal(){
		entityManager.persist(terminalPrueba2);
		List<Terminal> terminales = entityManager.createQuery("FROM Terminal").getResultList();
	
		Assert.assertEquals(terminales.size(),2);		
	}
	
	
	@Test
	public void lasIdsSeAutogeneranSecuencialmente(){
		entityManager.persist(terminalPrueba2);
		
        numTerminal1 = terminalPrueba1.getNumeroTerminal();
		numTerminal2 = terminalPrueba2.getNumeroTerminal();
		
		Assert.assertEquals(numTerminal2, numTerminal1+1);
	}
	
	@Test
	public void lasIdsNoSeRepiten(){
		List<Terminal> terminales = entityManager.createQuery("FROM Terminal where id= :unId", Terminal.class)
				.setParameter("unId", 1l).getResultList();
			
		Assert.assertEquals(terminales.size(),1);
	}

}