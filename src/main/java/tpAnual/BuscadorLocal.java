package tpAnual;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.Consultora;

public class BuscadorLocal implements Consultora{
	Mapa mapa;
	
	public BuscadorLocal(Mapa mapaConstructor){
		mapa = mapaConstructor;	
	}
	
	public List<Poi> consultar(List<String> palabras){
		return mapa.pois().stream()
				.filter(poi-> poi.cumpleCondicionBusqueda(palabras))
				.collect(Collectors.toList());
	}
}
