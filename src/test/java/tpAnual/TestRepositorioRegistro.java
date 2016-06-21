package tpAnual;

import org.junit.Assert;
import org.junit.Test;

import tpAnual.acciones.RepositorioRegistros;

public class TestRepositorioRegistro extends TestSetup{
	
	@Test
	public void testLosRegistrosEstanVacios(){
		Assert.assertEquals(0,RepositorioRegistros.getInstance().getRegistros().size(),0);
	}	
	
	@Test
	public void testSeAgregaUnResultadoALaBusqueda(){
		poisBusqueda = buscador.buscarSegunTexto("colectivo",terminal);
		Assert.assertEquals(1,RepositorioRegistros.getInstance().getRegistros().size(),0);
	}
	
	@Test
	public void testSeRealizaElReportePorFecha(){
		poisBusqueda = buscador.buscarSegunTexto("colectivo",terminal);
		Assert.assertEquals(1,RepositorioRegistros.getInstance().reportarPorFecha().size(),0);
	}

	@Test
	public void testSeRealizaElReportePorTerminal(){
		poisBusqueda = buscador.buscarSegunTexto("colectivo",terminal);
		Assert.assertEquals(1,RepositorioRegistros.getInstance().reportarPorTerminal().size(),0);
	}
	
	@Test
    public void prueboQueTodasLasInstanciasSeanLaMisma() {
		RepositorioRegistros instance1 = RepositorioRegistros.getInstance();
		RepositorioRegistros instance2 = RepositorioRegistros.getInstance();
        Assert.assertEquals(instance1,instance2);
    }
}
