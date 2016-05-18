package tpAnual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

public class BuscadorTexto {
	
	BancoAdapter bancoAdap;
	CGPAdapter cgpAdap;
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	public List<Poi> buscarSistemaCgpExterno(List<String> palabras){
//		cgpAdap.consultar(palabras);
		// Aca en realidad no consultamos sino que solo recibe??
		return null; //Provisorio para que compile
	}
	
	public List<Poi> buscarSistemaBancoExterno(String nombre, String servicio){
		return bancoAdap.consultar(nombre, servicio);
	}
	
	public List<Poi> busquedaTextoLibre(String tags, List<Poi> listaPois){
		return listaPois.stream()
				.filter(poi-> poi.cumpleCondicionBusqueda(this.separaLaBusqueda(tags)))
				.collect(Collectors.toList());
	}
	
	
	//Setters
	
		public void setBancoAdapter(BancoAdapter bancoAd){
			bancoAdap = bancoAd;
		}
		public void setCgpAdapter(CGPAdapter cgpAd){
			cgpAdap = cgpAd;
		}

	
}
