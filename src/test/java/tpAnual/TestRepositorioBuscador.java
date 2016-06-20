package tpAnual;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;

public class TestRepositorioBuscador {
	
	private BancoAdapter bancoAdapter = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	private Mapa mapa = new Mapa();

	@Before
	public void init() {
		RepositorioBuscador.resetSingleton();
		RepositorioBuscador.getInstance().agregarConsultora(bancoAdapter);
		RepositorioBuscador.getInstance().agregarConsultora(cgpAdapter);
		RepositorioBuscador.getInstance().agregarConsultora(new BuscadorLocal(mapa));
	}
		
	@Test 
	public void ElRepoAgregaAdaptersPolimorficamente(){
		Assert.assertEquals(3, RepositorioBuscador.getInstance().getConsultoras().size(), 0);
	}
	
	
	@Test
    public void prueboQueTodasLasInstanciasSeanLaMisma() {
       	RepositorioBuscador instance1 = RepositorioBuscador.getInstance();
    	RepositorioBuscador instance2 = RepositorioBuscador.getInstance();
        Assert.assertEquals(instance1,instance2);
    }

}
