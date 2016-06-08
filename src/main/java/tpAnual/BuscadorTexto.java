package tpAnual;

import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;

import java.util.ArrayList;
import java.util.Arrays;

//import oracle.javatools.util.Chronometer;

public class BuscadorTexto {
	
	BancoAdapter bancoAdapter = new BancoAdapter();
	CGPAdapter cgpAdater = new CGPAdapter();
	List<RegistroBusqueda> registros = new ArrayList<RegistroBusqueda>();

	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, List<Poi> listaPois){
		Integer timerInicio = (int) System.currentTimeMillis();

		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
		
		poisDeTodosOrigenes.addAll(buscarEnPoisLocales(palabras, listaPois));
		poisDeTodosOrigenes.addAll(this.buscarSistemaBancoExterno(palabras));
		poisDeTodosOrigenes.addAll(this.buscarSistemaCgpExterno(palabras));
		
		Integer timerFin = (int) System.currentTimeMillis();
		
		registros.add(new RegistroBusqueda(listaPois,palabras,timerFin-timerInicio));
		
		return poisDeTodosOrigenes;
	}
	
	public List<Poi> buscarSistemaCgpExterno(List<String> palabras){
		return cgpAdater.consultar(palabras);
	}
	
	public List<Poi> buscarSistemaBancoExterno(List<String> palabras){
		return bancoAdapter.consultar(palabras);
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
	
	//Setters
	
	public void setBancoAdapter(BancoAdapter adapter){
		bancoAdapter = adapter;
	}
	public void setCgpAdapter(CGPAdapter adapter){
		cgpAdater = adapter;
	}
	
}
