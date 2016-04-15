package tpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscadorTexto {
		
		//VARIABLES
		public LevenshteinDistance Buscador = new LevenshteinDistance();
		
		//METODOS
		
		public String[] SeparaLaBusqueda(String Busqueda){
			String[] palabras;
			palabras = Busqueda.split(" ");
			return palabras;
		}
		
		
		public List<Poi> BuscameSegunTags (String listaTagsIngresados, List<Poi> listaPois){
			String[] palabras = SeparaLaBusqueda(listaTagsIngresados);
			List<Poi> poisAceptados = listaPois.stream()
					   .filter(poi->hayInterseccionOInclusion(palabras,poi))
					   .collect(Collectors.toList());
			
			return poisAceptados;
			
		}

		public Boolean hayInterseccionOInclusion(String[] palabras, Poi poi) {
			for (String palabra : palabras){
				if(poi.tieneTag(palabra)){
					return true;
				}
			}
			return false;
		}
}
