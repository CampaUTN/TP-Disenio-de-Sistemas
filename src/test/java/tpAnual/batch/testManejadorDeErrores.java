package tpAnual.batch;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import org.mockito.Mockito;

import tpAnual.Terminal;
import tpAnual.batch.Lanzador;
import tpAnual.batch.observers.IEmailSenderFallo;
import tpAnual.batch.observers.ReLanzador;
import tpAnual.batch.procesos.FinEjecucion;
import tpAnual.batch.procesos.ProcesoActivadorAcciones;

public class testManejadorDeErrores {
	private Set<Terminal> terminales;
	private Set<String> activar;
	private Set<String> desactivar;
	private int limite;
	private IEmailSenderFallo mockSender = Mockito.mock(IEmailSenderFallo.class);
	private ProcesoActivadorAcciones proceso;
	
	@Before
	public void init(){
		terminales = new HashSet<>();
		activar = new HashSet<>();
		desactivar = new HashSet<>();
		Lanzador.resetSingleton();
		limite=3;
		terminales.add(new Terminal(0));
		activar.add("Mail");
		desactivar.add("Registro");
				
		proceso = ProcesoActivadorAcciones.EnComuna(0, activar, desactivar);
	}
	
	@After
	public void finalizar(){
		Lanzador.resetSingleton();
	}
	
	@Test
	public void noSumaReintentosSiNoSeReejecuta(){
		limite=0;
		ReLanzador relanzador = ReLanzador.ReLanzadorSinMail(limite);
		proceso.agregarObservador(relanzador);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(0, relanzador.getVecesConsecutivasQueFallo(),0);
	}
	
	@Test
	public void ignoraElLimiteSiSeEjecutaCorrectamente(){
		proceso.agregarObservador(ReLanzador.ReLanzadorSinMail(-1));
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(FinEjecucion.CORRECTO,proceso.getEstado());
	}
	
	@Test
	public void enviaMailSiEstanActivados(){
		proceso = ProcesoActivadorAcciones.EnTodos(null, null);
		ReLanzador relanzador = ReLanzador.ReLanzadorConMail(3);
		relanzador.setSender(mockSender);
		proceso.agregarObservador(relanzador);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Mockito.verify(mockSender).accionar(Mockito.any());
	}
}
