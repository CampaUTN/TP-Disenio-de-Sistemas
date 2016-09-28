package tpAnual.batch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tpAnual.Terminal;
import tpAnual.batch.postEjecucionProceso.IEmailSenderFallo;
import tpAnual.batch.postEjecucionProceso.ReLanzador;
import tpAnual.batch.procesos.AccionTerminal;
import tpAnual.batch.procesos.ActivacionEnTodas;
import tpAnual.batch.procesos.ActivacionPorComuna;
import tpAnual.batch.procesos.ActivarMails;
import tpAnual.batch.procesos.DesactivarRegistros;
import tpAnual.batch.procesos.FinEjecucion;
import tpAnual.batch.procesos.Proceso;
import tpAnual.batch.procesos.ProcesoActualizarLocales;
import tpAnual.util.Reseter;

public class testManejadorDeErrores {
	private Set<Terminal> terminales;
	private Set<String> activar;
	private Set<String> desactivar;
	private int limite;
	private IEmailSenderFallo mockSender = Mockito.mock(IEmailSenderFallo.class);
	private Proceso proceso, proceso2;
	
	@Before
	public void init(){
		Reseter.resetSingletons();
		terminales = new HashSet<>();
		activar = new HashSet<>();
		desactivar = new HashSet<>();
;
		limite=3;
		terminales.add(new Terminal(0));
		activar.add("Mail");
		desactivar.add("Registro");

		proceso = new ActivacionPorComuna(0, null);
		proceso2 = new ProcesoActualizarLocales();
	}
	
	@After
	public void finalizar(){
		Lanzador.resetSingleton();
	}
	
	@Test
	public void noSumaReintentosSiNoSeReejecuta(){
		limite=0;
		ReLanzador relanzador = ReLanzador.ReLanzadorSinMail(limite);
		proceso.agregarAccionPostFallo(relanzador);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Assert.assertEquals(0, relanzador.getVecesConsecutivasQueFallo(),0);
	}
	
	@Test
	public void ignoraElLimiteSiSeEjecutaCorrectamente(){
		List<AccionTerminal> acciones = new ArrayList<AccionTerminal>();
		acciones.add(new ActivarMails());
		acciones.add(new DesactivarRegistros());
		proceso2 = new ActivacionPorComuna(5, acciones);
		proceso2.agregarAccionPostFallo(ReLanzador.ReLanzadorSinMail(-1));
		Lanzador.getInstance().ejecutarProceso(proceso2);
		Assert.assertEquals(FinEjecucion.CORRECTO,proceso2.getEstado());
	}
	
	@Test
	public void enviaMailSiEstanActivados(){
		proceso = new ActivacionEnTodas(null);
		ReLanzador relanzador = ReLanzador.ReLanzadorConMail(3);
		relanzador.setSender(mockSender);
		proceso.agregarAccionPostFallo(relanzador);
		Lanzador.getInstance().ejecutarProceso(proceso);
		Mockito.verify(mockSender).accionar(Mockito.any());
	}
}
