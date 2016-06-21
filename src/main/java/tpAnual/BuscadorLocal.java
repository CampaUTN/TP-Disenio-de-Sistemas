package tpAnual;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.Consultora;

public class BuscadorLocal implements Consultora{
	
	public List<Poi> consultar(List<String> palabras){
		return Mapa.getInstance().pois().stream()
				.filter(poi-> poi.cumpleCondicionBusqueda(palabras))
				.collect(Collectors.toList());
	}
}
