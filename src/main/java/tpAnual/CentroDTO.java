package tpAnual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CentroDTO {
	int numeroComuna;
	Set<String> zonas = new HashSet<String>();
	String nombreDirector;
	String domicilio;
	String telefono;
	List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
	
	
	public void setServicios(List<ServicioDTO> serv){
		servicios = serv;
	}
	
	public List<ServicioDTO> getServicios(){
		return servicios;
	}
	
	public String getDirector(){
		return nombreDirector;
	}

	public int getNumeroComuna(){
		return numeroComuna;
	}
	public Set<String> getZonas(){
		return zonas;
	}
	public String getDomicilio(){
		return domicilio;
	}
}