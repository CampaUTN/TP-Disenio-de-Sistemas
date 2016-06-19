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
	private Integer cantidadPois;
	private Terminal terminal;
	
	public RegistroBusqueda(List<Poi> pois, List<String> palabrasUtilizadas, Long tiempoBusqueda, Terminal terminal){
		this.palabrasUtilizadas.addAll(palabrasUtilizadas);
		this.cantidadPois = pois.size();
		this.tiempoBusqueda = tiempoBusqueda;
		this.fecha = LocalDate.now();
		this.terminal = terminal;
	}
	
	public List<String> getPalabrasUtilizadas(){
		return palabrasUtilizadas;
	}
	
	public Long getTiempoBusqueda(){
		return tiempoBusqueda;
	}
	
	public LocalDate getFecha(){
		return fecha;
	}
	public Integer getCantidadPois(){
		return cantidadPois;
	}
	
	public Terminal getTerminal(){
		return terminal;
	}
}
