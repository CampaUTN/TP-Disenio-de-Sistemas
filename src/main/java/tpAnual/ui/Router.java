package tpAnual.ui;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.build();

		Spark.staticFiles.location("/ui");
		
		Spark.get("/", Server::paginaPrincipal);		
		Spark.get("/poi", PoiController::get,engine);
		Spark.get("/busqueda", PoiController::listar,engine);
		Spark.get("/terminal", TerminalController::listar,engine);
		Spark.get("/altaTerminal", TerminalController::alta,engine);
		Spark.get("/modificarTerminal", TerminalController::modificar,engine);
		Spark.get("/historico-consultas", BusquedasController::listar,engine);
		Spark.get("/administrar-pois", AdministrarPoiController::get,engine);

	//Spark.get("/conversor/resultado", ConversorController::showResultado,				engine);
		//Spark.get("/persona", PersonaController::get, engine);
	}
}
