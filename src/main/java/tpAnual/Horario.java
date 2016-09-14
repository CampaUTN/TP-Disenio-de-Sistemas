package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tpAnual.bd.LocalTimeToTimeConverter;

@Entity
@Table(name = "Horarios")
public class Horario  {
	@Id @GeneratedValue
	@Column(name = "hora_id")
	private long id;
	
	private DayOfWeek diaDesde;
	private DayOfWeek diaHasta;
	
	
	@Convert(converter = LocalTimeToTimeConverter.class)
	@Column(name = "hora_desde")
	private LocalTime horaDesde;
	
	@Convert(converter = LocalTimeToTimeConverter.class)
	@Column(name = "hora_hasta")
	private LocalTime horaHasta;

	//Es necesario el constructor vacio
	private Horario(){}
	
	//Esta entre el diaDesde y el diaHasta
	private boolean cumpleRango(DayOfWeek dia, LocalTime hora)
	{
		return (dia.getValue() >= diaDesde.getValue()) && 
				(dia.getValue() <= diaHasta.getValue());
	}
	
	//estaDentro del horario especificado
	private boolean estaEnHorario(LocalTime hora){
		return (hora.isAfter(horaDesde) && hora.isBefore(horaHasta));
	}
	
	public boolean estaEnFranjaHoraria(DayOfWeek dia, LocalTime hora){
		 return cumpleRango(dia,hora) && estaEnHorario(hora);
	}
	
	private Horario(DayOfWeek inicio,DayOfWeek fin, LocalTime desde, LocalTime hasta){
		this.diaDesde = inicio;
		this.diaHasta = fin;
		this.horaDesde= desde;
		this.horaHasta= hasta; 
	}
	
	//se usa si es un rango de dias
	public static Horario nuevoHorarioParaFranja(DayOfWeek inicio,DayOfWeek fin, LocalTime desde, LocalTime hasta){   
		return new Horario(inicio, fin, desde, hasta);
	}
	
	//se usa por si es un unico dia
	public static Horario nuevoHorarioParaDia(DayOfWeek inicio, LocalTime desde, LocalTime hasta){   
		return new Horario(inicio, inicio, desde, hasta);
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
}
