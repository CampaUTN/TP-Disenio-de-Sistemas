package tpAnual.externo.sistemasExternos;

import java.util.List;

import tpAnual.POIs.Poi;

public interface Consultora {
	public List<Poi> consultar(List<String> palabras);
}
