package tpAnual;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import org.uqbar.geodds.Point;

public class TestCercania {
	
	private Point puntoComunaA = new Point(0,0);
	private Point puntoComunaB = new Point(20,60);
	private Point puntoComunaC = new Point(50,0);
	
	private Set<String> tags = new HashSet<String>();
	private Point ubicacionPoi = new Point(20, 10);
	
	private Cgp cgp = new Cgp(puntoComunaA,puntoComunaB,puntoComunaC);
	private Banco banco = new Banco();
	private Colectivo colectivo = new Colectivo();
	
	private Poi poiColectivo = new Poi(colectivo,ubicacionPoi,"60",tags);
	private Poi poiCgp = new Poi(cgp, ubicacionPoi, "Asistencia social", tags);
	private Poi poiBanco = new Poi(banco, ubicacionPoi, "banco galicia", tags);
	
	private Point puntoDeBusqueda = new Point(20.003,10.003);
	private Point puntoDeBusquedaColect = new Point (20.0006,10.0003);
	
	@Before
	public void init() {
		
	}

	@Test
	public void cgpEstaCercaPorComuna() {
		Assert.assertTrue(poiCgp.estaCerca(puntoDeBusqueda));
	}
	
	@Test
	public void bancoEstaCercaPorMenosDe5Cuadras() {
		Assert.assertTrue(poiBanco.estaCerca(puntoDeBusqueda));
	}
	
	@Test
	public void paradaDeColectivoEstaCercaPorMenosDe1Cuadra(){
		Assert.assertTrue(poiColectivo.estaCerca(puntoDeBusquedaColect));
	}
	
}
