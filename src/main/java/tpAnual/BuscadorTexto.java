package tpAnual;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.Consultora;


public class BuscadorTexto{
	
	private final RepositorioBuscador repositorio = RepositorioBuscador.getInstance();
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, List<Poi> listaPois, Terminal terminal){
		
		Long timerInicio = System.currentTimeMillis();
		
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		
		HashSet<Consultora> consultoras = repositorio.getConsultoras();
		consultoras.forEach(consultora->poisDeTodosOrigenes.addAll(consultora.consultar(palabras)));
		
		Long timerFin = System.currentTimeMillis();
		
		terminal.informarBusqueda(poisDeTodosOrigenes, palabras, timerFin - timerInicio);

		return poisDeTodosOrigenes;
	}
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	public RepositorioBuscador getRepositorio(){
		return repositorio;
	}
	
}