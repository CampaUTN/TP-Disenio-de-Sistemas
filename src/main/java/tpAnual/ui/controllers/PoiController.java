package tpAnual.ui.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Mapa;
import tpAnual.POIs.Banco;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.util.wrapper.PointWrapper;

public class PoiController {

	public static ModelAndView get(Request req, Response res){
		
//		Map<String, Object> viewModel = new HashMap<String, Object>();
//
//	 //TODO borrar
//		Poi poi = (Poi)new Negocio(null,"Negocio",null,"compras",5);
//		poi.setCalle("Strangford 1857");
//		
//		viewModel.put("poi", poi);
//		
//		return new ModelAndView(viewModel, "busqueda.hbs");
		
		String nombre = req.queryParams("nombre");
		
		Map<String, List<Poi>> model = new HashMap<String, List<Poi>>();
		Negocio negocio = new Negocio();
		negocio.setNombre("NEGOCIO");
		negocio.setCalle("CALLE");
		Mapa.getInstance().alta(negocio);
		
		List<Poi> pois = new ArrayList<Poi>();
		pois.add(negocio);
		model.put("pois", pois);
		
//		if(nombre=="all"){
//			model.put("pois",Mapa.getInstance().getPois());
//		}else{
////			String tipo = req.queryParams("tipo");
////			String busqueda = nombre + " " + tipo;
////			List<Poi> resultado = Mapa.getInstance().buscarPoi(nombre,tipo);
////			model.put("pois", resultado);
//			model.put("pois",Mapa.getInstance().getPois());
//		}
		
  		return new ModelAndView(model, "pois.hbs");
	}
	
	public static ModelAndView listar(Request req, Response res){
			
		String nombre = req.queryParams("nombre");
		String tipo = req.queryParams("tipo");
		
		Map<String, List<Poi>> model = new HashMap<String, List<Poi>>();
		
  		List <Poi> resultado = new ArrayList<>();
		if(nombre.length() == 0 && tipo == "Todos"){
			System.out.println("ENTRE ACA");
			resultado = Mapa.getInstance().getPois();
		}else
		{

			resultado = Mapa.getInstance().buscarPoi(nombre,tipo);
		}
		 
		
  		model.put("pois", resultado);
  		return new ModelAndView(model, "pois.hbs");
	}
	
}