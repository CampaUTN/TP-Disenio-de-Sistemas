package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import tpAnual.Horario;

public class Servicio {
	private String nombre;
	private List <Horario> horarios; 

	public Servicio(String nombre) {
		this.nombre = nombre.toLowerCase();
		this.horarios = new ArrayList<>();
	}

	public boolean tienePorNombre(String nombreServ) {
		return nombre.equals(nombreServ.toLowerCase());
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
