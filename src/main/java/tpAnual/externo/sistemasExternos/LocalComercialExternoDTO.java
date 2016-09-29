package tpAnual.externo.sistemasExternos;

import java.util.HashSet;
import java.util.Set;

public class LocalComercialExternoDTO {
	
	private String nombre;
	private Set<String> palabrasClave = new HashSet<String>();
	
	
	public LocalComercialExternoDTO(String nombre,Set<String> palabrasClave){
		this.nombre = nombre;
		this.palabrasClave = palabrasClave;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public Set<String> getPalabrasClave(){
		return palabrasClave;
	}
}
