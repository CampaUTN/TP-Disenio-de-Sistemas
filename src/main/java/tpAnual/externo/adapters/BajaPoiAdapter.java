package tpAnual.externo.adapters;

import java.io.File;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import tpAnual.procesos.operaciones.Proceso;
import java.util.stream.Collectors;

import tpAnual.Mapa;
import tpAnual.externo.mocks.MockBajaPoi;
import tpAnual.externo.sistemasExternos.PoiAEliminar;

import org.apache.commons.io.FileUtils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;


import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;


public class BajaPoiAdapter {
	
	    private Client client;
	    private static final String API_GOOGLE = "http://demo3537367.mockable.io/trash";
	    private static final String RESOURCE = "pois";

	    //Inicializacion del cliente.
	    public BajaPoiAdapter() {
	        this.client = Client.create();
	    }
	    
	    
	  //Prueba de concepto de un parametro y los mensajes por separado para identificar los tipos de datos.
	    public ClientResponse consultar(String filter, String value){
	        WebResource recurso = this.client.resource(API_GOOGLE).path(RESOURCE);
	        WebResource recursoConParametros = recurso.queryParam("q",filter + ":" + value);
	        WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
	        ClientResponse response = builder.get(ClientResponse.class);
	        return response;
	    }
	    
//	    public ClientResponse getBookByFilter(String filter, String value, String fields){
//	        ClientResponse response = this.client.resource(API_GOOGLE).path(RESOURCE)
//	                .queryParam("q",value).queryParam("fields",fields)
//	                .accept(MediaType.APPLICATION_JSON)
//	                .get(ClientResponse.class);
//	        return response;
//	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	
    private MockBajaPoi poiAEliminar = new MockBajaPoi();
    
	public void consultar(List<String> palabras){
		List<PoiAEliminar> poisExternos = new ArrayList<PoiAEliminar>();
		palabras.forEach(palabra->poisExternos.addAll(this.adaptar(poiAEliminar.consultar(palabra))));
		this.ordenarEliminacion(poisExternos);
	}
	
	
	 public List<PoiAEliminar> adaptar(File reader){
		List<PoiAEliminar> pois = new ArrayList<PoiAEliminar>();
		
		Gson gson = new Gson();
		String contenido = null;
		try {
			contenido = FileUtils.readFileToString(reader,Charset.defaultCharset());
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
		pois = gson.fromJson(contenido, new TypeToken<List<PoiAEliminar>>() {}.getType());
		
		return pois;
		
      }
	 
	 public void ordenarEliminacion(List<PoiAEliminar> poisExternos){
			poisExternos.stream().forEach(poi -> this.eliminarPoi(poi));
		}
	 
	 public void eliminarPoi(PoiAEliminar PoiAEliminar){
		    int id = PoiAEliminar.getId();
		    String fechaEliminado = PoiAEliminar.getFechaEliminado();
		    Mapa.getInstance().baja(Mapa.getInstance().pois.stream()
		    	    .filter(poi -> poi.getId() == id).collect(Collectors.toList()).get(0));
		}
	 
	 
}