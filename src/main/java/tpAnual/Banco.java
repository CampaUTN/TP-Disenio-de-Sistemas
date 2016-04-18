package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class Banco extends TipoPoi{

	private List<String> servicios;
	private Horario horarioAtencion;
	
	public Banco(){
		this.horarioAtencion = new Horario(DayOfWeek.MONDAY,DayOfWeek.FRIDAY,"10:00", "17:00");
	}
	
	// Disponibilidad
	
	public boolean estaDisponible(DayOfWeek dia,String hora) {
		return horarioAtencion.estaEnFranjaHoraria(dia, LocalTime.parse(hora));
	}
	
	public boolean estaDisponible(String servicio,DayOfWeek dia,String hora){
		return this.brinda(servicio) && this.estaDisponible(dia,hora);
	}
	
	// Servicios
	
	public boolean brinda(String servicio){
		return servicios.contains(servicio);
	}
	
	public void agregarServicio(String servicio){
		servicios.add(servicio);
	}	
	
	//El metodo de estaCerca de banco corresponde al default de 5 cuadras definido en TipoPoi
}
