package tpAnual;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.acciones.RepositorioRegistros;
import tpAnual.acciones.reportes.ElementoReporte;

public class TestReportePorTerminal extends TestSetup{
	private Terminal otraTerminal = new Terminal(1);
	
	private List<ElementoReporte> reporte;

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
	public void seAgregaMasDeUnTerminal(){
		buscador.buscarSegunTexto("107",terminal);
		buscador.buscarSegunTexto("107",otraTerminal);
		
		reporte = RepositorioRegistros.getInstance().reportarPorTerminal();
		
		Assert.assertEquals(2, reporte.size());
	}
	
	@Test 
	public void mismaCantidadDeParcialesQueTotales(){
		
	}
	
	
}
