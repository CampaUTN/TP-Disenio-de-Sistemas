package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import tpAnual.Horario;
import tpAnual.Servicio;
import tpAnual.utils.PointWrapper;

@Entity
@DiscriminatorValue("nro_banco")
public class Banco extends PoiConServicios {

	@GeneratedValue
	@Column(name = "nro_banco")
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