package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.sun.jersey.api.client.ClientResponse;

import tpAnual.Mapa;
import tpAnual.POIs.Banco;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.externo.mocks.MockBajaPoi;
import tpAnual.externo.sistemasExternos.PoiAEliminarDTO;
import tpAnual.externo.sistemasExternos.UrlExterna;
import tpAnual.util.Reseter;
import tpAnual.util.wrapper.PointWrapper;

public class TestProcesoBajaPoi implements WithGlobalEntityManager{
	
	protected static Set<String> tags = new HashSet<String>();
	protected static PointWrapper ubicacion = new PointWrapper(54, 10);
	protected static Banco banco = new Banco(ubicacion,"Santander rio",new HashSet<String>());
	
	protected static Poi poi;
	protected static Poi poi2;
	protected static Poi poi3;
	
	private BajaPoiAdapter bpAdapter = new BajaPoiAdapter();
	private ProcesoBajaPoi procesoBaja = new ProcesoBajaPoi();
	private UrlExterna urlExt = new UrlExterna("http://demo3537367.mockable.io/trash","pois");
	private List<PoiAEliminarDTO> poisAEliminar = new ArrayList<PoiAEliminarDTO>();
	private MockBajaPoi mockbp = new MockBajaPoi();
	
	
	@Before
	public void init(){
		Reseter.resetSingletons();
		entityManager().getTransaction().begin();
		
		IntStream.range(0,126).forEach(i -> Mapa.getInstance().alta(new Banco(ubicacion, "", tags)));
		poi2 = new Banco(ubicacion, "", tags);
		poi = new Banco(ubicacion, "", tags);
		poi3 = new Banco(ubicacion, "", tags);
		Mapa.getInstance().alta(poi);
		Mapa.getInstance().alta(poi2);
		Mapa.getInstance().alta(poi3);
	}
	
	@After
	public void finalizar(){
		entityManager().getTransaction().rollback();
	}
	
	
	@Test
	public void testAndaBienLaUrl(){
		ClientResponse response = urlExt.consultarUrl("", "");
        Assert.assertEquals(200,response.getStatus(),0);
	}
	
	@Test 
	public void testAndaMalLaUrl(){
		ClientResponse response = urlExt.consultarUrl("", "");
        Assert.assertNotEquals(400, response.getStatus(),0);
	}
	
	@Test
	public void detectaPoisAEliminar(){
		procesoBaja.realizarProceso();
		Assert.assertEquals(2,procesoBaja.poisExternos.size(),0);
	}
	
	 @Test
	    public void consultarConFiltro() {
	        poisAEliminar = bpAdapter.consultar();
	        Assert.assertEquals(123,poisAEliminar.get(0).getId(),0);
	    }
	 
	 @Test
	    public void consultarConFiltro2() {
	        poisAEliminar = bpAdapter.consultar();
	        Assert.assertEquals(122,poisAEliminar.get(1).getId(),0);
	    }
	 
	 @Test
	    public void consultarConFiltro3() throws Exception {
	        poisAEliminar = bpAdapter.consultar();
	        Assert.assertEquals(2,poisAEliminar.size(),0);
	    }
	
	 @Test
	 public void testConMock(){
		 poisAEliminar = mockbp.consultar();
		 Assert.assertEquals(1,poisAEliminar.size(),0);
	 }
	 
	 @Test
	 public void testConMock2(){
		 poisAEliminar = mockbp.consultar();
		 Assert.assertEquals(1,poisAEliminar.get(0).getId(),0);
	 }
	 

}
