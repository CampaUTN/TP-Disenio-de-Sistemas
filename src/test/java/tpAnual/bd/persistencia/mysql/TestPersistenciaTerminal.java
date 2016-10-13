package tpAnual.bd.persistencia.mysql;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Terminal;

public class TestPersistenciaTerminal extends TestPersistenciaRelacional{
	
	private long numTerminal1, numTerminal2;
		
	private Terminal terminalPrueba1 = new Terminal();
	private Terminal terminalPrueba2 = new Terminal();
		
	@Before
	public void init() {
		super.init();
		
		terminalPrueba1.activarRegistros();
		terminalPrueba1.activarMails();
		
		terminalPrueba2.activarRegistros();
		terminalPrueba2.activarMails();		
		
		entityManager().persist(terminalPrueba1);
	}
			
	@Test
	public void sePersisteLaTerminal(){
		@SuppressWarnings("unchecked")
		List<Terminal> terminales = entityManager().createQuery("FROM Terminal").getResultList();
	
		Assert.assertFalse(terminales.isEmpty());
		
	}
	
	@Test
	public void sePersisteMasDeUnaTerminal(){
		entityManager().persist(terminalPrueba2);
		@SuppressWarnings("unchecked")
		List<Terminal> terminales = entityManager().createQuery("FROM Terminal").getResultList();
	
		Assert.assertEquals(terminales.size(),2);		
	}
	
	
	@Test
	public void lasIdsSeAutogeneranSecuencialmente(){
		entityManager().persist(terminalPrueba2);
		
        numTerminal1 = terminalPrueba1.getNumeroTerminal();
		numTerminal2 = terminalPrueba2.getNumeroTerminal();
		
		Assert.assertEquals(numTerminal2, numTerminal1+1);
	}
	
	@Test
	public void buscoTerminalPorID(){
		
		long id1 = terminalPrueba1.getNumeroTerminal();
		
		List<Terminal> busquedas = entityManager().createQuery("FROM Terminal where numeroTerminal= :unId ", Terminal.class).
				setParameter("unId", id1).getResultList();
		
		Assert.assertEquals(terminalPrueba1,busquedas.get(0));
	}
}