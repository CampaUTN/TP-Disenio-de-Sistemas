package tpAnual;

import java.util.ArrayList;
import java.util.List;

public class ReportePorTerminalDetallado implements Reporte{
	
	Reporte reporteConocido;
	
	public ReportePorTerminalDetallado(){
		reporteConocido = new ReporteCantidadTotalPorTerminal();
	}
	
	@Override
	public void reportar(List<RegistroBusqueda> registros) {
		this.reportarDetallado(registros);
		reporteConocido.reportar(registros);
	}
	
	private void reportarDetallado(List<RegistroBusqueda> registros){
		List<ElementoReporte> listadoReporte = new ArrayList<ElementoReporte>();
		
		registros.forEach(registro->{
			agregarSiNoEstaTerminal(registro,listadoReporte);
			buscarElementoYAgregarBusquedas(registro,listadoReporte);
		});
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
