package tpAnual.reportes;

import java.util.ArrayList;
import java.util.List;

import tpAnual.Terminal;

public class ReportePorTerminal{
	
	private Boolean detalladoActivado=false;
	
	public void mostrarReportes(List<RegistroBusqueda> registros) {
		if(detalladoActivado){
			this.mostrarDetallado(this.reportar(registros));
		}
		this.mostrarCompleto(this.reportar(registros));
	}
	
	// Esto se haria con la GUI cuando la tengamos
	private void mostrarDetallado(List<ElementoReporte> reportes){
		reportes.forEach(reporte->{
			System.out.println(reporte.getTerminal().getNombre());
			this.imprimirCantidades(reporte);
		});
	}

	// Esto se haria con la GUI cuando la tengamos
	private void mostrarCompleto(List<ElementoReporte> reportes){
		reportes.forEach(reporte->{
			System.out.println(reporte.getTerminal().getNombre());
			System.out.println(reporte.getBusquedasParciales().stream().mapToInt(i->i).sum());
		});
	}
	
	// Esto se haria con la GUI cuando la tengamos
	private void imprimirCantidades(ElementoReporte reporte){
		reporte.getBusquedasParciales().forEach(unaCantidad->System.out.println(unaCantidad));
	}

	public List<ElementoReporte> reportar(List<RegistroBusqueda> registros){
		List<ElementoReporte> listadoReporte = new ArrayList<ElementoReporte>();
		
		registros.forEach(registro->{
			agregarSiNoEstaTerminal(registro,listadoReporte);
			buscarElementoYAgregarBusquedas(registro,listadoReporte);
		});
		
		return listadoReporte;
	}
	
	private void buscarElementoYAgregarBusquedas(RegistroBusqueda registro, List<ElementoReporte> listadoReporte){
		listadoReporte.stream()
			.filter(elemento->elemento.esDeLaTerminal(registro.getTerminal()))
			.findFirst()
			.get()
			.agregarBusquedasParciales(registro.getCantidadPois());
	}
	
	private void agregarSiNoEstaTerminal(RegistroBusqueda registro,List<ElementoReporte> listadoReporte){
		if(!this.estaElTerminal(registro.getTerminal(), listadoReporte)){
			listadoReporte.add(ElementoReporte.crearConTerminal(registro.getTerminal()));
		}
	}
	
	private boolean estaElTerminal(Terminal terminal, List<ElementoReporte> listadoReporte){
		return listadoReporte.stream()
				.anyMatch(elemento-> elemento.getTerminal().equals(terminal));
	}
}
