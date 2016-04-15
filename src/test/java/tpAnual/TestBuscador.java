import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.*;

public class TestBuscador {
	private Set<String> tags = new HashSet<String>();
	private Colectivo tipo = new Colectivo();
	private Poi poi = new Poi(tipo, "107", tags);
	List<Poi> listaPois = new ArrayList<Poi>();
	Mapa mapa = new Mapa();
	@Before
	public void init(){
		poi.agregarTag("107");
		poi.agregarTag("colectivo");
		mapa.agregarPoi(poi);
	}
	@Test
	public void busca107SegunLibre(){
		int i = mapa.buscarPoi("107").size();
		Assert.assertEquals(1,i);
	}
}
