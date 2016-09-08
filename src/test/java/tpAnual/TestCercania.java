package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.*;
import org.uqbar.geodds.Point;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Cgp;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;

public class TestCercania extends TestSetup{
	private Point puntoComunaA = new Point(0,0);
	private Point puntoComunaB = new Point(20,60);
	private Point puntoComunaC = new Point(50,0);
	private List<Point> puntosComuna =  new ArrayList<Point>();
	
	private Set<String> tags = new HashSet<String>();
	private Point ubicacionPoi = new Point(20, 10);
	
	
	private Banco banco = new Banco();
	private EstacionDeColectivo estacionDeColectivo = new EstacionDeColectivo(0,"");
	private Cgp cgp;
	
	private Point puntoDeBusqueda = new Point(20.003,10.003);
	private Point puntoDeBusquedaColect = new Point (20.0006,10.0003);
	private Point puntoDeBusquedaNegocio = new Point(54.0008,10.0002);
	
	private Poi poiColectivo = new Poi(estacionDeColectivo,ubicacionPoi,"60",tags);
	private Poi poiColectivo2 = new Poi(estacionDeColectivo,puntoDeBusquedaColect,"47",tags);
	private Poi poiBanco = new Poi(banco, ubicacionPoi, "banco galicia", tags);
	private Poi poiCgp;
	
	
	@Before
	public void init(){
		super.init();
		Mapa.resetSingleton();
		puntosComuna.add(puntoComunaA);
		puntosComuna.add(puntoComunaB);
		puntosComuna.add(puntoComunaC);
		cgp = new Cgp(puntosComuna);
		poiCgp = new Poi(cgp, ubicacionPoi, "Asistencia social", tags);
		negocio.setRadio(10);

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
	
	@Test
	public void cercaniaEntrePois(){
		Assert.assertTrue(Mapa.getInstance().estaAMenosDe(poiBanco, poiColectivo2, 0.4));
	}
	
	@Test
	public void cercaniaNegocioRadio(){
		Assert.assertTrue(poiNegocio.estaCerca(puntoDeBusquedaNegocio));
	}
}
