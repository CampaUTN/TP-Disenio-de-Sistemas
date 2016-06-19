package tpAnual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.externo.sistemasExternos.Consultora;


public class BuscadorTexto{
	private HashSet<Consultora> adapters = new HashSet<Consultora>();
	
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, List<Poi> listaPois, Terminal terminal){
		Long timerInicio = System.currentTimeMillis();

		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
				
		poisDeTodosOrigenes.addAll(buscarEnPoisLocales(palabras, listaPois)); // TODO armar un repositorio (singleton) con esto y pasar la busqueda local y las de los adapters como observe
		buscarEnPoisExternos(palabras, poisDeTodosOrigenes);   //va agregando los resultados de los adapters a la lista de pois
				
		Long timerFin = System.currentTimeMillis();
		
		terminal.informarBusqueda(poisDeTodosOrigenes, palabras, timerFin - timerInicio);

		return poisDeTodosOrigenes;
	}
	
	public void buscarEnPoisExternos(List<String> palabras, List<Poi> poisDeTodosOrigenes) {
		adapters.forEach(adapter -> poisDeTodosOrigenes.addAll(adapter.consultar(palabras)));
	}

	public List<Poi> buscarEnPoisLocales(List<String> palabras, List<Poi> listaPois){
		return listaPois.stream()
				.filter(poi-> poi.cumpleCondicionBusqueda(palabras))
				.collect(Collectors.toList());
	}
	
	public List<Poi> obtenerCGPsConServicioExternos(String servicio){
		
		List <String> palabras = Arrays.asList(servicio.split(" "));
		return (new CGPAdapter()).consultar(palabras);
	}
	
	//Getters
	public void agregarAdapterExterno(Consultora adapter){
		adapters.add(adapter);
	}

	public HashSet<Consultora> getAdapters() {
		return adapters;
	}
}