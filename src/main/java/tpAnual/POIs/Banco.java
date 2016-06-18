package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;

import tpAnual.Horario;
import tpAnual.Servicio;

public class Banco extends PoiConServicio{
	
	@Override
	public void agregarServicio(Servicio servicio){
		servicio.agregarHorario(Horario.nuevoHorarioParaFranja(DayOfWeek.MONDAY,DayOfWeek.FRIDAY,LocalTime.parse("10:00"), LocalTime.parse("15:00")));
		servicios.add(servicio);
	}
}
