package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import tpAnual.Horario;
import tpAnual.Servicio;
import tpAnual.util.wrapper.PointWrapper;

@Entity
@DiscriminatorValue("Banco")
public class Banco extends PoiConServicios {
	
	public Banco(){ 
		super();
	}
	
	public Banco(PointWrapper ubicacion, String nombre, Set<String> tags) {
		super(ubicacion, nombre, tags);
	}

	@Override
	public void agregarServicio(Servicio servicio) {
		servicio.agregarHorario(Horario.nuevoHorarioParaFranja(DayOfWeek.MONDAY, DayOfWeek.FRIDAY,
				LocalTime.parse("10:00"), LocalTime.parse("15:00")));
		servicios.add(servicio);
	}

}