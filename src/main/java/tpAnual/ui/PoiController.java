package tpAnual.ui;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;

public class PoiController {

	public static ModelAndView get(Request req, Response res){
		
		Map<String, Object> viewModel = new HashMap<String, Object>();

	 //TODO borrar
		Poi poi = (Poi)new Negocio(null,"Negocio",null,"compras",5);
		poi.setCalle("Strangford 1857");
		
		viewModel.put("poi", poi);
		
		return new ModelAndView(viewModel, "busqueda.hbs");
	}
}

