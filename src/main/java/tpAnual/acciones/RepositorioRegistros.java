package tpAnual.acciones;

import java.util.ArrayList;
import java.util.List;

import tpAnual.Terminal;
import tpAnual.POIs.Poi;
import tpAnual.acciones.reportes.RegistroBusqueda;
import tpAnual.acciones.reportes.ReportePorFecha;
import tpAnual.acciones.reportes.ReportePorTerminal;

public class RepositorioRegistros{
	private List<RegistroBusqueda> registros = new ArrayList<RegistroBusqueda>();
	
	private static RepositorioRegistros instance = null;
	
	private RepositorioRegistros(){
		//Para evitar que sea instanciada esta clase.
	}
	
	public static RepositorioRegistros getInstance(){
		if(instance==null){
			instance = new RepositorioRegistros();
		}
		return instance;
	}
	
	
	public void agregarRegistro(List<Poi> listaPois, List<String> palabras, Long tiempoEmpleado, Terminal terminal) {
		registros.add(new RegistroBusqueda(listaPois, palabras, tiempoEmpleado, terminal));
	}
	
	public void reportarPorFecha(){
		new ReportePorFecha().reportar(registros);
	}
	
	public void reportarPorTerminal(){
		new ReportePorTerminal().reportar(registros);
	}

	public List<RegistroBusqueda> getRegistros() {
		return registros;
	}
	
	public static void resetSingleton(){
	    instance = null;
	}
	
}
