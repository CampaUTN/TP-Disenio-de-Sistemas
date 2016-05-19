package tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
//import org.mockito.Mockito;

import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;

public class TestExterno {
	private BancoAdapter adapterBanco = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	
	
	@Test
	public void testDevuelveListaExternaBanco(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertEquals(2,bancosExterno.size(),0); //Porque el mock devuelve dos bancos, sin importar que texto se pase.
	}
	
	@Test
	public void testDevuelveListaExternaCgp(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
		
		Assert.assertEquals(2,cgpsExternos.size(),0);
		
	}
}
