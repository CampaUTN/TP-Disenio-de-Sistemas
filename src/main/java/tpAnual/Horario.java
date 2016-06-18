package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horario  {
	private DayOfWeek diaDesde;
	private DayOfWeek diaHasta;
	private LocalTime desde;
	private LocalTime hasta;
	
	//Esta entre el diaDesde y el diaHasta
	private boolean cumpleRango(DayOfWeek dia, LocalTime hora)
	{
		return (dia.getValue() >= diaDesde.getValue()) && 
				(dia.getValue() <= diaHasta.getValue());
	}
	
	//estaDentro del horario especificado
	private boolean estaEnHorario(LocalTime hora){
		return (hora.isAfter(desde) && hora.isBefore(hasta));
	}
	
	public boolean estaEnFranjaHoraria(DayOfWeek dia, LocalTime hora){
		 return cumpleRango(dia,hora) && estaEnHorario(hora);
	}
	
	private Horario(DayOfWeek inicio,DayOfWeek fin, LocalTime desde, LocalTime hasta){
		this.diaHasta = fin;
		this.diaDesde = inicio;
		this.desde= desde;
		this.hasta= hasta; 
	}
	
	//se usa si es un rango de dias
	public static Horario nuevoHorarioParaFranja(DayOfWeek inicio,DayOfWeek fin, LocalTime desde, LocalTime hasta){   
		return new Horario(inicio, fin, desde, hasta);
	}
	
	//se usa por si es un unico dia
	public static Horario nuevoHorarioParaDia(DayOfWeek inicio, LocalTime desde, LocalTime hasta){   
		return new Horario(inicio, inicio, desde, hasta);
	}
}
