package tpAnual.ui.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.POIs.Poi;
import tpAnual.busquedas.Busqueda;
import tpAnual.busquedas.RepositorioBusqueda;
import tpAnual.busquedas.RepositorioPersistente;
import tpAnual.ui.ParametroBusqueda;
import tpAnual.ui.RepositorioTerminales;

public class PoiController {

	public static ModelAndView get(Request req, Response res){

		String id = req.queryParams("id");		
		Map<String, Object> viewModel = new HashMap<String, Object>();
		
		Poi poi = Mapa.getInstance().poiDeId(Long.parseLong(id));		 		
		viewModel.put("poi", poi);	
	
		return new ModelAndView(viewModel, "poi.hbs");
	}
	
	public static ModelAndView listar(Request req, Response res){
		String nombre = req.queryParams("nombre");
		String tipo = req.queryParams("tipo");
		String calle = req.queryParams("calle");
		String tags = req.queryParams("tags");
		long nro = Long.parseLong(req.queryParams("terminal"));
		
		Terminal terminal = RepositorioTerminales.getInstance().buscarPorId(nro);
		
		Map<String, Object> model = new HashMap<String,Object>();
		
		ParametroBusqueda parametro = new ParametroBusqueda(nombre, tipo, calle,terminal, tags);
		
		List <Poi> resultado = Mapa.getInstance().buscar(parametro);
		
		RepositorioPersistente.getInstance().registrarBusqueda(tags, resultado);
		model.put("terminal",terminal);		
  		model.put("pois", resultado);
  		return new ModelAndView(model, "pois.hbs");
	}
	
}