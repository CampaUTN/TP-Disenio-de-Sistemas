package tpAnual.externo.adapters;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import tpAnual.Mapa;
import tpAnual.externo.mocks.MockBajaPoi;
import tpAnual.externo.sistemasExternos.PoiAEliminar;

import org.apache.commons.io.FileUtils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class BajaPoiAdapter {
	
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
