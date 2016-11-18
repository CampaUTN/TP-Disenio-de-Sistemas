package tpAnual.ui.controllers;

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
		
		Map<String, List<Poi>> model = new HashMap<>();
  		//List<Proyecto> proyectos = RepositorioProyectos.instancia.listar();
  		
		//BORRAR ESTO DE PRUEBA
		List<Poi> pois = new ArrayList<Poi>();
		
		Poi poi1 = (Poi)new Negocio(new PointWrapper(0, 0),"Negocio",null,"compras",5);
		Poi poi2 = (Poi) new Banco(new PointWrapper(2, 2), "Banco Santander" , null);
		
		poi1.setCalle("Strangford");
		poi1.setDireccion(1857);
		
		poi2.setCalle("Avenida Rivadavia");
		poi2.setDireccion(458);
		
		pois.add(poi1);
		pois.add(poi2);
		
  		model.put("pois", pois);
  		return new ModelAndView(model, "pois.hbs");
	
	}
	
}

