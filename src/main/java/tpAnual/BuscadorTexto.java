package tpAnual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class BuscadorTexto {

	// METODOS

	public List<String> SeparaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}

	public List<Poi> BuscameSegunTags(String listaTagsIngresados, List<Poi> listaPois) {
		List<String> palabras = SeparaLaBusqueda(listaTagsIngresados);
		return listaPois.stream()
				.filter(poi -> hayInterseccionOInclusion(palabras, poi))
				.collect(Collectors.toList());
	}

	public boolean hayInterseccionOInclusion(List<String> palabras, Poi poi) {
		return palabras.stream()
				.anyMatch(palabra -> poi.tieneTag(palabra));
	}
}
