package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import com.sun.jersey.api.client.ClientResponse;

import tpAnual.Mapa;
import tpAnual.POIs.Banco;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.externo.sistemasExternos.PoiAEliminarDTO;
import tpAnual.externo.sistemasExternos.UrlExterna;

public class TestProcesoBajaPoi {
	
	protected Set<String> tags = new HashSet<String>();
	protected Banco banco = new Banco();
	protected Point ubicacion = new Point(54, 10);
	
	protected Poi poi = new Poi(banco, ubicacion, "", tags);
	protected Poi poi2 = new Poi(banco, ubicacion, "", tags);
	protected Poi poi3 = new Poi(banco, ubicacion, "", tags);
	
	private BajaPoiAdapter bpAdapter = new BajaPoiAdapter();
	ProcesoBajaPoi procesoBaja = new ProcesoBajaPoi();
	List<PoiAEliminarDTO> poisAEliminar = new ArrayList<PoiAEliminarDTO>();
	
	
	
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
		
		//SI LE INTENTO PASAR LA URL, ROMPE
		bpAdapter.setUrl("http://demo3537367.mockable.io/trash");
		bpAdapter.setPath("pois");
		procesoBaja.realizarProceso();
		Assert.assertEquals(1,Mapa.getInstance().cantidadPois(),0);
		
		
	}
	
	 @Test
	    public void consultarConFiltro() {
	        poisAEliminar = bpAdapter.consultar();
	        Assert.assertEquals(poisAEliminar.get(0).getId(),123,0);
	    }
	 
	 @Test
	    public void consultarConFiltro2() {
	        poisAEliminar = bpAdapter.consultar();
	        Assert.assertEquals(poisAEliminar.get(1).getId(),122,0);
	    }
	 
	 @Test
	    public void consultarConFiltro3() throws Exception {
	        poisAEliminar = bpAdapter.consultar();
	        Assert.assertEquals(poisAEliminar.size(),2,0);
	    }
	
	 
	 

}
