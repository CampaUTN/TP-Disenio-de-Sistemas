package tpAnual.externo.sistemasExternos;

import java.util.List;

import tpAnual.Poi;

public interface Consultora { //el nombre es cualquiera, pero no se me ocurre otro :p
	public List<Poi> consultar(List<String> palabras);
}
