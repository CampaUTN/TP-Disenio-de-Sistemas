package tpAnual;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import org.uqbar.geodds.Point;

public class TestBuscador {
	private Set<String> tags = new HashSet<String>();
	private EstacionDeColectivo tipo = new EstacionDeColectivo();
	private Banco frances= new Banco();
	private Point ubicacion = new Point(54, 10);
	private Poi poi = new Poi(tipo, ubicacion, "107", tags);
	private Poi poi2 = new Poi(tipo, ubicacion, "108", tags);
	private Mapa mapa = new Mapa();
	private Servicio servicio = new Servicio("rentas");
	
	@Before
	public void init() {
		poi.agregarTag("107");
		poi.agregarTag("colectivo");
		mapa.alta(poi);

		poi2.agregarTag("108");
		poi2.agregarTag("colectivo");
		mapa.alta(poi2);
	}

	@Test
	public void testUnaBusquedaParaLosTresOrigenes(){
		Assert.assertEquals(6, mapa.buscar("colectivo").size(),0); //2 del init, 2 de bancos, 2 de cpos
	}
	
//	@Test
//	public void buscaEsUnColecEnVezDeColectivoYSonDos() {
//		Assert.assertEquals(2, mapa.buscarPoi("es un colec").size());
//	}
//
//	@Test
//	public void busca10EnVezDe107y108YEncuentraDos() {
//		Assert.assertEquals(2, mapa.buscarPoi("10").size());
//	}
//
//	@Test
//	public void siNoContienenTagNoEncuentra() {
//		Assert.assertEquals(0, mapa.buscarPoi("colectivos").size());
//	}
//	
//	@Test
//	public void losServiciosSonTags()
//	{
//		frances.agregarServicio(servicio);
//		Poi poi3 = new Poi(frances, ubicacion, "108", tags);
//		Assert.assertTrue(poi3.tieneTag("rentas"));
//	}
}
