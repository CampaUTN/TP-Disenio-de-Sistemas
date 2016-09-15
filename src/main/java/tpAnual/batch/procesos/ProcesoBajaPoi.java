package tpAnual.batch.procesos;

import java.util.ArrayList;

import java.util.List;

import tpAnual.Mapa;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.externo.sistemasExternos.PoiAEliminarDTO;

import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("proc_baja")
public class ProcesoBajaPoi extends Proceso {
	
	@Column(name="proc_baja")
	private long nro_proc_baja;
	
	@Transient
	public List<PoiAEliminarDTO> poisExternos = new ArrayList<PoiAEliminarDTO>();
	
	@Transient
	BajaPoiAdapter bajaPoiAdapter = new BajaPoiAdapter();
	
	public ProcesoBajaPoi(){ //Cuando se instancia la clase se trae la ultima modificacion
		poisExternos = bajaPoiAdapter.consultar();
	}
	
	@Override
	public void ejecutar(){ //Command para el lanzador
		 poisExternos.forEach(poi -> this.eliminarPoi(poi.getId()));
	}
	 
	 public void eliminarPoi(long id){
		Mapa mapa = Mapa.getInstance();
		List<Poi> pois = mapa.getPois();
		mapa.baja(pois.stream().filter(p -> p.esDeId(id)).collect(Collectors.toList()).get(0));
	 }	
	  

}
