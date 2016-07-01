package tpAnual.externo.adapters;

import java.io.File;



import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
//import tpAnual.procesos.operaciones.Proceso;
//import java.util.stream.Collectors;

import tpAnual.externo.mocks.MockBajaPoi;
import tpAnual.externo.sistemasExternos.PoiAEliminarDTO;
import tpAnual.externo.sistemasExternos.UrlExterna;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class BajaPoiAdapter{
		
	//private MockBajaPoi mockBajaPoi = new MockBajaPoi();
	
	public List<PoiAEliminarDTO> consultar(String url, String path){
		UrlExterna urlExterna = new UrlExterna(url, path);
		return jsonToPoiAEliminar(urlExterna.consultarUrl("", "").getEntity(String.class));
		
	}
	
	
	
	 public List<PoiAEliminarDTO> jsonToPoiAEliminar(String contenido){
		 
		//File reader = mockBajaPoi.consultar();
		 
		List<PoiAEliminarDTO> pois = new ArrayList<PoiAEliminarDTO>();
		
		Gson gson = new Gson();
		
		pois = gson.fromJson(contenido, new TypeToken<List<PoiAEliminarDTO>>() {}.getType());
		
		return pois;
		
      }
	 
}
