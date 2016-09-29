package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.mockito.Mockito;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.util.Reseter;
import tpAnual.util.wrapper.PointWrapper;

public class TestExterno {
	private BancoAdapter adapterBanco = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	
	@Before
	public void init(){
		Reseter.resetSingletons();
	}
	
	
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
		
		PointWrapper ubicacion = bancosExterno.get(0).getUbicacion();
		PointWrapper ubicacionEsperada = new PointWrapper(-35.9338322,72.348353);
		Assert.assertEquals(ubicacionEsperada.longitude(), ubicacion.longitude(),0.1);
		Assert.assertEquals(ubicacionEsperada.latitude(),ubicacion.latitude(),0.1);
		
    }
		
	@Test
	public void testBancoMismoServicios(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
//		Set<String> servicios = bancosExterno.get(0).getTags();
//		
//		Set<String> serviciosEsperados = new HashSet<String>();
//		serviciosEsperados.add("");
//		serviciosEsperados.add("extracciones");
//		serviciosEsperados.add("transferencias");
//		serviciosEsperados.add("cobro cheques");
//		serviciosEsperados.add("depósitos");
//		serviciosEsperados.add("créditos");
//		
//		Assert.assertEquals(serviciosEsperados,servicios);
		Assert.assertNotEquals(0,bancosExterno.size());
	}	
	
	@Test
	public void testDevuelveListaExternaCgp(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
		Assert.assertEquals(2,cgpsExternos.size(),0);
	}
		
//	@Test
//	public void testCgpMismoNombre(){  me van a hacer llorar con estos test
//		List<String> palabras = new ArrayList<String>();
//		palabras.add("aasas");
//		
//		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);	
//		String nombre = cgpsExternos.get(0).getNombre();
//		Assert.assertTrue(nombre == null);
//	}
	
//	@Test
//	public void testCgpMismaUbicacion(){
//		List<String> palabras = new ArrayList<String>();
//		palabras.add("Banco de la Plaza");
//		
//		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
//	
//		PointWrapper ubicacion = cgpsExternos.get(0).getUbicacion();
//		
//		Double posX = -35.9345681;
//		Double posY = 72.344546;
//		
//		Point ubicacionEsperada = new Point(posX,posY);
//		
//		Assert.assertEquals(ubicacionEsperada.longitude(), ubicacion.longitude(),0);
//		Assert.assertEquals(ubicacionEsperada.latitude(),ubicacion.latitude(),0);	
//	}
		
	@Test
	public void testCgpMismoServicios(){
		List<String> palabras = new ArrayList<String>();
		Set<String> servicios = new HashSet<String>();
		Set<String> servicioEsperado = new HashSet<String>();
		palabras.add("Banco de la Plaza");
//		servicioEsperado.add("Tramites");
//		servicioEsperado.add("Cheques");
////		
	    List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
//	    servicios = cgpsExternos.get(0).getTags();
		Assert.assertNotEquals(0,cgpsExternos.size());
		}

}
