package tpAnual.externo.adapters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import tpAnual.externo.sistemasExternos.LocalComercialExternoDTO;

public class LocalComercialAdapter{
	
	private String direccion;
	
	
	public LocalComercialAdapter(String direccion){
		this.direccion = direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	
	public File getFile(){
		return FileUtils.getFile(direccion);
	}
	
	public List<String> obtenerLineas(){
		List<String> lineas = new ArrayList<String>();
		LineIterator it;
		try {
			it = FileUtils.lineIterator(this.getFile(), "UTF-8");
			while(it.hasNext())
				lineas.add(it.nextLine());
		} catch (IOException e) {
			e.printStackTrace();
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

