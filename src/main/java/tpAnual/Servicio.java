package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Servicios")
public class Servicio {
	@Id @GeneratedValue

	@Column(name = "serv_id")
	private long id;	
	
	@ManyToMany
	private List <Horario> horarios = new ArrayList<Horario>(); 
	
	@Column(name = "serv_nombre")
	private String nombre;

	
	public Servicio(){}
	
	public Servicio(String nombre) {
		this.nombre = nombre;
		this.horarios = new ArrayList<>();
	}

	public boolean tienePorNombre(String nombreServ) {
		return nombre.equalsIgnoreCase(nombreServ);
	}
	
	public boolean disponible(DayOfWeek dia,LocalTime hora){
		return  horarios.stream().anyMatch(horario -> horario.estaEnFranjaHoraria(dia,hora));
	}
	
	public void agregarHorario(Horario unHorario){
		horarios.add(unHorario);
	}
	
	public String getNombre() {
		return this.nombre;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}