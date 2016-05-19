package tpAnual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

public class BuscadorTexto {
	
	BancoAdapter bancoAdap = new BancoAdapter();
	CGPAdapter cgpAdap = new CGPAdapter();
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, List<Poi> listaPois) throws InterruptedException{
		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
		
		poisDeTodosOrigenes.addAll(buscarEnPoisLocales(palabras, listaPois));
		poisDeTodosOrigenes.addAll(this.buscarSistemaBancoExterno(palabras));
		poisDeTodosOrigenes.addAll(this.buscarSistemaCgpExterno(palabras));
		return poisDeTodosOrigenes;
	}
	
	public List<Poi> buscarSistemaCgpExterno(List<String> palabras) throws InterruptedException{
		return cgpAdap.consultar(palabras);
	}
	
	public List<Poi> buscarSistemaBancoExterno(List<String> palabras){
		return bancoAdap.consultar(palabras);
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
