package tpAnual;

import java.util.List;
import java.util.stream.Collectors;

import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.Consultora;

public class BuscadorLocal implements Consultora{
	private Mapa mapa;
	
	public BuscadorLocal(Mapa mapa){
		this.mapa = mapa;	
	}
	
	public List<Poi> consultar(List<String> palabras){
		return mapa.pois().stream()
				.filter(poi-> poi.cumpleCondicionBusqueda(palabras))
				.collect(Collectors.toList());
	}
}
