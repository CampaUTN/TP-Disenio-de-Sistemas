package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.uqbar.geodds.Point;

import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.batch.procesos.ProcesoActualizarLocales;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.externo.sistemasExternos.LocalComercialExternoDTO;

public class TestProcesoActualizacionLocales {
	
	private LocalComercialAdapter lcAdapter = new LocalComercialAdapter();
	private ProcesoActualizarLocales procesoLocales = new ProcesoActualizarLocales();
	
	@Test
	public void testNuevoLocalComercialExterno(){
		LocalComercialExternoDTO externo= lcAdapter.adaptar("negocio1;chocolates helado");
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("chocolates");
		palabrasClave.add("helado");
		palabrasClave.equals(externo.getPalabrasClave());
	}
	
	@Test
	public void testCambioTagsLocalComercial(){
		LocalComercialExternoDTO externo= lcAdapter.adaptar("negocio1;chocolates helado");
		
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("pepas");
		Negocio negocio = new Negocio("Venta");
		Poi poi = new Poi(negocio,new Point(0,0),"negocio1",palabrasClave);
		List<Poi> pois = new ArrayList<Poi>();
		pois.add(poi);
		procesoLocales.cambiarLocalComercial(pois, externo);
		Assert.assertFalse(pois.get(0).getTags().contains("pepas"));
		Assert.assertTrue(pois.get(0).getTags().contains("chocolates"));
		Assert.assertTrue(pois.get(0).getTags().contains("helado"));
	}
	
	public void testIntercambiarLocalComercial(){
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("pepas");
		Negocio negocio = new Negocio("Venta");
		Poi poi = new Poi(negocio,new Point(0,0),"negocio1",palabrasClave);
		List<Poi> pois = new ArrayList<Poi>();
		pois.add(poi);
		procesoLocales.realizarProceso();
		Assert.assertFalse(pois.get(0).getTags().contains("pepas"));
		Assert.assertTrue(pois.get(0).getTags().contains("chocolates"));
		Assert.assertTrue(pois.get(0).getTags().contains("helado"));
	}
}
