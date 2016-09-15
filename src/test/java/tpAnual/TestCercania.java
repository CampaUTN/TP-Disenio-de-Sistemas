package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Cgp;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.util.wrapper.PointWrapper;

public class TestCercania extends TestSetup{
	private PointWrapper puntoComunaA = new PointWrapper(0,0);
	private PointWrapper puntoComunaB = new PointWrapper(20,60);
	private PointWrapper puntoComunaC = new PointWrapper(50,0);
	private List<PointWrapper> puntosComuna =  new ArrayList<PointWrapper>();
	
	private Set<String> tags = new HashSet<String>();
	private PointWrapper ubicacionPoi = new PointWrapper(20, 10);
	

	private PointWrapper puntoDeBusqueda = new PointWrapper(20.003,10.003);
	private PointWrapper puntoDeBusquedaColect = new PointWrapper (20.0006,10.0003);
	private PointWrapper puntoDeBusquedaNegocio = new PointWrapper(54.0008,10.0002);
	
	private Poi poiColectivo = new EstacionDeColectivo(ubicacionPoi,"60",tags,0,"");
	private Poi poiColectivo2 = new EstacionDeColectivo(puntoDeBusquedaColect,"47",tags,0,"");
	private Poi poiBanco = new Banco(ubicacionPoi, "banco galicia", tags);
	private Cgp cgp;
	
	
	@Before
	public void init(){
		super.init();
		puntosComuna.add(puntoComunaA);
		puntosComuna.add(puntoComunaB);
		puntosComuna.add(puntoComunaC);
		cgp = new Cgp(ubicacionPoi, "Asistencia social", tags,puntosComuna);

	}

	@Test
	public void cgpEstaCercaPorComuna() {
		Assert.assertTrue(cgp.estaCerca(puntoDeBusqueda));
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
		Assert.assertTrue(negocio.estaCerca(puntoDeBusquedaNegocio));
	}
}
