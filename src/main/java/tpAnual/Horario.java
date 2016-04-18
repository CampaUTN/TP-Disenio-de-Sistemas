package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horario  {
	private DayOfWeek diaDesde;
	private DayOfWeek diaHasta;
	private LocalTime desde;
	private LocalTime hasta;
	
	
	public boolean estaEnFranjaHoraria(DayOfWeek dia,LocalTime hora){
		
		 boolean cumpleDia = (dia.getValue() >= diaDesde.getValue()) && (dia.getValue() <= diaHasta.getValue()); //esta entre el diaDesde y el diaHasta
		 
		 boolean estaEnHorario = (hora.isAfter(this.desde) && hora.isBefore(this.hasta));  //estaDentro del horario especificado
		 return cumpleDia && estaEnHorario;
	}

	Horario(DayOfWeek inicio,DayOfWeek fin, String desde, String hasta){ //se usa si es un rango de dias
		this.diaHasta = fin;
		this.diaDesde = inicio;
		this.desde= LocalTime.parse(desde);
		this.hasta= LocalTime.parse(hasta); 
	}
	
	
	Horario(DayOfWeek inicio, String desde, String hasta){   //se usa por si es un unico dia
		this.diaDesde = inicio;
		this.diaHasta = inicio;
		this.desde= LocalTime.parse(desde);
		this.hasta= LocalTime.parse(hasta);
	}
}
