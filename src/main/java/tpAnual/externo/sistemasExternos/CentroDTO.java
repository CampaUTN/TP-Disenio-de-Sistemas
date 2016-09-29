package tpAnual.externo.sistemasExternos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

@Entity
public class CentroDTO {
	private int numeroComuna;

	@Embedded
	private Set<String> zonas = new HashSet<String>();
	private String nombreDirector;
	private String domicilio;
	@Embedded
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
	
	public String getNombreDirector() {
		return nombreDirector;
	}

	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}

	public void setNumeroComuna(int numeroComuna) {
		this.numeroComuna = numeroComuna;
	}

	public void setZonas(Set<String> zonas) {
		this.zonas = zonas;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
}