package tpAnual.externo.sistemasExternos;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UrlExterna {
	private Client client;
	private String url;
	private String path;
	//private static final String API_GOOGLE = "http://demo3537367.mockable.io/trash";
	//private static final String RESOURCE = "pois";

	//Inicializacion del cliente.
	public UrlExterna(String urlExt, String path) {
		this.client = Client.create();
		this.url = urlExt;
		this.path = path;
	}
	    
	//Prueba de concepto de un parametro y los mensajes por separado para identificar los tipos de datos.
	public ClientResponse consultarUrl(String filter, String value){
		WebResource recurso = this.client.resource(url).path(path);
		WebResource recursoConParametros = recurso.queryParam("q",filter + ":" + value);
	    WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
	    ClientResponse response = builder.get(ClientResponse.class);
	    return response;
	}
}
