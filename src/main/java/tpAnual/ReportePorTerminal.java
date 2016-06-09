package tpAnual;

import java.util.ArrayList;
import java.util.List;

public class ReportePorTerminal{
	
	Boolean detalladoActivado=false;
	private int contador;
	
	public void reportar(List<RegistroBusqueda> registros) {
		if(detalladoActivado){
			this.mostrarDetallado(this.asignarRegistros(registros));
		}
		this.mostrarCompleto(this.asignarRegistros(registros));
	}
	
	private void mostrarDetallado(List<ElementoReporte> reportes){
		reportes.forEach(reporte->{
			System.out.println(reporte.terminal.getNombre());
			this.imprimirCantidades(reporte);
		});
	}
	
	private void mostrarCompleto(List<ElementoReporte> reportes){
		reportes.forEach(reporte->{
			System.out.println(reporte.terminal.getNombre());
			System.out.println(this.sumatoria(reporte.busquedasParciales));
		});
	}
	
	private void imprimirCantidades(ElementoReporte reporte){
		reporte.busquedasParciales.forEach(unaCantidad->System.out.println(unaCantidad));
	}
	
	private int sumatoria(List<Integer> lista){
		contador=0;
		lista.forEach(elem->contador=contador+elem);
		return contador;
	}

	private List<ElementoReporte> asignarRegistros(List<RegistroBusqueda> registros){
		List<ElementoReporte> listadoReporte = new ArrayList<ElementoReporte>();
		
		registros.forEach(registro->{
			agregarSiNoEstaTerminal(registro,listadoReporte);
			buscarElementoYAgregarBusquedas(registro,listadoReporte);
		});
		
		return listadoReporte;
	}
	
	private void buscarElementoYAgregarBusquedas(RegistroBusqueda registro, List<ElementoReporte> listadoReporte){
		listadoReporte.stream()
			.filter(elemento->elemento.terminal.getId() == registro.getTerminal().getId())
			.findFirst()
			.get()
			.agregarBusquedasParciales(registro.getCantidad());
	}
	
	private void agregarSiNoEstaTerminal(RegistroBusqueda registro,List<ElementoReporte> listadoReporte){
		if(!this.estaElTerminal(registro.getTerminal(), listadoReporte)){
			ElementoReporte elemento = new ElementoReporte();
			elemento.terminal=registro.getTerminal();
			listadoReporte.add(elemento);
		}
	}
	
	private boolean estaElTerminal(Terminal terminalParam, List<ElementoReporte> listadoReporte){
		return listadoReporte.stream()
				.anyMatch(elemento-> elemento.terminal.getId() == (terminalParam.getId())); //no hago el equals pq no es Integer
	}
	
}
