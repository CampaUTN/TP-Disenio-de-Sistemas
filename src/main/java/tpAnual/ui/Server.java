package tpAnual.ui;

import spark.Request;
import spark.Response;
import spark.Spark;
import tpAnual.util.Reseter;

public class Server {
	public static void main(String[] args) {

		Reseter.resetSingletons();
		configurarSpark();
		Router.configure();

		Reseter.resetSingletons();
	}
	
	
	public static void configurarSpark(){
		Spark.port(4567);
		Spark.staticFileLocation("/ui");
	}
	
	public static String paginaPrincipal(Request req, Response res){
		return "hola";
	}
}
