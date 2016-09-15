package tpAnual;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import org.uqbar.geodds.Point;

import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.utils.PointWrapper;

public class TestPoi {
	private Set<String> tags = new HashSet<String>();
	private PointWrapper ubicacion = new PointWrapper(54, 10);
	private Poi poi = new EstacionDeColectivo(ubicacion, "107", tags,0,"");

	
	@Before
	public void init() {
		SingletonReseter.resetAll();
		poi.agregarTag("mejor");
		poi.agregarTag("colectivo");
	}
	
	@Test
	public void seAgreganTodosLosTags(){
		Assert.assertEquals(2, poi.getTags().size(),0);
	}

	@Test
	public void tieneTagsAgregados() {
		Assert.assertTrue(poi.tieneTag("colectivo"));
	}

	@Test
	public void losTagsSeAgregan() {
		poi.agregarTag("transporte");
		Assert.assertTrue(poi.tieneTag("transporte"));
	}

}
