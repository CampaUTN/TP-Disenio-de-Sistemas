package tpAnual.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import tpAnual.Terminal;
import tpAnual.POIs.Poi;
import tpAnual.util.bd.mysql.LocalDateConverter;

@Entity
public class RegistroBusqueda {
	@Id @GeneratedValue
	@Column (name="cod_registro")
	private long numeroRegistroBusqueda;
	@ElementCollection @Cascade({CascadeType.ALL})
	private List<String> palabrasUtilizadas = new ArrayList<String>();
	@Column (name="tiempo_busqueda")
	private Long tiempoBusqueda;
	@Convert(converter = LocalDateConverter.class)
	private LocalDate fecha;
	private Integer cantidadPois;
	@OneToOne
	private Terminal terminal;
	
	@SuppressWarnings("unused")
	private RegistroBusqueda(){}
	
	public RegistroBusqueda(List<Poi> pois, List<String> palabrasUtilizadas, Long tiempoBusqueda, Terminal terminal){
		this.palabrasUtilizadas.addAll(palabrasUtilizadas);
		this.cantidadPois = pois.size();
		this.tiempoBusqueda = tiempoBusqueda;
		this.fecha = LocalDate.now();
		this.terminal = terminal;
	}
	
	public List<String> getPalabrasUtilizadas(){
		return palabrasUtilizadas;
	}
	
	public Long getTiempoBusqueda(){
		return tiempoBusqueda;
	}
	
	public LocalDate getFecha(){
		return fecha;
	}
	public Integer getCantidadPois(){
		return cantidadPois;
	}
	
	public Terminal getTerminal(){
		return terminal;
	}

	public long getNumeroRegistroBusqueda() {
		return numeroRegistroBusqueda;
	}

	public void setNumeroRegistroBusqueda(long numeroRegistroBusqueda) {
		this.numeroRegistroBusqueda = numeroRegistroBusqueda;
	}

	public void setPalabrasUtilizadas(List<String> palabrasUtilizadas) {
		this.palabrasUtilizadas = palabrasUtilizadas;
	}

	public void setTiempoBusqueda(Long tiempoBusqueda) {
		this.tiempoBusqueda = tiempoBusqueda;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setCantidadPois(Integer cantidadPois) {
		this.cantidadPois = cantidadPois;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
}
