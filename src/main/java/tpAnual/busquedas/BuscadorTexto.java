package tpAnual.busquedas;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;

import tpAnual.Terminal;
import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.Consultora;
import tpAnual.util.bd.PoiDTO;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;


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
		
		List <PoiDTO> pois = poisDeTodosOrigenes.stream()
														.map( poi -> PoiDTO.nuevoDesdePoi(poi))
														.collect(Collectors.toList());
		
		Busqueda busqueda = new Busqueda(palabrasIngresadas,pois);
		
		Datastore datastore;
		try {
			datastore = MongoDatastoreSingleton.getDatastore("busquedas");

			datastore.save(busqueda);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		// TODO: aca decirle al 'entity manager de morphia' que persista la busqueda.
	}
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
}