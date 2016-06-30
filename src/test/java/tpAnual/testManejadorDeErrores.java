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
		Lanzador.resetSingleton();
		ManejadorDeErrores.resetSingleton();
		limite=3;
		ManejadorDeErrores.getInstance().setLimite(limite);
		ManejadorDeErrores.getInstance().desactivarAvisoPorMail();
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
	public void seReinicianLosIntentosDespuesDeQueSuperaElLimite(){
		// Seteo todo en null para que de null pointer exception y se tenga que reejecutar
		Set<String> activadosParaRomper = new HashSet<String>();
		activadosParaRomper.add(null);
		proceso = ProcesoActivadorAcciones.EnTodos(activadosParaRomper, new HashSet<>());
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(0, proceso.getIntentos(),0);
	}
	
	@Test
	public void noSumaReintentosSiNoSeReejecuta(){
		limite=0;
		ManejadorDeErrores.getInstance().setLimite(limite);
		Assert.assertEquals(limite, proceso.getIntentos(),0);
		Lanzador.getInstance().ejecutarProceso(proceso);
	}
	
	@Test
	public void ignoraElLimiteSiSeEjecutaCorrectamente(){
		limite=-1;
		ManejadorDeErrores.getInstance().setLimite(limite);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(0, proceso.getIntentos(),0);
	}
	
	@Test
	public void noEnviaMailSiNoEstanActivados(){
		ManejadorDeErrores.getInstance().setLimite(2);
		ManejadorDeErrores.getInstance().setSender(mockSender);
		ManejadorDeErrores.getInstance().desactivarAvisoPorMail();
		Lanzador.getInstance().ejecutarProceso(proceso);
		Mockito.verifyZeroInteractions(mockSender);
	}
	
	@Test
	public void enviaMailSiEstanActivados(){
		proceso = ProcesoActivadorAcciones.EnTodos(activar, new HashSet<>());
		ManejadorDeErrores.getInstance().setLimite(2);
		ManejadorDeErrores.getInstance().setSender(mockSender);
		ManejadorDeErrores.getInstance().activarAvisoPorMail();
		Lanzador.getInstance().ejecutarProceso(proceso);
		Mockito.verify(mockSender).enviarMensajePorFallo(Mockito.any());
	}
}
