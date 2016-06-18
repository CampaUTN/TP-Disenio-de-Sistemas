package tpAnual.externo.sistemasExternos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CentroDTO {
	private int numeroComuna;
	private Set<String> zonas = new HashSet<String>();
	private String nombreDirector;
	private String domicilio;
	private List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
	
	
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