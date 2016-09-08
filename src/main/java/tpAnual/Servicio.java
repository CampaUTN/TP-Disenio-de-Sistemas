package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import tpAnual.Horario;

@Entity
public class Servicio {
	@Id @GeneratedValue
	private long id;
	
	@ManyToMany
	private List <Horario> horarios; 
	
	
	private String nombre;

	public Servicio(String nombre) {
		this.nombre = nombre;
		this.horarios = new ArrayList<>();
	}

	public boolean equals(String nombreServ) {
		return nombre.equalsIgnoreCase(nombreServ);
	}
	
	public boolean disponible(DayOfWeek dia,LocalTime hora){
		return  horarios.stream().anyMatch(horario -> horario.estaEnFranjaHoraria(dia,hora));
	}
	
	public void agregarHorario(Horario unHorario){
		horarios.add(unHorario);
	}
	
	public String getNombre(){
		return this.nombre;
	}
}
