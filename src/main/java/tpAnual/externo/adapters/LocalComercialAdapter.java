package tpAnual.externo.adapters;

import tpAnual.externo.mocks.MockLocalComercial;
import tpAnual.externo.sistemasExternos.LocalComercialExterno;
import tpAnual.POIs.Poi;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class LocalComercialAdapter {
	
	private MockLocalComercial localComercial = new MockLocalComercial();
	
	public void actualizarLocalesComerciales(List<Poi> pois){
		List<String> lineas = this.obtenerLineas(localComercial.getFile());
		lineas.forEach(linea->cambiarLocalComercial(pois,adaptar(linea)));
	}
	
	public List<String> obtenerLineas(File file){
		List<String> lineas = new ArrayList<String>();
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
		    while ((line = reader.readLine()) != null) {
		       lineas.add(line);
		    }
		}
		catch(IOException e){
			throw new UnsupportedOperationException(e);
		}
		return lineas;
	}
	
	public LocalComercialExterno adaptar(String unaLinea){
		List<String> compuestos = Arrays.asList(unaLinea.split(";"));
		String nombre = compuestos.get(0);
		Set<String> palabrasClave = new HashSet<String>(Arrays.asList(compuestos.get(1).split(" ")));
		return new LocalComercialExterno(nombre,palabrasClave);
	}
	
	public void cambiarLocalComercial(List<Poi> pois,LocalComercialExterno actualizado){
		Poi poiAModificar = pois.stream()
			.filter(poi->poi.getNombre().equals(actualizado.getNombre()))
			.collect(Collectors.toList())
			.get(0);
		if(poiAModificar != null)
			poiAModificar.cambiarTags(actualizado.getPalabrasClave());
	}
}

