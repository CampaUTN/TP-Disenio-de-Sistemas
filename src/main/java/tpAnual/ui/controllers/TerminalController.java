package tpAnual.ui.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Terminal;
import tpAnual.ui.RepositorioTerminales;

public class TerminalController {

	public static ModelAndView get(Request req, Response res){
		
		Map<String, Object> viewModel = new HashMap<String, Object>();

	 //TODO borrar
		
		Terminal terminal = new Terminal(12);
		terminal.setNombre("test");
		
		viewModel.put("terminal",terminal);
		
		return new ModelAndView(viewModel, "terminal.hbs");
	}
	
	public static ModelAndView listar(Request req, Response res){
		
		Map<String, List<Terminal>> model = new HashMap<>();
  		List<Terminal> terminales = RepositorioTerminales.instancia.listar();
		
  		model.put("terminales", terminales);
  		return new ModelAndView(model, "terminal.hbs");
	
	}
	
	public static ModelAndView alta(Request req, Response res){
		
		//ACA DEBERIAMOS TENER EL REPO QUE SE ENCARGEU DE BUSCAR LOS POIS EN LA BD Y LUEGO MOSTRARLOS		
		
		Map<String, List<Terminal>> model = new HashMap<>();
  		//List<Proyecto> proyectos = RepositorioProyectos.instancia.listar();
  		
  		return new ModelAndView(model, "altaTerminal.hbs");
	
	}
	
	public static ModelAndView modificar(Request req, Response res){
		
		//ACA DEBERIAMOS TENER EL REPO QUE SE ENCARGEU DE BUSCAR LOS POIS EN LA BD Y LUEGO MOSTRARLOS		
		
		Map<String, List<Terminal>> model = new HashMap<>();
  		//List<Proyecto> proyectos = RepositorioProyectos.instancia.listar();
  		
//		Terminal terminal = Mapa.getInstance().buscarTerminalPorId(0);
		Terminal terminal = new Terminal(0);
		terminal.setNombre("termi");
//  		return new ModelAndView(model, "modificarTerminal.hbs");
		return new ModelAndView(terminal, "modificarTerminal.hbs");
	
	}
	
}

