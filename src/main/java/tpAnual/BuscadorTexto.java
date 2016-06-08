package tpAnual;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.com.EmailSender;
import tpAnual.externo.sistemasExternos.Consultora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

//import oracle.javatools.util.Chronometer;

public class BuscadorTexto{
	private HashSet<Consultora> adapters = new HashSet<Consultora>();
	private List<RegistroBusqueda> registros = new ArrayList<RegistroBusqueda>();
	private Long limite = Long.MAX_VALUE;
	private EmailSender sender;
	
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	private void informar(Long tiempo){
		if(tiempo>limite){
			sender.enviarMensajePorDemora(limite);
		}
	}
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, List<Poi> listaPois){
		Long timerInicio = System.currentTimeMillis();

		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
				
		poisDeTodosOrigenes.addAll(buscarEnPoisLocales(palabras, listaPois));
		buscarEnPoisExternos(palabras, poisDeTodosOrigenes);   //va agregando los resultados de los adapters a la lista de pois
				
		Long timerFin = System.currentTimeMillis();
		
		registros.add(new RegistroBusqueda(listaPois,palabras,timerFin-timerInicio));
		this.informar(timerFin-timerInicio);
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
	
	//Setters
	public void setLimite(Long limite) {
		this.limite = limite;
	}

	public void setSender(EmailSender sender) {
		this.sender = sender;
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

	public EmailSender getSender() {
		return sender;
	}
}