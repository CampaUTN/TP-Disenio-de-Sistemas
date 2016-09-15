package tpAnual.acciones.reportes;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import tpAnual.Terminal;
import tpAnual.bd.LocalDateConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ElementoReporte {
	
	@Id @GeneratedValue @Column (name="cod_elemento")
	private long numeroElemento;
	@Convert(converter = LocalDateConverter.class)
	private LocalDate fecha;
	private Integer cantidadBusquedas=0;
	private Terminal terminal;
	private Integer cantidadPoisEncontrados=0;
	@ElementCollection @Cascade({CascadeType.ALL})
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

	public long getNumeroElemento() {
		return numeroElemento;
	}

	public void setNumeroElemento(long numeroElemento) {
		this.numeroElemento = numeroElemento;
	}

	public void setCantidadBusquedas(Integer cantidadBusquedas) {
		this.cantidadBusquedas = cantidadBusquedas;
	}

	public void setCantidadPoisEncontrados(Integer cantidadPoisEncontrados) {
		this.cantidadPoisEncontrados = cantidadPoisEncontrados;
	}

	public void setBusquedasParciales(List<Integer> busquedasParciales) {
		this.busquedasParciales = busquedasParciales;
	}
}
