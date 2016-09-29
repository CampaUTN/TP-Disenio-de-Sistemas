package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;

import tpAnual.Mapa;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.externo.sistemasExternos.PoiAEliminarDTO;

@Entity
public class ProcesoBajaPoi extends Proceso {
	@Transient
	public List<PoiAEliminarDTO> poisExternos = new ArrayList<PoiAEliminarDTO>();
	
	@Transient
	BajaPoiAdapter bajaPoiAdapter = new BajaPoiAdapter();
	
	
	public ProcesoBajaPoi(){ //Cuando se instancia la clase se trae la ultima modificacion
		poisExternos = bajaPoiAdapter.consultar();
	}
	
	public void ejecutar(){ //Command para el lanzador
		 poisExternos.forEach(poi -> this.eliminarPoi(poi.getId()));
	}
	 
	 public void eliminarPoi(long id){
		Mapa mapa = Mapa.getInstance();
		List<Poi> pois = mapa.getPois();
		mapa.baja(pois.stream().filter(p -> p.esDeId(id)).collect(Collectors.toList()).get(0));
	 }	
	  

}
