package tpAnual.ui;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
	public static void main(String[] args) {
		configurarSpark();
		Router.configure();
	}
	
	
	public static void configurarSpark(){
		Spark.port(4567);
		Spark.staticFileLocation("/ui");
	}
	
	public static String estilizar(String contenido){
		return contenido;
	}
	
	public static String paginaPrincipal(Request req, Response res){
		return "hola";
	}
}
