package tpAnual.ui.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.util.wrapper.PointWrapper;

public class PoiController {

	public static ModelAndView get(Request req, Response res){

		String id = req.queryParams("id");
		
		Map<String, Object> viewModel = new HashMap<String, Object>();
		
	 	 //TODO borrar
		Poi poi = Mapa.getInstance().poiDeId(Long.parseLong(id));
	
		 		
		viewModel.put("poi", poi);		 		
		 	
	
		return new ModelAndView(viewModel, "poi.hbs");
	}
	
	public static ModelAndView listar(Request req, Response res){
			
		String nombre = req.queryParams("nombre");
		String tipo = req.queryParams("tipo");
		String calle = req.queryParams("calle");
		String numeroTerminal = req.queryParams("terminal");
		Map<String, Object> model = new HashMap<String,Object>();
		
  		List <Poi> resultado = new ArrayList<>();
		
		resultado = Mapa.getInstance().buscarPoi(nombre,tipo, calle);
		
		model.put("terminal",new Terminal(Integer.parseInt(numeroTerminal)));		
  		model.put("pois", resultado);
  		return new ModelAndView(model, "pois.hbs");
	}
	
}