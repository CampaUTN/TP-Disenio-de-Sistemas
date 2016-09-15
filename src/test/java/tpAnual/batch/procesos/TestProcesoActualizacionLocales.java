package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.uqbar.geodds.Point;

import tpAnual.Mapa;
import tpAnual.SingletonReseter;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.batch.procesos.ProcesoActualizarLocales;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.externo.sistemasExternos.LocalComercialExternoDTO;
import tpAnual.utils.PointWrapper;

public class TestProcesoActualizacionLocales {
	
	private LocalComercialAdapter lcAdapter = new LocalComercialAdapter("src/test/resources/localesComerciales.txt");
	private ProcesoActualizarLocales procesoLocales = new ProcesoActualizarLocales();
	
	@Before
	public void init(){
		SingletonReseter.resetAll();
	}
	
	@Test
	public void testNuevoLocalComercialExterno(){
		LocalComercialExternoDTO externo= lcAdapter.adaptar("negocio1;chocolates helado");
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("chocolates");
		palabrasClave.add("helado");
		Assert.assertTrue(palabrasClave.equals(externo.getPalabrasClave()));
	}
	
	@Test
	public void testCambioTagsLocalComercial(){
		LocalComercialExternoDTO externo= lcAdapter.adaptar("negocio1;chocolates helado");
		
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("pepas");
		Poi poi = new Negocio(new PointWrapper(0,0),"negocio1",palabrasClave,"Venta",10);
		Mapa.getInstance().alta(poi);
		procesoLocales.cambiarLocalComercial(externo);
		Assert.assertFalse(poi.getTags().contains("pepas"));
		Assert.assertTrue(poi.getTags().contains("chocolates"));
		Assert.assertTrue(poi.getTags().contains("helado"));
	}
	
	@Test
	public void testIntercambiarLocalComercial(){
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("pepas");
		Poi poi = new Negocio(new PointWrapper(0,0),"negocio1",palabrasClave,"Venta",10);

		Mapa.getInstance().alta(poi);
		procesoLocales.realizarProceso();
		Assert.assertFalse(poi.getTags().contains("pepas"));
		Assert.assertTrue(poi.getTags().contains("chocolates"));
		Assert.assertTrue(poi.getTags().contains("helado"));
	}
}
