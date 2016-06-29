package tpAnual;

import java.util.ArrayList;

import java.util.List;
import java.util.*;
import org.uqbar.geodds.Point;

import org.junit.*;
//import org.mockito.Mockito;

import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.externo.sistemasExternos.LocalComercialExterno;

import com.sun.jersey.api.client.ClientResponse;


public class TestExterno {
	private BancoAdapter adapterBanco = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	private LocalComercialAdapter lcAdapter = new LocalComercialAdapter();
	private BajaPoiAdapter bpAdapter = new BajaPoiAdapter();
	
	@Test
	public void testDevuelveListaExternaBanco(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertEquals(2,bancosExterno.size(),0); //Porque el mock devuelve dos bancos, sin importar que texto se pase.
	}
	
	@Test
	public void testBancoMismoNombre(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertTrue(bancosExterno.get(0).getNombre().equals("Banco de la Plaza"));
	}
	
	@Test
	public void testBancoMismaUbicacion(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		
		Point ubicacion = bancosExterno.get(0).getUbicacion();
		Point ubicacionEsperada = new Point(-35.9338322,72.348353);
		Assert.assertEquals(ubicacionEsperada.longitude(), ubicacion.longitude(),0.1);
		Assert.assertEquals(ubicacionEsperada.latitude(),ubicacion.latitude(),0.1);
		
    }
		
	@Test
	public void testBancoMismoServicios(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		Set<String> servicios = bancosExterno.get(0).getTags();
		
		Set<String> serviciosEsperados = new HashSet<String>();
		serviciosEsperados.add("");
		serviciosEsperados.add("extracciones");
		serviciosEsperados.add("transferencias");
		serviciosEsperados.add("cobro cheques");
		serviciosEsperados.add("depósitos");
		serviciosEsperados.add("créditos");
		
		Assert.assertEquals(serviciosEsperados,servicios);
	}	
	
	@Test
	public void testDevuelveListaExternaCgp(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
		Assert.assertEquals(2,cgpsExternos.size(),0);
	}
		
	@Test
	public void testCgpMismoNombre(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);	
		String nombre = cgpsExternos.get(0).getNombre();
		Assert.assertTrue(nombre == null);
	}
	
	@Test
	public void testCgpMismaUbicacion(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		
		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
	
		Point ubicacion = cgpsExternos.get(0).getUbicacion();
		Double posX = -35.9345681;
		Double posY = 72.344546;
		
		Point ubicacionEsperada = new Point(posX,posY);
		Assert.assertEquals(ubicacionEsperada.longitude(), ubicacion.longitude(),0);
		Assert.assertEquals(ubicacionEsperada.latitude(),ubicacion.latitude(),0);
	
	}
		
	@Test
	public void testCgpMismoServicios(){
		List<String> palabras = new ArrayList<String>();
		Set<String> servicios = new HashSet<String>();
		Set<String> servicioEsperado = new HashSet<String>();
		palabras.add("Banco de la Plaza");
		servicioEsperado.add("tramites");
		servicioEsperado.add("cheques");
		
	    List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
	    servicios = cgpsExternos.get(0).getTags();
		Assert.assertEquals(servicioEsperado, servicios);
		}
	
	@Test
	public void testNuevoLocalComercialExterno(){
		LocalComercialExterno externo= lcAdapter.adaptar("negocio1;chocolates helado");
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("chocolates");
		palabrasClave.add("helado");
		palabrasClave.equals(externo.getPalabrasClave());
	}
	
	@Test
	public void testCambioTagsLocalComercial(){
		LocalComercialExterno externo= lcAdapter.adaptar("negocio1;chocolates helado");
		
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("pepas");
		Negocio negocio = new Negocio("Venta");
		Poi poi = new Poi(negocio,new Point(0,0),"negocio1",palabrasClave);
		List<Poi> pois = new ArrayList<Poi>();
		pois.add(poi);
		lcAdapter.cambiarLocalComercial(pois, externo);
		Assert.assertFalse(pois.get(0).getTags().contains("pepas"));
		Assert.assertTrue(pois.get(0).getTags().contains("chocolates"));
		Assert.assertTrue(pois.get(0).getTags().contains("helado"));
	}
	
	public void testIntercambiarLocalComercial(){
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("pepas");
		Negocio negocio = new Negocio("Venta");
		Poi poi = new Poi(negocio,new Point(0,0),"negocio1",palabrasClave);
		List<Poi> pois = new ArrayList<Poi>();
		pois.add(poi);
		lcAdapter.realizarProceso();
		Assert.assertFalse(pois.get(0).getTags().contains("pepas"));
		Assert.assertTrue(pois.get(0).getTags().contains("chocolates"));
		Assert.assertTrue(pois.get(0).getTags().contains("helado"));
	}
	
	//	@Test
//	public void testNoExisteEseID(){
//		List<String> palabras = new ArrayList<String>();
//		palabras.add("123");
//		bpAdapter.consultar(palabras);
//		int cantidadPois = Mapa.getInstance().cantidadPois();
//		Assert.assertEquals(2,cantidadPois,0);
//	}
	
	 @Test
	    public void consultarConFiltro() throws Exception {
	        //Se solicita todos los datos de un libro por su isbn.
	        ClientResponse response = this.bpAdapter.consultar("","");
	        Assert.assertEquals(response.getStatus(), 200);
	        String json = response.getEntity(String.class);
	        Assert.assertTrue(json.contains("122"));
	        Assert.assertTrue(json.contains("123"));
	        Assert.assertFalse(json.contains("321"));
	        Assert.assertTrue(json.contains("2016-06-22T02:10:58.128Z"));
	        Assert.assertTrue(json.contains("id")); 
	    }
	
	 
	 
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
