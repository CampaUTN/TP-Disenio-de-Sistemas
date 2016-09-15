package tpAnual.bd.persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.Terminal;
import tpAnual.busquedas.Busqueda;

public class TestPersistenciaTerminal {
	private static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	static long numTerminal1, numTerminal2;
	static Integer numComuna;
		
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
		@SuppressWarnings("unchecked")
		List<Terminal> terminales = entityManager.createQuery("FROM Terminal").getResultList();
	
		Assert.assertFalse(terminales.isEmpty());
		
	}
	
	@Test
	public void sePersisteMasDeUnaTerminal(){
		entityManager.persist(terminalPrueba2);
		@SuppressWarnings("unchecked")
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
	public void buscoTerminalPorID(){
		
		long id1 = terminalPrueba1.getNumeroTerminal();
		
		List<Terminal> busquedas = entityManager.createQuery("FROM Terminal where numeroTerminal= :unId ", Terminal.class).
				setParameter("unId", id1).getResultList();
		
		Assert.assertEquals(terminalPrueba1,busquedas.get(0));
	}

}