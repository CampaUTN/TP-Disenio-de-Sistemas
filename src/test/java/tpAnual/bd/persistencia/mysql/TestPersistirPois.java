package tpAnual.bd.persistencia.mysql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.POIs.Cgp;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.util.wrapper.PointWrapper;

public class TestPersistirPois extends TestPersistenciaRelacional{
		
	private PointWrapper punto = new PointWrapper(10,10);
	private  Set<String> tags = new HashSet<String>();
	long id1, id2;
	
	private Poi poiPrueba;
	private Poi poiPrueba2;
	
	
	@Before
	public void init() {
		super.init();
				
		tags.add("cole");
		poiPrueba = new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		poiPrueba2 = new EstacionDeColectivo(punto,"Parada del 60",tags,60, "pilar");
		
		
		entityManager().persist(poiPrueba);		
		entityManager().persist(poiPrueba2);
		
		id1 = poiPrueba.getId();
		id2 = poiPrueba2.getId();	

	}
		
	@Test
	public void lasIdsSeAutogeneranSecuencialmente(){
		Assert.assertEquals(id2, id1+1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void todosLosPoisSePersisten(){
		List<Poi> poisBd = entityManager().createQuery("FROM Poi").getResultList();
		Assert.assertEquals(2,poisBd.size());
	}
	
	@Test
	public void losPoisConServicioSePersisten(){
		Poi negocio = new Negocio(punto,"mueblesSA",tags,"muebleria",10);
		
		PointWrapper puntoComunaA = new PointWrapper(0,0);
		PointWrapper puntoComunaB = new PointWrapper(20,60);
		PointWrapper puntoComunaC = new PointWrapper(50,0);
		
		List<PointWrapper> puntosComuna =  new ArrayList<PointWrapper>();
		
		puntosComuna.add(puntoComunaA);
		puntosComuna.add(puntoComunaB);
		puntosComuna.add(puntoComunaC);
		
		Poi cgp = new Cgp(punto, "Asistencia social", tags,puntosComuna);
		
		entityManager().persist(negocio);
		entityManager().persist(cgp);
		
		@SuppressWarnings("unchecked")
		List<Poi> poisBd2 = entityManager().createQuery("FROM Poi").getResultList();
		
		Assert.assertEquals(4,poisBd2.size(),0);
		
		entityManager().remove(entityManager().contains(negocio) ? negocio : entityManager().merge(negocio));
		entityManager().remove(entityManager().contains(cgp) ? cgp : entityManager().merge(cgp));
	}
	
	@Test
	public void lasIdsNoSeRepiten(){
		
		List<Poi> poisBd = entityManager().createQuery("FROM Poi where id= :unId", Poi.class)
				.setParameter("unId", poiPrueba.getId()).getResultList();
		
		Assert.assertEquals(1,poisBd.size());
	}
	
	@Test
	public void buscoPoiInexistenteYGeneraListaVacia(){
		List<Poi> poisBd = entityManager().createQuery("FROM Poi where poi_id= :unId", Poi.class)
				.setParameter("unId", 56l).getResultList();
		
		Assert.assertTrue(poisBd.isEmpty());
	}	

	@Test
	public void buscoPoiPorID(){
		
		List<Poi> busquedas = entityManager().createQuery("FROM EstacionDeColectivo where poi_id= :unId ", Poi.class).
			setParameter("unId", id1).getResultList();
	
		Assert.assertEquals(poiPrueba,busquedas.get(0));
	}

}
