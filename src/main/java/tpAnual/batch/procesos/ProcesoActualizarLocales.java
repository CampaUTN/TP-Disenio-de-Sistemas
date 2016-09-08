package tpAnual.batch.procesos;

import java.util.List;

import tpAnual.Mapa;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.externo.sistemasExternos.LocalComercialExternoDTO;

public class ProcesoActualizarLocales extends Proceso{
	
	LocalComercialAdapter localAdapter = new LocalComercialAdapter("src/test/resources/localesComerciales.txt");
	
	@Override
	public void ejecutar(){
		List<LocalComercialExternoDTO> locales = localAdapter.consultar();
		locales.forEach(local->cambiarLocalComercial(local));
	}
	
	public void cambiarLocalComercial(LocalComercialExternoDTO actualizado){
		Poi poiAModificar = findPoi(actualizado.getNombre());
		if(poiAModificar != null)
			poiAModificar.setTags(actualizado.getPalabrasClave());
	}

	private Poi findPoi(String nombrePoi) {
		return Mapa.getInstance().poisPendientesDeModificar(nombrePoi);
		
	}
}
