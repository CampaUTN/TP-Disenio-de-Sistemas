package tpAnual;

import java.time.DayOfWeek;

public class Banco extends PoiConServicio{

	@Override
	public void agregarServicio(Servicio servicio){
		servicio.agregarHorario(new Horario(DayOfWeek.MONDAY,DayOfWeek.FRIDAY,"10:00", "15:00"));
		servicios.add(servicio);
	}
	
	
}
