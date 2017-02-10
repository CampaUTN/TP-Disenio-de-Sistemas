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
import tpAnual.busquedas.RepositorioNoPersistente;
import tpAnual.busquedas.RepositorioPersistente;
import tpAnual.util.wrapper.PointWrapper;


public class BusquedasController {


	
	public static ModelAndView listarHardcodeado(Request req, Response res)
	{
		Map<String, List<Busqueda>> model = new HashMap<>();
		
		List<Poi> pois = new ArrayList<>();
		
		Poi poi1 = (Poi)new Negocio(new PointWrapper(0, 0),"Negocio",null,"compras",5);
		Poi poi2 = (Poi) new Banco(new PointWrapper(2, 2), "Banco Santander" , null);
		
		poi1.setCalle("Strangford");
		poi1.setDireccion(1857);
		
		poi2.setCalle("Avenida Rivadavia");
		poi2.setDireccion(458);
		
		pois.add(poi1);
		pois.add(poi2);
		
		Busqueda b1 = new Busqueda("hola", pois);
		b1.setFecha(Date.valueOf(LocalDate.now()));
		
		//BORRAR ESTO DE PRUEBA
		List<Busqueda> busqueda = new ArrayList<>();
		busqueda.add(b1);
		
		model.put("busqueda", busqueda);
		return new ModelAndView(model, "historico-consultas.hbs");
	}
	
	public static ModelAndView listar (Request req, Response res)
	{
		Map<String, List<Busqueda>> model = new HashMap<>();
		String fechaDesde = req.params("fechaDesde");
		String fechaHasta = req.params("fechaHasta");
		String cantidadPois = req.params("cantidadPois");
		String terminal = req.params("terminal");
		
	
		List<Busqueda> busqueda = new ArrayList<Busqueda>();

		
		
		busqueda.addAll(RepositorioPersistente.getInstance().listar());
		busqueda.addAll(RepositorioNoPersistente.getInstance().listar());
		
//		if(!fechaDesde.isEmpty()){
//			LocalDate desdeTime = LocalDate.parse(req.params(fechaDesde));
//			busqueda = busqueda.stream()
//					           .filter(res->res.esFechaPosterior(Date.valueOf(desdeTime)))
//					           .collect(Collectors.toList());
//		}
//		
//		if(!fechaDesde.isEmpty()){
//			LocalDate hastaTime = LocalDate.parse(req.params(fechaHasta));
//			busqueda = busqueda.stream()
//					           .filter(res->res.esFechaAnterior(Date.valueOf(hastaTime)))
//					           .collect(Collectors.toList());
//		
//		}
//		
//		if(!cantidadPois.isEmpty()){
//			busqueda = busqueda.stream()
//					           .filter(res->res.getResultado().size() == Integer.parseInt(cantidadPois))
//					           .collect(Collectors.toList());
//		}
//		
//		if(!terminal.isEmpty()){
//			busqueda = busqueda.stream()
//					           .filter(res->res.getTerminal()????.equals(terminal))
//					           .collect(Collectors.toList());
//		}
		
		model.put("busqueda", busqueda);
		return new ModelAndView(model, "historico-consultas.hbs");
		
	}
	
	
public static ModelAndView verPois(Request req, Response res){
      //ESTE METODO DEBERIA LLEVARME AL BUSQUEDA-POIS.HBS DONDE SE DEBERIAN MOSTRAR
	    int busqueda = Integer.parseInt(req.queryParams("nroBusqueda"));
	    Busqueda busquedaPoi = (RepositorioPersistente.getInstance().buscarPorId(busqueda)); //Y EL REPO NO PERSISTENTE????
	    return new ModelAndView(busquedaPoi, "busqueda-pois.hbs");
    }




//    public static ModelAndView poisDeBusqueda(Request req, Response res){
//	  
//   }
	
}