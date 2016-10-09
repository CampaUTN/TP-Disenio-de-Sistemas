package tpAnual.busquedas;

import java.util.ArrayList;
import java.util.List;

import tpAnual.POIs.Poi;

public class BusquedaParaMemoria {
	
	@SuppressWarnings("unused")
	private List<Poi> poisEncontrados = new ArrayList<>();
	@SuppressWarnings("unused")
	private String palabrasIngresadas;
	
	public BusquedaParaMemoria(List<Poi> pois, String palabras){
		this.poisEncontrados = pois;
		this.palabrasIngresadas = palabras;
	}
}
