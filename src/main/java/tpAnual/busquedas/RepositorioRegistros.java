package tpAnual.busquedas;

import java.util.ArrayList;

import java.util.List;

import tpAnual.Terminal;
import tpAnual.POIs.Poi;
import tpAnual.reportes.ElementoReporte;
import tpAnual.reportes.RegistroBusqueda;
import tpAnual.reportes.ReportePorFecha;
import tpAnual.reportes.ReportePorTerminal;


public class RepositorioRegistros{
	private List<RegistroBusqueda> registros = new ArrayList<RegistroBusqueda>();
	private static RepositorioRegistros instance = null;
	
	private RepositorioRegistros(){}
	
	public static RepositorioRegistros getInstance(){
		if(instance==null){
			instance = new RepositorioRegistros();
		}
		return instance;
	}
	
	
	public void agregarRegistro(List<Poi> listaPois, List<String> palabras, Long tiempoEmpleado, Terminal terminal) {
		registros.add(new RegistroBusqueda(listaPois, palabras, tiempoEmpleado, terminal));
	}
	
	public List<ElementoReporte> reportarPorFecha(){
		return new ReportePorFecha().reportar(registros);
	}
	
	public List<ElementoReporte> reportarPorTerminal(){
		return new ReportePorTerminal().reportar(registros);
	}

	public List<RegistroBusqueda> getRegistros() {
		return registros;
	}
	
	public static void resetSingleton(){
	    instance = null;
	}

	public void setRegistros(List<RegistroBusqueda> registros) {
		this.registros = registros;
	}
	
}
