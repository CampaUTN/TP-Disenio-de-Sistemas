package tpAnual.procesos;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.Mapa;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.externo.sistemasExternos.LocalComercialExternoDTO;
import tpAnual.procesos.operaciones.Proceso;

public class ProcesoActualizarLocales extends Proceso{
	
	LocalComercialAdapter localAdapter = new LocalComercialAdapter();
	
	public void realizarProceso(){
		List<Poi> pois = Mapa.getInstance().pois(); 
		List<LocalComercialExternoDTO> locales = localAdapter.consultar();
		locales.forEach(local->cambiarLocalComercial(pois,local));
	}
	
	public void cambiarLocalComercial(List<Poi> pois,LocalComercialExternoDTO actualizado){
		Poi poiAModificar = findPoi(pois, actualizado.getNombre());
		if(poiAModificar != null)
			poiAModificar.cambiarTags(actualizado.getPalabrasClave());
	}

	private Poi findPoi(List<Poi> pois, String nombrePoi) {
		Poi poiAModificar = pois.stream()
			.filter(poi->poi.getNombre().equals(nombrePoi))
			.collect(Collectors.toList())
			.get(0);
		return poiAModificar;
	}
}
