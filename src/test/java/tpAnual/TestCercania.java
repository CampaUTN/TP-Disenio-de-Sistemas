package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.*;
import org.uqbar.geodds.Point;

public class TestCercania {
	
	private Point puntoComunaA = new Point(0,0);
	private Point puntoComunaB = new Point(20,60);
	private Point puntoComunaC = new Point(50,0);
	private List<Point> puntosComuna =  new ArrayList<Point>();
	
	private Set<String> tags = new HashSet<String>();
	private Point ubicacionPoi = new Point(20, 10);
	
	private Banco banco = new Banco();
	private Colectivo colectivo = new Colectivo();
	private Cgp cgp;
	
	private Poi poiColectivo = new Poi(colectivo,ubicacionPoi,"60",tags);
	private Poi poiBanco = new Poi(banco, ubicacionPoi, "banco galicia", tags);
	Poi poiCgp;
	
	private Point puntoDeBusqueda = new Point(20.003,10.003);
	private Point puntoDeBusquedaColect = new Point (20.0006,10.0003);
	
	public TestCercania(){
		puntosComuna.add(puntoComunaA);
		puntosComuna.add(puntoComunaB);
		puntosComuna.add(puntoComunaC);
		cgp = new Cgp(puntosComuna);
		poiCgp = new Poi(cgp, ubicacionPoi, "Asistencia social", tags);
	}
	
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
