package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.*;

public class Cgp extends TipoPoi {

private Polygon comuna;
	
	public Cgp(Point puntoA, Point puntoB, Point puntoC){ //Pedimos 3 puntos al constructor para generar al menos un triangulo como una comuna
		List<Point> puntosComu = new ArrayList<Point>();
		puntosComu.add(puntoA);
		puntosComu.add(puntoB);
		puntosComu.add(puntoC);
		
		comuna = new Polygon(puntosComu); 
	}
	
	private List<Servicio> servicios = new ArrayList<>();

	@Override
	public boolean estaDisponible(DayOfWeek dia,String hora) {
		LocalTime horaComp = LocalTime.parse(hora);
		
		return servicios.stream().anyMatch(unServicio -> unServicio.estaDentroDelHorario(dia, horaComp));
	}//este es si NO se ingresa el nombre del servicio
	
	
	public boolean estaDisponible(String servicio, DayOfWeek dia,String hora) {
		
		LocalTime horaComp = LocalTime.parse(hora);
		
		return servicios.stream().anyMatch(unServicio -> unServicio.servicioDisponible(servicio,dia,horaComp));
	}//Este es si se ingresa un servicio (no se si no hace falta pasarle la hora o si con la hora actual alcanza
	
		
	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return comuna.isInside(puntoPoi) && comuna.isInside(unPunto);
	}
	
	public void agregarServicio(Servicio servicio){
		servicios.add(servicio);
	}
}
