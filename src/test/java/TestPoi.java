import java.util.HashSet;
import java.util.Set;
import org.junit.*;

import tpAnual.*; //TODO mover esta clase al paquete tpAnual. Borrarlo y crear un archivo identico no me parece copado :(

public class TestPoi {

	private Set<String> tags = new HashSet<String>();
	private Colectivo tipo = new Colectivo();
	private Poi poi = new Poi(tipo, "107", tags);
	
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
