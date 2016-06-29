package tpAnual;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.Assert;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.procesos.Lanzador;
import tpAnual.procesos.ManejadorDeErrores;
import tpAnual.procesos.ProcesoActivadorAcciones;
import tpAnual.procesos.emailSenderFallo.IEmailSenderFallo;

public class testManejadorDeErrores {
	private Set<Terminal> terminales;
	private Set<String> activar;
	private Set<String> desactivar;
	private int limite;
	private IEmailSenderFallo mockSender = Mockito.mock(IEmailSenderFallo.class);
	private ProcesoActivadorAcciones proceso;
	private LocalComercialAdapter proceso2;
	
	@Before
	public void init(){
		terminales = new HashSet<>();
		activar = new HashSet<>();
		desactivar = new HashSet<>();
		limite=3;
		ManejadorDeErrores.getInstance().setLimite(limite);
		terminales.add(new Terminal(0));
		activar.add("hola");
		desactivar.add("chau");
				
		proceso = ProcesoActivadorAcciones.EnComuna(0, activar, desactivar);
	}
	
	@After
	public void finalizar(){
		ManejadorDeErrores.resetSingleton();
		Lanzador.resetSingleton();
	}
	
	@Test
	public void testLimiteAlcanzado(){
		// Seteo todo en null para que de null pointer exception y se tenga que reejecutar
		proceso = ProcesoActivadorAcciones.EnComuna(9, null, null);
		Lanzador.getInstance().ejecutarProceso(proceso);
		//se va a intentar reejecutar hasta superar el limite.
		Assert.assertEquals(limite, proceso.getIntentos(),0);
	}
	
	@Test
	public void noSumaReintentosSiNoSeReejecuta(){
		Lanzador.getInstance().ejecutarProceso(proceso);
		limite=0;
		ManejadorDeErrores.getInstance().setLimite(limite);
		Assert.assertEquals(limite, proceso.getIntentos(),0);
	}
	
	@Test
	public void ignoraElLimiteSiSeEjecutaCorrectamente(){
		Lanzador.getInstance().ejecutarProceso(proceso);
		limite=-1;
		ManejadorDeErrores.getInstance().setLimite(limite);
		Assert.assertEquals(0, proceso.getIntentos(),0);
	}
	
	@Test
	public void noEnviaMailSiNoEstanActivados(){
		proceso = ProcesoActivadorAcciones.EnComuna(9, null, null);
		Lanzador.getInstance().ejecutarProceso(proceso);
		limite=2;
		ManejadorDeErrores.getInstance().setLimite(limite);
	}
}
