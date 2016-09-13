package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.Horario;

@Entity
@Table(name = "Servicios")
public class Servicio {
	@Id @GeneratedValue

	@Column(name = "serv_id")
	private long id;
	
	@ManyToMany
	//@Transient
	private List <Horario> horarios; 
	
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
		EntityManager em= PerThreadEntityManagers.getEntityManager(); 
	}
	
	public String getNombre(){
		return this.nombre;
	}
}