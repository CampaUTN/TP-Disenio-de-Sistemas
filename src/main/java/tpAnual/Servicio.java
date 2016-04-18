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
		this.nombre = nombre;
		this.horarios = new ArrayList<>();
	}

	public boolean es(String unNombre) {
		return nombre.equals(unNombre);
	} // para poder seleccionar el servicio pedido y luego preguntarle la hora,
		// al buscarlo en una lista de servicios.
	
	
	public boolean servicioDisponible(String nombre, DayOfWeek dia,LocalTime hora){
		boolean a = es(nombre);
		boolean b= estaDentroDelHorario(dia, hora);
		
		return a && b;
	}
	
	public boolean estaDentroDelHorario(DayOfWeek dia,LocalTime hora){
		return  horarios.stream().anyMatch(horario -> horario.estaEnFranjaHoraria(dia,hora));
	}
	
	public void agregarHorario(Horario unHorario){
		horarios.add(unHorario);
	}
}
