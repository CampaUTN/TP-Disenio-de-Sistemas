package tpAnual;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.busquedas.BuscadorLocal;
import tpAnual.busquedas.RepositorioBuscadores;
import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.util.Reseter;

public class TestRepositorioBuscador {
	
	private BancoAdapter bancoAdapter = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	private RepositorioBuscadores repositorio;
	
	@Before
	public void init() {
		Reseter.resetSingletons();
		repositorio = RepositorioBuscadores.getInstance();
		
		repositorio.agregarConsultora(bancoAdapter);
		repositorio.agregarConsultora(cgpAdapter);
		repositorio.agregarConsultora(new BuscadorLocal());
	}
		
	
	@Test 
	public void ElRepoAgregaAdaptersPolimorficamente(){
		Assert.assertEquals(3, repositorio.getConsultoras().size(), 0);
	}
	
	
	@Test
    public void prueboQueTodasLasInstanciasSeanLaMisma() {
		RepositorioBuscadores instance1 = RepositorioBuscadores.getInstance();
		RepositorioBuscadores instance2 = RepositorioBuscadores.getInstance();
        Assert.assertEquals(instance1,instance2);
    }

}
