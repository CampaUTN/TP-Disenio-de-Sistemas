package tpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import org.junit.*;
//import org.mockito.Mockito;

import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;

public class TestExterno {
	private BancoAdapter adapterBanco = new BancoAdapter();
	private CGPAdapter cgpAdapter = new CGPAdapter();
	
	
	@Test
	public void testDevuelveListaExternaBanco(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		Assert.assertEquals(2,bancosExterno.size(),0); //Porque el mock devuelve dos bancos, sin importar que texto se pase.
	}
	
	@Test
	public void testBancoMismoNombre(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		
		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
		
		String nombre =bancosExterno.get(0).getNombre();
		String nombrecito ="Banco de la Plaza";
		Assert.assertTrue(nombrecito.equals(nombre));
		
	}
	
	
//	@Test PROBLEMA CON POSICIONES 
//	public void testBancoMismaUbicacion(){
//		List<String> palabras = new ArrayList<String>();
//		palabras.add("Banco de la Plaza");
//		
//		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
//		
//		String ubicacion = bancosExterno.get(0).getUbicacion().toString();
//		
//		String ubicacioncita = "x: " + -35.9338321999999976696926751174032688140869140625
//				+ ", y: " + 72.348353000000003021341399289667606353759765625 + "";
//		//Assert.assertTrue(ubicacioncita.equals(ubicacion));
//		Assert.assertEquals(ubicacioncita, ubicacion, 0);
//		
//    }
		
//	@Test PROBLEMAS CON LISTAS (TILDES)
//	public void testBancoMismoServicios(){
//		List<String> palabras = new ArrayList<String>();
//		palabras.add("Banco de la Plaza");
//
//
//		List<Poi> bancosExterno = adapterBanco.consultar(palabras);
//		Set<String> servicios = bancosExterno.get(0).getTags();
//		String servicitos = "[, extracciones, transferencias, cobro cheques, depÃ³sitos, crÃ©ditos]";
//		//Assert.assertEquals(servicitos , servicios, 0);
//		Assert.assertTrue(servicitos.equals(servicios));
//		
//	}	

	
	@Test
	public void testDevuelveListaExternaCgp(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
		
		Assert.assertEquals(2,cgpsExternos.size(),0);
	}
		
	@Test
	public void testCgpMismoNombre(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("aasas");
		
		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);	
		String nombre = cgpsExternos.get(0).getNombre();
		//Assert.assertEquals(null, nombre, 0);
		Assert.assertTrue(nombre == null);
	}
	
	@Test
	public void testCgpMismaUbicacion(){
		List<String> palabras = new ArrayList<String>();
		palabras.add("Banco de la Plaza");
		
		List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
		
		String ubicacion = cgpsExternos.get(0).getUbicacion().toString();
		String ubicacioncita = "x: " + 1 + ", y: " + 1 + "";
		Assert.assertTrue(ubicacioncita.equals(ubicacion));
		//Assert.assertEquals(ubicacioncita, ubicacion, 0);
		
	}
		
	@Test
	public void testCgpMismoServicios(){
		List<String> palabras = new ArrayList<String>();
		Set<String> servicios = new HashSet<String>();
		Set<String> servicioEsperado = new HashSet<String>();
		palabras.add("Banco de la Plaza");
		servicioEsperado.add("tramites");
		servicioEsperado.add("cheques");
	    List<Poi> cgpsExternos = cgpAdapter.consultar(palabras);
	    servicios = cgpsExternos.get(0).getTags();
		Assert.assertEquals(servicioEsperado, servicios);
	    //Assert.assertTrue(servicioEsperado.equals(servicios));
			
		}		


}
