package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import tpAnual.Terminal;
import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.Consultora;


public class BuscadorTexto{
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, Terminal terminal){
		
		RepositorioBuscador repositorio = RepositorioBuscador.getInstance();
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
		
		Long timerInicio = System.currentTimeMillis();
		
		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		
		HashSet<Consultora> consultoras = repositorio.getConsultoras();
		consultoras.forEach(consultora->poisDeTodosOrigenes.addAll(consultora.consultar(palabras)));
		
		Long timerFin = System.currentTimeMillis();
		
		terminal.informarBusqueda(poisDeTodosOrigenes, palabras, timerFin - timerInicio);
		
		this.registrarBusqueda(palabrasIngresadas, poisDeTodosOrigenes);
		
		return poisDeTodosOrigenes;
	}
	
	private void registrarBusqueda(String palabrasIngresadas, List<Poi> poisDeTodosOrigenes){
		Busqueda busqueda = new Busqueda(palabrasIngresadas,poisDeTodosOrigenes);
		// TODO: aca decirle al 'entity manager de morphia' que persista la busqueda.
	}
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
}