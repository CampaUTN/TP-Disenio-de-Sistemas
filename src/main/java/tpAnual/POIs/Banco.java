package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;

import tpAnual.Horario;
import tpAnual.Servicio;
import tpAnual.util.wrapper.PointWrapper;

@Entity
public class Banco extends PoiConServicios {

	private long numero;
	
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

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}
}