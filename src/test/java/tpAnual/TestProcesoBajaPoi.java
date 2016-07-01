package tpAnual;

import java.util.HashSet;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import com.sun.jersey.api.client.ClientResponse;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.procesos.ProcesoBajaPoi;
import tpAnual.externo.sistemasExternos.UrlExterna;

public class TestProcesoBajaPoi {
	
	protected Set<String> tags = new HashSet<String>();
	protected Banco banco = new Banco();
	protected Point ubicacion = new Point(54, 10);
	
	protected Poi poi = new Poi(banco, ubicacion, "", tags);
	protected Poi poi2 = new Poi(banco, ubicacion, "", tags);
	protected Poi poi3 = new Poi(banco, ubicacion, "", tags);
	
//	private BajaPoiAdapter bpAdapter = new BajaPoiAdapter();
//	ProcesoBajaPoi procesoBaja = new ProcesoBajaPoi();
	
	
	
	@Before
	public void init(){
		Mapa.resetSingleton();
		poi2.setId(122);
		poi.setId(123);
		poi3.setId(124);
		Mapa.getInstance().alta(poi);
		Mapa.getInstance().alta(poi2);
		Mapa.getInstance().alta(poi3);
	}
	
	@Test
	public void testNoExisteEseID(){
//		procesoBaja.setUrl("http://demo3537367.mockable.io/trash");
//		procesoBaja.setUrl("pois");
//		procesoBaja.realizarProceso();
//		Assert.assertEquals(1,Mapa.getInstance().cantidadPois(),0);
		Assert.assertEquals(3, Mapa.getInstance().cantidadPois(), 0);
		
	}
	
//	 @Test
//	    public void consultarConFiltro() throws Exception {
//	        //Se solicita todos los datos de un libro por su isbn.
//	        ClientResponse response = this.bpAdapter.consultarUrl("","");
//	        Assert.assertEquals(response.getStatus(), 200);
//	        String json = response.getEntity(String.class);
//	        Assert.assertTrue(json.contains("122"));
//	        Assert.assertTrue(json.contains("123"));
//	        Assert.assertFalse(json.contains("321"));
//	        Assert.assertTrue(json.contains("2016-06-22T02:10:58.128Z"));
//	        Assert.assertTrue(json.contains("id")); 
//	    }
	
	 
	 
//	@Test
//    public void obtenerConDosFiltros() throws Exception {
//        //Se filtra y devuelve solo el campo titulo.
//        ClientResponse response = this.bpAdapter.getBookByFilter("","","");
//        assertEquals(response.getStatus(), 200);
//        String json = response.getEntity(String.class);
//        assertTrue(json.contains("122"));
//        assertTrue(json.contains("123"));
//        assertFalse(json.contains("321"));
//        //assertFalse(json.contains("2016-06-22T02:10:58.128Z"));
//        assertTrue(json.contains("id"));
//      
//    }
}
