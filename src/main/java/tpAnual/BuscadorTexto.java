package tpAnual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class BuscadorTexto {
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}

	public List<Poi> buscameSegunTags(String listaTagsIngresados, List<Poi> listaPois) {
		List<String> palabras = separaLaBusqueda(listaTagsIngresados);
		return listaPois.stream()
				.filter(poi -> hayInterseccionOInclusion(palabras, poi))
				.collect(Collectors.toList());
	}

	private boolean hayInterseccionOInclusion(List<String> palabras, Poi poi) {
		return palabras.stream()
				.anyMatch(palabra -> poi.tieneTag(palabra));
	}
}
