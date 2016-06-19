package tpAnual.acciones.reportes;

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
		return this.fecha.toString().equals(fecha.toString());
	}
	
	public boolean esDeLaTerminal(Terminal terminal){
		return this.terminal.equals(terminal);
	}
	
	// Factory methods
	public static ElementoReporte crearConFecha(LocalDate fecha){
		ElementoReporte elemento = new ElementoReporte();
		elemento.setFecha(fecha);
		return elemento;
	}
	
	
	public static ElementoReporte crearConTerminal(Terminal terminal){
		ElementoReporte elemento = new ElementoReporte();
		elemento.setTerminal(terminal);
		return elemento;
	}
	
	/*--------Setters----------*/
	
	public void setFecha(LocalDate fecha){
		this.fecha = fecha;
	}
	
	public void setTerminal(Terminal terminal){
		this.terminal = terminal;
	}
	public void registrarBusqueda(){
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

	public LocalDate getFecha() {
		return fecha;
	}

	public Integer getCantidadPoisEncontrados() {
		return cantidadPoisEncontrados;
	}
}
