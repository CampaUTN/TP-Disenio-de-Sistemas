package tpAnual;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import org.uqbar.geodds.Point;

public class TestCercania {
	
	private Point puntoComunaA = new Point(0,0);
	private Point puntoComunaB = new Point(20,60);
	private Point puntoComunaC = new Point(50,0);
	
	private Set<String> tags = new HashSet<String>();
	private Cgp cgp = new Cgp(puntoComunaA,puntoComunaB,puntoComunaC);
	private Point ubicacionPoi = new Point(20, 10);
	
	private Poi poi = new Poi(cgp, ubicacionPoi, "107", tags);
	
	private Point puntoDeBusqueda = new Point(21,11);
	
	@Before
	public void init() {
		
	}

	@Test
	public void cgpEstaCercaPorComuna() {
		Assert.assertTrue(poi.estaCerca(puntoDeBusqueda));
		
	}
}
