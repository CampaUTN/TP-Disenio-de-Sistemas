package tpAnual;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.com.EmailSender;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.externo.sistemasExternos.Consultora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class BuscadorTexto{
	private HashSet<Consultora> adapters = new HashSet<Consultora>();
	private List<RegistroBusqueda> registros = new ArrayList<RegistroBusqueda>();
	private Long limite = Long.MAX_VALUE;
	
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, List<Poi> listaPois, Terminal terminal){
		Long timerInicio = System.currentTimeMillis();

		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
				
		poisDeTodosOrigenes.addAll(buscarEnPoisLocales(palabras, listaPois));
		buscarEnPoisExternos(palabras, poisDeTodosOrigenes);   //va agregando los resultados de los adapters a la lista de pois
				
		Long timerFin = System.currentTimeMillis();
		terminal.informar(timerFin - timerInicio, limite);
		
		agregarRegistro(poisDeTodosOrigenes, palabras, timerFin - timerInicio, terminal);

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
	
	private void agregarRegistro(List<Poi> listaPois,List<String> palabras,Long tiempoEmpleado, Terminal terminal){
		registros.add(new RegistroBusqueda(listaPois,palabras,tiempoEmpleado,terminal));
	}
	
	public List<Poi> obtenerCGPsConServicioExternos(String servicio){
		
		List <String> palabras = Arrays.asList(servicio.split(" "));
		return (new CGPAdapter()).consultar(palabras);
	}
	

	//Setters
	public void setLimite(Long limite) {
		this.limite = limite;
	}
	
	//Getters
	public List<RegistroBusqueda> getRegistros(){
		return registros;
	}
	
	public void agregarAdapterExterno(Consultora adapter){
		adapters.add(adapter);
	}

	public Long getLimite() {
		return limite;
	}

	public HashSet<Consultora> getAdapters() {
		return adapters;
	}
}