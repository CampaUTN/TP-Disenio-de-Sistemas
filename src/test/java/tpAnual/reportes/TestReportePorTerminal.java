package tpAnual.reportes;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.Terminal;
import tpAnual.TestSetup;
import tpAnual.busquedas.RepositorioRegistros;
import tpAnual.reportes.ElementoReporte;

public class TestReportePorTerminal extends TestSetup{
	private Terminal otraTerminal = new Terminal(1);	
	private List<ElementoReporte> reporte;
	private ElementoReporte registro;
	
	@Before
	public void init(){
		super.init();

		otraTerminal.desactivarMails();
		otraTerminal.activarRegistros();
	}
	
	@Test
	public void noSeRealizanBusquedas(){
		reporte = RepositorioRegistros.getInstance().reportarPorTerminal();
		Assert.assertTrue(reporte.isEmpty());
	}
	
	@Test
	public void seAgregaAlMenosUnTerminal(){
		buscador.buscarSegunTexto("107",terminal);
		reporte = RepositorioRegistros.getInstance().reportarPorTerminal();
		
		Assert.assertFalse(reporte.isEmpty());
	}
	
	
	@Test 
	public void paraUnSoloTerminalSeAgreganVariasBusquedas(){
		buscador.buscarSegunTexto("107",terminal);
		buscador.buscarSegunTexto("108",terminal);
		buscador.buscarSegunTexto("rentas",terminal);
		
		reporte = RepositorioRegistros.getInstance().reportarPorTerminal();
		
		Assert.assertEquals(1, reporte.size());
		
	}
	
	@Test
	public void seAgregaMasDeUnTerminal(){
		buscador.buscarSegunTexto("107",terminal);
		buscador.buscarSegunTexto("108",otraTerminal);
		
		reporte = RepositorioRegistros.getInstance().reportarPorTerminal();
		
		Assert.assertEquals(2, reporte.size());
	}

	
	@Test 
	public void testMismaCantidadDeParcialesQueTotalesConUnaSolaBusqueda(){
		buscador.buscarSegunTexto("107",terminal);		
		reporte = RepositorioRegistros.getInstance().reportarPorTerminal();
		registro = reporte.get(0); //en este caso, 0 muestra los resultados del Terminal terminal	
		
		Integer totales = registro.getBusquedasParciales().stream().mapToInt(i -> i).sum();
		
		Assert.assertEquals(registro.getBusquedasParciales().get(0), totales);
		
	}
	
	@Test 
	public void testSumaDeParcialesIgualaTotales(){ //TODO la segunda busqueda me da que encontro 8 resultados y deberia dar 6,como la primera D:
		poisBusqueda = buscador.buscarSegunTexto("107",terminal);		
		poisBusqueda.addAll(buscador.buscarSegunTexto("107",terminal));	
		poisBusqueda.addAll(buscador.buscarSegunTexto("rentas",terminal));	
		reporte = RepositorioRegistros.getInstance().reportarPorTerminal();
		
		registro = reporte.get(0); //en este caso, la posicion 0 tiene los resultados del Terminal terminal	
		
		int totales = registro.getBusquedasParciales().stream().mapToInt(i -> i).sum();
		
		Assert.assertEquals(poisBusqueda.size(), totales);
	}
}