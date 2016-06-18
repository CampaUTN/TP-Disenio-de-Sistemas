package tpAnual.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tpAnual.Terminal;
import tpAnual.POIs.Poi;

public class RegistroBusqueda {
	
	private List<String> palabrasUtilizadas = new ArrayList<String>();
	private Long tiempoBusqueda;
	private LocalDate fecha;
	private Integer cantidadEncontrada;
	private Terminal terminal;
	
	public RegistroBusqueda(List<Poi> pois, List<String> palabras, Long tiempo, Terminal terminalParam){
		palabrasUtilizadas.addAll(palabras);
		cantidadEncontrada = pois.size();
		tiempoBusqueda = tiempo;
		fecha = LocalDate.now();
		terminal = terminalParam;
	}
	
	public List<String> getPalabras(){
		return palabrasUtilizadas;
	}
	
	public Long getTiempo(){
		return tiempoBusqueda;
	}
	
	public LocalDate getFecha(){
		return fecha;
	}
	public Integer getCantidad(){
		return cantidadEncontrada;
	}
	
	public Terminal getTerminal(){
		return terminal;
	}
}
