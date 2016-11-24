package tpAnual.ui.controllers;

import java.sql.Date;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.POIs.Banco;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.busquedas.Busqueda;
import tpAnual.util.wrapper.PointWrapper;

import tpAnual.busquedas.RepositorioBusqueda;
import tpAnual.busquedas.RepositorioPersistente;
import tpAnual.busquedas.RepositorioNoPersistente;


public class BusquedasController {


	
	public static ModelAndView listar(Request req, Response res)
	{
		Map<String, List<Busqueda>> model = new HashMap<>();
		String fechaDesde = req.params("fechaDesde");
		String fechaHasta = req.params("fechaHasta");
		//String cantidadPois = req.params("cantidadPois");
		//String terminal = req.params("terminal");
		
		List<Busqueda> busqueda = (new RepositorioPersistente()).listar(fechaDesde, fechaHasta);
		busqueda.addAll(RepositorioNoPersistente.getInstance().listar(fechaDesde,fechaHasta));
		
		
		
		
//		List<Poi> pois = new ArrayList<>();
//		
//		Poi poi1 = (Poi)new Negocio(new PointWrapper(0, 0),"Negocio",null,"compras",5);
//		Poi poi2 = (Poi) new Banco(new PointWrapper(2, 2), "Banco Santander" , null);
//		
//		poi1.setCalle("Strangford");
//		poi1.setDireccion(1857);
//		
//		poi2.setCalle("Avenida Rivadavia");
//		poi2.setDireccion(458);
//		
//		pois.add(poi1);
//		pois.add(poi2);
//		
//		Busqueda b1 = new Busqueda("hola", pois);
//		b1.setFecha(Date.valueOf(LocalDate.now()));
//		
//		//BORRAR ESTO DE PRUEBA
//		List<Busqueda> busqueda = new ArrayList<>();
//		busqueda.add(b1);
		
		
       
		
		
		model.put("busqueda", busqueda);
		return new ModelAndView(model, "historico-consultas.hbs");
	}
	
	
	
	
}
