package tpAnual.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tpAnual.Poi;
import tpAnual.Terminal;

public class RegistroBusqueda {
	
	private List<String> palabrasUtilizadas = new ArrayList<String>();
	private Long tiempoBusqueda;
	private LocalDate fecha;
	private Integer cantidadEncontrada;
	private Terminal terminal;
	
	public RegistroBusqueda(List<Poi> pois, List<String> palabras, Long tiempoBusqueda, Terminal terminal){
		this.palabrasUtilizadas.addAll(palabras);
		this.cantidadEncontrada = pois.size();
		this.tiempoBusqueda = tiempoBusqueda;
		this.fecha = LocalDate.now();
		this.terminal = terminal;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	
}
