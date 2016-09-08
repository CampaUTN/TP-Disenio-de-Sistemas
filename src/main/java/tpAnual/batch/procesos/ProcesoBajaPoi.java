package tpAnual.batch.procesos;

import java.util.ArrayList;

import java.util.List;

import tpAnual.Mapa;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.externo.sistemasExternos.PoiAEliminarDTO;

import java.util.stream.Collectors;

public class ProcesoBajaPoi extends Proceso {
	
	public List<PoiAEliminarDTO> poisExternos = new ArrayList<PoiAEliminarDTO>();
	BajaPoiAdapter bajaPoiAdapter = new BajaPoiAdapter();
	
	public ProcesoBajaPoi(){ //Cuando se instancia la clase se trae la ultima modificacion
		poisExternos = bajaPoiAdapter.consultar();
	}
	
	@Override
	public void ejecutar(){ //Command para el lanzador
		 poisExternos.forEach(poi -> this.eliminarPoi(poi.getId()));
	}
	 
	 public void eliminarPoi(Integer id){
		Mapa mapa = Mapa.getInstance();
		List<Poi> pois = new ArrayList<>();
		pois = mapa.pois;
		mapa.baja(pois.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList()).get(0));
	 }	
	  

}
