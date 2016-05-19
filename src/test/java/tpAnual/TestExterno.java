package tpAnual;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.Assert;

public class TestExterno {
	ExternoEntidadesBancarias mockBanco = new ExternoEntidadesBancarias();
	BancoAdapter adapterBanco = new BancoAdapter();
	ExternoCPO externoCpo = new ExternoCPO();
	CGPAdapter cgpAdapter = new CGPAdapter();
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDevuelveListaExternaBanco(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> bancosExterno = new ArrayList<Poi>();
		bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertEquals(3,bancosExterno.size(),0);
	}
	
	@Test
	public void testDevuelveListaExternaCgp() throws InterruptedException{
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> cgpsExternos = new ArrayList<Poi>();
		cgpsExternos= cgpAdapter.consultar(palabras);
		
		Assert.assertEquals(2,cgpsExternos.size(),0);
		
	}
}
