package tpAnual.externo.adapters;

import tpAnual.externo.mocks.MockLocalComercial;
import tpAnual.externo.sistemasExternos.LocalComercialExternoDTO;
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

public class LocalComercialAdapter{
	
	private MockLocalComercial localComercial = new MockLocalComercial();
	
	
	public List<String> obtenerLineas(){
		File file = localComercial.getFile();
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
	
	public LocalComercialExternoDTO adaptar(String unaLinea){
		List<String> compuestos = Arrays.asList(unaLinea.split(";"));
		String nombre = compuestos.get(0);
		Set<String> palabrasClave = new HashSet<String>(Arrays.asList(compuestos.get(1).split(" ")));
		return new LocalComercialExternoDTO(nombre,palabrasClave);
	}
	
	public List<LocalComercialExternoDTO> consultar(){
		List<LocalComercialExternoDTO> lineasAdaptadas = new ArrayList<>();
		this.obtenerLineas().forEach(linea->lineasAdaptadas.add((this.adaptar(linea))));
		return lineasAdaptadas;
	}
	
	
}

