package tpAnual.procesos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tpAnual.Mapa;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.externo.sistemasExternos.PoiAEliminar;
import tpAnual.procesos.operaciones.Proceso;

public class ProcesoBajaPoi extends Proceso {
	
	public List<PoiAEliminar> poisExternos = new ArrayList<PoiAEliminar>();
	BajaPoiAdapter bajaPoiAdapter = new BajaPoiAdapter();
	
	public ProcesoBajaPoi(){ //Cuando se instancia la clase se trae la ultima modificacion del archivo
		poisExternos = bajaPoiAdapter.consultar();
	}
	
	public void realizarProceso(){ //Command para el lanzador
		 poisExternos.forEach(poi -> this.eliminarPoi(poi.getId()));
	}
	 
	 public void eliminarPoi(int id){
		Mapa mapa = Mapa.getInstance();
		List<Poi> pois = new ArrayList<>();
		pois = mapa.pois;
		
//		mapa.pois.forEach(poi->{
//			if(poi.getId()==id){
//				mapa.baja(poi);
//				return;
//			}
//		});
		
		Iterator<Poi> iter = pois.iterator();

		while (iter.hasNext()) {
		    Poi poi = iter.next();

		    if (poi.getId()==id){
		        iter.remove();
		        mapa.baja(poi);
		    }
		}
	
	 }	    	 

}
