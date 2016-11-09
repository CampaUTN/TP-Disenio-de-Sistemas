package tpAnual.ui;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
	public static void main(String[] args) {
		
		
		configurarSpark();
		Router.configure();
		
		 //Cuando recibe una request a esa ruta (/), ejecuta la lambda.
		//Spark.get("/", (request, response) -> { return estilizar("<html><body><h1>hola mundo!</h1></body></html>");} );
		 
		//localhost:4567/nombre?=Juan
		/* Spark.get("/",(request, response) -> {
			String nombreVistante = request.queryParams("nombre");
			return "<html><body><h1>hola " + nombreVistante + "!</h1></body></html>";
			} ); */
		
		//localhost:4567/saludador/juan
//		Spark.get("/saludador/:nombre", (request, response) -> {
//			String nombreVistante = request.params("nombre");
//			return "<html><body><h1>hola " + nombreVistante + "!</h1></body></html>";
//			} );
	}
	
	
	public static void configurarSpark(){
		Spark.port(4567);
		Spark.staticFileLocation("/ui");
	}
	
	public static String estilizar(String contenido){
		return contenido;
		//return "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"screen\" />"+contenido;
	}
	
	public static String paginaPrincipal(Request req, Response res){
		return "hola";
	}
}
