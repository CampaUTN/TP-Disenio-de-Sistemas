package tpAnual;

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
	
	/*--------Setters----------*/
	
	public void setFecha(LocalDate fechaParam){
		fecha = fechaParam;
	}
	
	public void setTerminal(Terminal unaTerminal){
		terminal = unaTerminal;
	}
	public void sumarBusqueda(){
		cantidadBusquedas++;
	}
	
	public void agregarBusquedasParciales(int cantidadEncontrada){
		busquedasParciales.add(cantidadEncontrada);
	}
	
	
	/*--------Getters----------*/
	
	public LocalDate getfecha(){
		return fecha;
	}
	
	public Integer getCantidadBusquedas(){
		return cantidadBusquedas;
	}
	
	public Terminal getTerminal(){
		return terminal;
	}
	
	public Integer getCantidadPois(){
		return cantidadPoisEncontrados;
	}
	
	public List<Integer> getBusquedasParciales(){
		return busquedasParciales;
	}
}
