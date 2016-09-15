package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import tpAnual.Horario;
import tpAnual.util.wrapper.PointWrapper;

@Entity
public class Negocio extends Poi {
	
	@Column(name = "radio_cercania")
	private long radioCercania = 5;
	
	private String rubro;
	
	@ManyToMany
	private List <Horario> horarios;
	
	
	public Negocio(){super();}
	
	public Negocio(PointWrapper ubicacion, String nombre, Set<String> tags, String rubro, int radioCercania) {
		super(ubicacion, nombre, tags);
		this.rubro = rubro;
		this.horarios = new ArrayList<>();
		this.radioCercania = radioCercania;
	}
	
	//Busqueda	
	public boolean cumpleBusqueda(List<String> palabras){
		return palabras.stream()
				.anyMatch(palabra -> palabra.equalsIgnoreCase(rubro));
	}
	
	//Disponibilidad
	@Override
	public boolean estaDisponible(DayOfWeek dia,LocalTime hora){
		return  horarios.stream()
				.anyMatch(horario -> horario.estaEnFranjaHoraria(dia,hora));
	}

	public void agregarHorario(Horario unHorario){
		horarios.add(unHorario);
	}
	
	//Cercania
	@Override
	public boolean estaCerca(PointWrapper ubicacion) {
		return this.getUbicacion().distance(ubicacion) < radioCercania;
	}

	public String getRubro() {
		return rubro;
	}
	
	public List <Horario> getHorarios(){
		return horarios;
	}

	public long getRadioCercania() {
		return radioCercania;
	}

	public void setRadioCercania(long radioCercania) {
		this.radioCercania = radioCercania;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
}
