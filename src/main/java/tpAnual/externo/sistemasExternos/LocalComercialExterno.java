package tpAnual.externo.sistemasExternos;

import java.util.Set;
import java.util.HashSet;

public class LocalComercialExterno {
	
	private String nombre;
	private Set<String> palabrasClave = new HashSet<String>();
	
	public LocalComercialExterno(String nombre,Set<String> palabrasClave){
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
