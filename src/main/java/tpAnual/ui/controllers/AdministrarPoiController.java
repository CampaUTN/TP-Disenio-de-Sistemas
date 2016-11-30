package tpAnual.ui.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Mapa;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.util.wrapper.PointWrapper;

public class AdministrarPoiController  implements WithGlobalEntityManager, TransactionalOps {

	public static ModelAndView get(Request req, Response res) {

		Map<String, Object> viewModel = new HashMap<String, Object>();

		return new ModelAndView(viewModel, "administrarPoi.hbs");
	}

	public static ModelAndView listar(Request req, Response res) {

		String nombre = req.queryParams("nombre");
		String tipo = req.queryParams("tipo");
		
		Map<String, List<Poi>> model = new HashMap<>();
		
		List<Poi> pois = new ArrayList<Poi>();
//				(nombrePoi=="" || nombrePoi==null)
//				?Mapa.getInstance().getPois()
//				:Mapa.getInstance().buscarPoi(nombrePoi);
		
		// testeo. TODO BORRAR
		Set<String> tags = new HashSet<String>();
		PointWrapper ubicacion = new PointWrapper(54, 10);
		Poi poi = new EstacionDeColectivo(ubicacion, "107", tags, 0, "");
		pois.add(poi);
				
		model.put("pois", pois);
		return new ModelAndView(model, "administrarPoi.hbs");

	}

	public static ModelAndView editar(Request req, Response res) {
		long poiId = Long.parseLong(req.queryParams("id"));
		
		Map<String, Poi> viewModel = new HashMap<>();

		Poi poi = Mapa.getInstance().poiDeId(poiId);
		viewModel.put("poi", poi);
		return new ModelAndView(poi, "modificarPoi.hbs");
	}
	
	public Void guardar(Request req, Response res){
		String nombre = req.queryParams("nuevoNombre");
		double latitud = Double.parseDouble(req.queryParams("nuevaLatitud"));
		double longitud = Double.parseDouble(req.queryParams("nuevaLongitud"));
		int comuna = Integer.parseInt(req.queryParams("nuevaComuna"));
		long id = Long.parseLong(req.queryParams("id"));
		
		Poi poi = Mapa.getInstance().poiDeId(id);
		poi.setNombre(nombre);
		poi.setUbicacion(new PointWrapper(latitud, longitud));
		poi.setNumeroComuna(comuna);
		
		res.redirect("/administrarPoi");
		
		return null;
	}
	
	
	public Void baja(Request req, Response res){
		
		long poiId = Integer.parseInt(req.queryParams("poiId"));
		Poi poi = Mapa.getInstance().poiDeId(poiId);
		
		withTransaction(() ->{
			Mapa.getInstance().baja(poi);
		});
		
		res.redirect("/administrarPoi");
		return null;
	}
}
