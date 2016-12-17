package tpAnual.busquedas;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.Consultora;
import tpAnual.ui.ParametroBusqueda;

public class BuscadorLocal implements Consultora, WithGlobalEntityManager{
	
	public List<Poi> consultar(List<String> palabras){
		return Mapa.getInstance().getPois().stream()
				.filter(poi-> poi.cumpleCondicionBusqueda(palabras))
				.collect(Collectors.toList());
	}
	
	public List<Poi> buscar(ParametroBusqueda parametros){
		List<Poi> resultados = parametros.armarQuery().getResultList();
		
	  if(parametros.getTags().size() != 0){			
			resultados = resultados.
				stream().
				filter(poi -> poi.cumpleCondicionBusqueda(parametros.getTags())).
				collect(Collectors.toList());
		}
	  return resultados;
	}
}
