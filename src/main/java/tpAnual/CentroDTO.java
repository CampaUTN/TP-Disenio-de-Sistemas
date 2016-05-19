package tpAnual;

import java.util.ArrayList;
import java.util.List;

public class CentroDTO {
	int numeroComuna;
	String zonas;
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
	public String getZonas(){
		return zonas;
	}
	public String getDomicilio(){
		return domicilio;
	}
}