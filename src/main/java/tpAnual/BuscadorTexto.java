package tpAnual;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.externo.sistemasExternos.Consultora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

//import oracle.javatools.util.Chronometer;

public class BuscadorTexto{

	HashSet<Consultora> adapters = new HashSet<Consultora>();
	
	List<RegistroBusqueda> registros = new ArrayList<RegistroBusqueda>();

	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, List<Poi> listaPois){
		Integer timerInicio = (int) System.currentTimeMillis();

		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
				
		poisDeTodosOrigenes.addAll(buscarEnPoisLocales(palabras, listaPois));
		buscarEnPoisExternos(palabras, poisDeTodosOrigenes);   //va agregando los resultados de los adapters a la lista de pois
				
		Integer timerFin = (int) System.currentTimeMillis();
		
		registros.add(new RegistroBusqueda(listaPois,palabras,timerFin-timerInicio));
		
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
	
	//Getters
	public List<RegistroBusqueda> getRegistros(){
		return registros;
	}
	
	public void agregarAdapterExterno(Consultora adapter){
		adapters.add(adapter);
	}
}