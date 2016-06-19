package tpAnual;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class TestReporteBusqueda extends TestBuscador{
	
	@Before
	public void init(){
		super.init();
		terminal.desactivarRegistros();
		terminal.desactivarMails();
	}
	
	
	@Test
	public void testSeAlmacenaLaCantidad(){
			
		Assert.assertEquals(pois.size(), registro.getCantidadPois(),0);
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
