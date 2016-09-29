package tpAnual.externo.adapters;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tpAnual.externo.sistemasExternos.PoiAEliminarDTO;
import tpAnual.externo.sistemasExternos.UrlExterna;



public class BajaPoiAdapter{
	
	private String url;
	private String path;
	
	
	public List<PoiAEliminarDTO> consultar(){
		UrlExterna urlExterna = new UrlExterna(url, path);
		return jsonToPoiAEliminar(urlExterna.consultarUrl("", "").getEntity(String.class));
		
	}
	
	 public List<PoiAEliminarDTO> jsonToPoiAEliminar(String contenido){
		List<PoiAEliminarDTO> pois = new ArrayList<PoiAEliminarDTO>();
		Gson gson = new Gson();
		pois = gson.fromJson(contenido, new TypeToken<List<PoiAEliminarDTO>>() {}.getType());
		return pois;
      }
	 
	 public void setUrl(String url){
		 this.url=url;
	 }
	 
	 public String getUrl(){
		 return this.url;
	 }
	 
	 public void setPath(String path){
		 this.path=url;
	 }
	 
	 public String getPath(){
		 return this.path;
	 }
	 
}
