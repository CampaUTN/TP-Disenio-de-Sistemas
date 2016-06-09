package tpAnual.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tpAnual.Terminal;

public class ElementoReporte {
	
	LocalDate fecha;
	Integer cantidadBusquedas=0;
	Terminal terminal;
	Integer cantidadPoisEncontrados=0;
	List<Integer> busquedasParciales = new ArrayList<Integer>();
	
	public void setFecha(LocalDate fechaParam){
		fecha = fechaParam;
	}
	
	public void sumarBusqueda(){
		cantidadBusquedas++;
	}
	
	public void agregarBusquedasParciales(int cantidadEncontrada){
		busquedasParciales.add(cantidadEncontrada);
	}
}
