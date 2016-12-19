package tpAnual.ui.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.ui.RepositorioTerminales;
import tpAnual.util.wrapper.PointWrapper;

public class AdministrarPoiController implements WithGlobalEntityManager, TransactionalOps {

	public static ModelAndView get(Request req, Response res) {

		Map<String, Object> viewModel = new HashMap<String, Object>();

		return new ModelAndView(viewModel, "administrarPoi.hbs");
	}

	public static ModelAndView listar(Request req, Response res) {

		String nombre = req.queryParams("nombre");
		String tipo = req.queryParams("tipo");
		
		Map<String, List<Poi>> model = new HashMap<>();
		
		List<Poi> pois = Mapa.getInstance().buscarPoi(nombre,tipo);
				
		model.put("pois", pois);
		return new ModelAndView(model, "administrarPoi.hbs");

	}

	public static ModelAndView editar(Request req, Response res) {
		long poiId = Long.parseLong(req.queryParams("poiId"));
		
		Map<String, Poi> viewModel = new HashMap<>();

		Poi poi = Mapa.getInstance().poiDeId(poiId);
		return new ModelAndView(poi, "modificarPoi.hbs");
	}
	
	public Void guardar(Request req, Response res){
		
		String nombre = req.queryParams("nuevoNombre");
		double latitud = 0;
		double longitud = 0; 
		
		if(req.queryParams("nuevaLatitud")!=null && req.queryParams("nuevaLongitud")!=null){
			latitud = Double.parseDouble(req.queryParams("nuevaLatitud"));
			longitud = Double.parseDouble(req.queryParams("nuevaLongitud"));
		}
		
//		int comuna = Integer.parseInt(req.queryParams("nuevaComuna"));
		long id = Long.parseLong(req.queryParams("poiId"));
		
		withTransaction(() ->{
			Poi poi = Mapa.getInstance().poiDeId(id);
			poi.setNombre(nombre);
//			poi.setUbicacion(new PointWrapper(latitud, longitud));
//			poi.setNumeroComuna(comuna);
			Mapa.getInstance().actualizar(poi);
		});
		
		res.redirect("/administrarPoi?tipo=Todos&nombre=all");
		
		return null;
	}
	
	
	public Void baja(Request req, Response res){
		
		long poiId = Integer.parseInt(req.queryParams("poiId"));
		
		withTransaction(() ->{
			Poi poi = Mapa.getInstance().poiDeId(poiId);
			Mapa.getInstance().baja(poi);
		});
		
		res.redirect("/administrarPoi?tipo=Todos&nombre=all");
		return null;
	}
	
	public static ModelAndView alta(Request req, Response res){
		Map<String, List<Poi>> model = new HashMap<>();
  		
  		return new ModelAndView(model, "altaPoi.hbs");
	
	}
	
	public Void altaAgregar(Request req, Response res){
		
		String nombre = req.queryParams("nombre");
		int direccion = Integer.parseInt(req.queryParams("direccion"));
		PointWrapper punto = new PointWrapper(1,2);
		
		Negocio negocio = new Negocio();
		negocio.setDireccion(direccion);
		negocio.setNombre(nombre); 
		negocio.setUbicacion(punto);
		
		withTransaction(() ->{
			Mapa.getInstance().alta(negocio);
		});
		
		res.redirect("/administrarPoi?tipo=Todos&nombre=all");
		
		return null;
	}
}
