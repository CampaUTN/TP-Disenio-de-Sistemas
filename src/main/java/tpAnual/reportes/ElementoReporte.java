package tpAnual.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tpAnual.Terminal;

public class ElementoReporte {
	
	private LocalDate fecha;
	private Integer cantidadBusquedas=0;
	private Terminal terminal;
	private Integer cantidadPoisEncontrados=0;
	private List<Integer> busquedasParciales = new ArrayList<Integer>();
	
	public boolean esDeLaFecha(LocalDate fecha){
		return fecha.toString().equals(fecha.toString());
	}
	
	public void setFecha(LocalDate fechaParam){
		fecha = fechaParam;
	}
	
	public void registrarBusqueda(){
		cantidadBusquedas++;
	}
	
	public void agregarBusquedasParciales(int cantidadEncontrada){
		busquedasParciales.add(cantidadEncontrada);
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public Integer getCantidadBusquedas() {
		return cantidadBusquedas;
	}
}
