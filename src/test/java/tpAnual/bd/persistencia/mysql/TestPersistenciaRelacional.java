package tpAnual.bd.persistencia.mysql;

import org.junit.After;
import org.junit.Before;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tpAnual.util.Reseter;

public abstract class TestPersistenciaRelacional implements WithGlobalEntityManager{
	//protected static EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	@Before
	public void init() {
		Reseter.resetSingletons();
		entityManager().getTransaction().begin();
	}
	
	@After
	public void clear() {
		entityManager().getTransaction().rollback();
		Reseter.resetSingletons();
	}	
}
