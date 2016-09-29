package tpAnual;

import java.util.ArrayList;
import java.util.List;

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
		palabras.add("Banco de la Plaza");
		palabras.add("depósitos");
		
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertEquals(2,bancosExterno.size(),0); //Porque el mock devuelve dos bancos, sin importar que texto se pase.
	}
	
	@Test
	public void testBancoMismoNombre(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		palabras.add("depósitos");
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertTrue(bancosExterno.get(0).getNombre().equals("Banco de la Plaza"));
	}
	
	@Test
	public void testBancoMismaUbicacion(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		palabras.add("extracciones");
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
		palabras.add("extracciones");
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertNotEquals(0,bancosExterno.size());
	}	
	
	@Test
	public void testBuscaGgpsPorZona(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Palermo");
	    List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
		Assert.assertEquals(2,cgpsExternos.size());
		}
}
