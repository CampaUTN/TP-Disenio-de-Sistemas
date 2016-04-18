package tpAnual;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import org.uqbar.geodds.Point;

public class TestPoi {

	private Set<String> tags = new HashSet<String>();
	private Colectivo tipo = new Colectivo();
	private Point ubicacion = new Point(54, 10);
	private Poi poi = new Poi(tipo, ubicacion, "107", tags);
	
	@Before
	public void init()
	{
		poi.agregarTag("mejor");
		poi.agregarTag("colectivo");
	}
	
	@Test
	public void tieneTagsAgregados()
	{
		Assert.assertTrue(poi.tieneTag("mejor"));
		Assert.assertTrue(poi.tieneTag("colectivo"));
	}
	
	@Test
	public void suNombreEsUnTag()
	{
		Assert.assertTrue(poi.tieneTag("107"));
	}
	
	@Test
	public void losTagsSeAgregan()
	{
		poi.agregarTag("transporte");
		Assert.assertTrue(poi.tieneTag("transporte"));
	}
	
}
