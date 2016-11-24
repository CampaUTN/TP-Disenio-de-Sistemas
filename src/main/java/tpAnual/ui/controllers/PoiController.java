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
		
		Map<String, Object> viewModel = new HashMap<String, Object>();

	 //TODO borrar
		Poi poi = (Poi)new Negocio(null,"Negocio",null,"compras",5);
		poi.setCalle("Strangford 1857");
		
		viewModel.put("poi", poi);
		
		return new ModelAndView(viewModel, "busqueda.hbs");
	}
	
	public static ModelAndView listar(Request req, Response res){
		
		//ACA DEBERIAMOS TENER EL REPO QUE SE ENCARGEU DE BUSCAR LOS POIS EN LA BD Y LUEGO MOSTRARLOS		
		String nombre = req.queryParams("nombre");
		String tipo = req.queryParams("tipo");
		
		Map<String, List<Poi>> model = new HashMap<String, List<Poi>>();
  		//List<Proyecto> proyectos = RepositorioProyectos.instancia.listar();
  		
		String busqueda = nombre + " " + tipo;
		List<Poi> resultado = Mapa.getInstance().buscarPoi(nombre,tipo);
		
  		model.put("pois", resultado);
  		return new ModelAndView(model, "pois.hbs");
	}
	
}