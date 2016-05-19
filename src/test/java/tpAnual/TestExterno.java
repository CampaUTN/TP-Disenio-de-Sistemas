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
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDevuelveListaExterna(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> bancosExterno = new ArrayList<Poi>();
		bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertEquals(3,bancosExterno.size(),0);
	}
}
