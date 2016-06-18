package tpAnual;

import java.util.ArrayList;
import java.util.List;

public class ReportePorTerminal{
	
	private Boolean detalladoActivado=false;
	private int contador;
	
	public void reportar(List<RegistroBusqueda> registros) {
		if(detalladoActivado){
			this.mostrarDetallado(this.asignarRegistros(registros));
		}
		this.mostrarCompleto(this.asignarRegistros(registros));
	}
	
	private void mostrarDetallado(List<ElementoReporte> reportes){
		reportes.forEach(reporte->{
			System.out.println(reporte.getTerminal().getNombre());
			this.imprimirCantidades(reporte);
		});
	}
	
	private void mostrarCompleto(List<ElementoReporte> reportes){
		reportes.forEach(reporte->{
			System.out.println(reporte.getTerminal().getNombre());
			System.out.println(this.sumatoria(reporte.getBusquedasParciales()));
		});
	}
	
	private void imprimirCantidades(ElementoReporte reporte){
		reporte.getBusquedasParciales().forEach(unaCantidad->System.out.println(unaCantidad));
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
			.filter(elemento->elemento.getTerminal().getId() == registro.getTerminal().getId())
			.findFirst()
			.get()
			.agregarBusquedasParciales(registro.getCantidad());
	}
	
	private void agregarSiNoEstaTerminal(RegistroBusqueda registro,List<ElementoReporte> listadoReporte){
		if(!this.estaElTerminal(registro.getTerminal(), listadoReporte)){
			ElementoReporte elemento = new ElementoReporte();
			elemento.setTerminal(registro.getTerminal());
			listadoReporte.add(elemento);
		}
	}
	
	private boolean estaElTerminal(Terminal terminalParam, List<ElementoReporte> listadoReporte){
		return listadoReporte.stream()
				.anyMatch(elemento-> elemento.getTerminal().getId() == (terminalParam.getId())); //no hago el equals pq no es Integer
	}
	
}
