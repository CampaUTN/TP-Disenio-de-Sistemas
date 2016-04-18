package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.uqbar.geodds.*;


public class Negocio extends TipoPoi {

	private int radio;
	private String nombre;
	private List <Horario> horarios;

	
	public boolean estaDisponible(DayOfWeek dia,String hora){
		
		LocalTime horaComp = LocalTime.parse(hora);
		return  horarios.stream().anyMatch(horario -> horario.estaEnFranjaHoraria(dia,horaComp));

	}

	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {

		Double distancia = puntoPoi.distance(unPunto);
		return distancia < radio;
	}
	
	public void agregarHorario(Horario unHorario){
		horarios.add(unHorario);
	}
	
}
