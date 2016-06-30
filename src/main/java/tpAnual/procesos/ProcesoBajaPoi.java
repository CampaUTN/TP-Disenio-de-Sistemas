package tpAnual.procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tpAnual.Mapa;
import tpAnual.externo.adapters.BajaPoiAdapter;
import tpAnual.externo.sistemasExternos.PoiAEliminar;
import tpAnual.procesos.operaciones.Proceso;

public class ProcesoBajaPoi extends Proceso {
	
	List<PoiAEliminar> poisExternos = new ArrayList<PoiAEliminar>();
	BajaPoiAdapter bajaPoiAdapter = new BajaPoiAdapter();
	
	public ProcesoBajaPoi(){ //Cuando se instancia la clase se trae la ultima modificacion del archivo
		poisExternos = bajaPoiAdapter.consultar();
	}
	
	 public void realizarProceso(){ //Command para el lanzador
			poisExternos.forEach(poi -> this.eliminarPoi(poi));
	}
	 
	 public void eliminarPoi(PoiAEliminar poiAEliminar){
		    int id = poiAEliminar.getId();
		    Mapa.getInstance().baja(Mapa.getInstance().pois.stream()
		    	    .filter(poi -> poi.getId() == id).collect(Collectors.toList()).get(0));
		}	 

}
