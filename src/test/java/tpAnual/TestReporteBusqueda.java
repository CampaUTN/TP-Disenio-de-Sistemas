package tpAnual;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import tpAnual.POIs.Poi;
import tpAnual.acciones.RepositorioRegistros;
import tpAnual.acciones.reportes.RegistroBusqueda;

public class TestReporteBusqueda extends TestBuscador{
	
	@Before
	public void init(){
		super.init();
		terminal.activarRegistros();
	}
	
	@Test
	public void testSeAlmacenaLaCantidad(){
			
		Assert.assertEquals(poisBusqueda.size(), registro.getCantidadPois(),0);
	}
	
	@Test
	public void testSeAlmacenaLaFecha(){
		Assert.assertEquals(LocalDate.now(), registro.getFecha());
	}
	
	@Test
	public void testSeAlmacenaLasPalabaras(){
		List <String> comp = new ArrayList<>();
		comp.add("colectivo");
		Assert.assertTrue(registro.getPalabrasUtilizadas().containsAll(comp));
	}
	
	@Test
	public void testSeAlmacenaElTerminal(){
		
		Assert.assertTrue(registro.getTerminal().equals(terminal));
	}
	
}
