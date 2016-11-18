package tpAnual.ui.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Terminal;
import tpAnual.ui.RepositorioTerminales;

public class PerfilController implements WithGlobalEntityManager {

	public static ModelAndView mostrarPerfil(Request req, Response res){
		
		String nombre = req.queryParams("nombre");
		String password = req.queryParams("contrasenia");
		
		Map<String, Object> viewModel = new HashMap<String, Object>();
		
		
		Terminal terminal = RepositorioTerminales.instancia.buscarPorNombre(nombre);
		
		if(terminal != null){
			return new ModelAndView(viewModel, "perfil-acciones.hbs");
		}else
		{
			return new ModelAndView(viewModel, "perfil-acciones.hbs");
		}
		
		
	}
	
}
