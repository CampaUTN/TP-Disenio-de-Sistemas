package tpAnual.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Ej llamada: generadorReportes.reporteFecha(mapa.buscadorTexto.getRegistros());
 */
public class ReportePorFecha{

	public List<ElementoReporte> reportar(List<RegistroBusqueda> registros){
		List<ElementoReporte> listadoReporte = new ArrayList<ElementoReporte>();
		
		registros.forEach(registro->{
					agregarSiNoEstaFecha(registro,listadoReporte);
					buscarElementoYSumarBusqueda(registro,listadoReporte);
		});
		return listadoReporte;
		//mostrarReporteFecha(listadoReporte);
	}
	
	// Esto se haria mediante la GUI cuando la tengamos
	public void mostrarReporteFecha(List<ElementoReporte> listadoReporte){
		listadoReporte.forEach(elemento->{
						System.out.println(elemento.getFecha().toString());
						System.out.println(elemento.getCantidadBusquedas());
					}
				);
	}
	
	private void buscarElementoYSumarBusqueda(RegistroBusqueda registro,List<ElementoReporte> listadoReporte){
		listadoReporte.stream()
				.filter(elemento->elemento.esDeLaFecha(registro.getFecha()))
				.findFirst()
				.get()
				.registrarBusqueda();
	}
	
	private void agregarSiNoEstaFecha(RegistroBusqueda registro,List<ElementoReporte> listadoReporte){
		if(!this.estaLaFecha(registro.getFecha(), listadoReporte)){
			listadoReporte.add(ElementoReporte.crearConFecha(registro.getFecha()));
		}
	}
	
	private boolean estaLaFecha(LocalDate fecha, List<ElementoReporte> listadoReporte){
		return listadoReporte.stream()
				.anyMatch(elemento-> elemento.esDeLaFecha(fecha));
	}
	
}
