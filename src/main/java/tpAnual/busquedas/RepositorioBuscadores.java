package tpAnual.busquedas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.externo.sistemasExternos.Consultora;

/**
 * Repositorio con buscadores
 */
public class RepositorioBuscadores {
	private final HashSet<Consultora> consultoras = new HashSet<Consultora>();
	private static RepositorioBuscadores instance = null;
	
	private RepositorioBuscadores(){
		//Para evitar que sea instanciada esta clase.
	}
	
	public static RepositorioBuscadores getInstance(){
		if(instance==null){
			instance = new RepositorioBuscadores();
		}
		return instance;
	}
	
	public void agregarConsultora(Consultora adapter){
		consultoras.add(adapter);
	}
	
	public HashSet<Consultora> getConsultoras() {
		return consultoras;
	}
	
	public static void resetSingleton(){
	    instance = null;
	}
	
	public List<Poi> obtenerCGPsConServicioExternos(String servicio){
		List <String> palabras = Arrays.asList(servicio.split(" "));
		return (new CGPAdapter()).consultar(palabras);
	}
}