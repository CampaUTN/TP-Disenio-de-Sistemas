package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import org.uqbar.geodds.Point;

import tpAnual.Horario;
import tpAnual.Servicio;

public class Banco extends PoiConServicios {

	public Banco(Point ubicacion, String nombre, Set<String> tags) {
		super(ubicacion, nombre, tags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void agregarServicio(Servicio servicio) {
		servicio.agregarHorario(Horario.nuevoHorarioParaFranja(DayOfWeek.MONDAY, DayOfWeek.FRIDAY,
				LocalTime.parse("10:00"), LocalTime.parse("15:00")));
		servicios.add(servicio);
	}
}
