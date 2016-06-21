package tpAnual;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import tpAnual.acciones.reportes.ElementoReporte;
import tpAnual.acciones.RepositorioRegistros;


public class TestReportePorFecha extends TestSetup{
	private List<ElementoReporte> reporte;
	private ElementoReporte registro;
	
	@Test
	public void noSeRealizanBusquedas(){
		reporte = RepositorioRegistros.getInstance().reportarPorTerminal();
		Assert.assertTrue(reporte.isEmpty());
	}	
	
	@Test
	public void alReporteSeLeAgregaLaFechaDeHoy(){
		buscador.buscarSegunTexto("107",terminal);
		
		reporte = RepositorioRegistros.getInstance().reportarPorFecha();
		Assert.assertTrue(reporte.stream().anyMatch(reporte -> reporte.esDeLaFecha(LocalDate.now())));	
	}
	
	
	@Test
	public void dosBusquedasTienenLaMismaFecha(){
		buscador.buscarSegunTexto("107",terminal);
		buscador.buscarSegunTexto("colectivo",terminal);

		reporte = RepositorioRegistros.getInstance().reportarPorFecha();
		Assert.assertTrue(reporte.stream().anyMatch(reporte -> reporte.esDeLaFecha(LocalDate.now())));	
	}
	
	@Test
	public void unaFechaTieneMasDeUnaBusqueda(){
		buscador.buscarSegunTexto("107",terminal);
		buscador.buscarSegunTexto("colectivo",terminal);

		registro = RepositorioRegistros.getInstance().reportarPorFecha().get(0);
		Assert.assertTrue(registro.getCantidadBusquedas().equals(2));
	}
	
}
