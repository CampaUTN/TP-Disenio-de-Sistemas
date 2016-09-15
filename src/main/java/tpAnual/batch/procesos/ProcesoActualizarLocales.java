package tpAnual.batch.procesos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import tpAnual.Mapa;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.externo.sistemasExternos.LocalComercialExternoDTO;

@Entity
@DiscriminatorValue("proc_actualizar")
public class ProcesoActualizarLocales extends Proceso{
	
	@Transient
	LocalComercialAdapter localAdapter = new LocalComercialAdapter("src/test/resources/localesComerciales.txt");
	
	@Column(name="proc_actualizar")
	private long nro_proc_actualizar;
	
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
