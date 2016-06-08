package tpAnual;

import java.util.ArrayList;
import java.util.List;

public class ReportePorTerminal{
	
	Boolean detalladoActivado=false;
	
	public void reportar(List<RegistroBusqueda> registros) {
		if(detalladoActivado){
			this.mostrarDetallado(this.asignarRegistros(registros));
		}
		this.mostrarCompleto(this.asignarRegistros(registros));
	}
	
	//TODO mostrarDetallado muestra la lista elemento por elemento
	//TODO mostrarCompleto hace antes un sum de de esa lista y la muestra
	//TODO Falta algo con el tema de guarda el id del terminal y el nombre y mostrarlo por pantalla, revisar
	
	private void mostrarDetallado(List<ElementoReporte> reportes){
		
	}
	private void mostrarCompleto(List<ElementoReporte> reportes){
		
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
			.filter(elemento->elemento.terminal.id == registro.terminal.id)
			.findFirst()
			.get()
			.agregarBusquedasParciales(registro.cantidadEncontrada);
	}
	
	private void agregarSiNoEstaTerminal(RegistroBusqueda registro,List<ElementoReporte> listadoReporte){
		if(!this.estaElTerminal(registro.terminal, listadoReporte)){
			ElementoReporte elemento = new ElementoReporte();
			elemento.terminal=registro.terminal;
			listadoReporte.add(elemento);
		}
	}
	
	private boolean estaElTerminal(Terminal terminalParam, List<ElementoReporte> listadoReporte){
		return listadoReporte.stream()
				.anyMatch(elemento-> elemento.terminal.id == (terminalParam.id)); //no hago el equals pq no es Integer
	}
	
}
