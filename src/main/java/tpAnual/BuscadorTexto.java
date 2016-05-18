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
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, List<Poi> listaPois){
		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		List<Poi> resultado = new ArrayList<Poi>();
		
		resultado.addAll(buscarEnPoisLocales(palabras, listaPois));
		resultado.addAll(this.buscarSistemaCgpExterno(palabras));
		resultado.addAll(this.buscarSistemaBancoExterno(palabras));
		return resultado;
	}
	
	public List<Poi> buscarSistemaCgpExterno(List<String> palabras){
//		cgpAdap.consultar(palabras);
		// Aca en realidad no consultamos sino que solo recibe??
		return null; //Provisorio para que compile
	}
	
	public List<Poi> buscarSistemaBancoExterno(List<String> palabras){
//		bancoAdap.consultar(palabras);
		return null; //Provisorio para que compile
	}
	
	public List<Poi> buscarEnPoisLocales(List<String> palabras, List<Poi> listaPois){
		return listaPois.stream()
				.filter(poi-> poi.cumpleCondicionBusqueda(palabras))
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
