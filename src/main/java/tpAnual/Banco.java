package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Banco extends PoiConServicio{
	
	@Override
	public void agregarServicio(Servicio servicio){
		servicio.agregarHorario(Horario.nuevoHorarioParaFranja(DayOfWeek.MONDAY,DayOfWeek.FRIDAY,LocalTime.parse("10:00"), LocalTime.parse("15:00")));
		servicios.add(servicio);
	}
}
