package tpAnual;

import java.time.DayOfWeek;

import java.util.*;

public class Banco extends PoiConServicio{

	public Banco(){
		servicios = new HashSet<Servicio>();
	}

	@Override
	public void agregarServicio(Servicio servicio){
		servicio.agregarHorario(new Horario(DayOfWeek.MONDAY,DayOfWeek.FRIDAY,"10:00", "15:00"));
		servicios.add(servicio);
	}
	
	
}
