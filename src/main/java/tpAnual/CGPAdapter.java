package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.uqbar.geodds.Point;

public class CGPAdapter {
	
	private ExternoCPO cpoExterno = new ExternoCPO();
		
		
	// Consultar es un metodo de la interface externa que me da el JSON que esto debe adaptar
	public List<Poi> consultar(List<String> palabras){
		List<Poi> pois = new ArrayList<Poi>();
		palabras.forEach(palabra-> pois.addAll(this.adaptar(cpoExterno.consultar(palabra))));
		return pois;
	}
	
	private List<Poi> adaptar(List<CentroDTO> centros){
		List<Poi> pois = new ArrayList<Poi>();
		centros.forEach(centro -> pois.add(convertirAPoi(centro)));
		return pois;
	}
	
	
	private Poi convertirAPoi(CentroDTO centro){
		Cgp cgp = new Cgp(null); //ACA HAY QUE HACER ALGO CON LA COMUNA!!
		Point ubicacion = new Point(1,1); //ACA TMB!
		String nombre = centro.getDirector();
		Set<String> tags = centro.getZonas();
		
		centro.getServicios().forEach(servDto-> cgp.agregarServicio( convertirAServicioCgp(servDto)));
		
		Poi nuevoPoi = new Poi(cgp,ubicacion,nombre,tags);
		return nuevoPoi;
	}
	
	private Servicio convertirAServicioCgp(ServicioDTO servDto){
		LocalTime desde = LocalTime.of(servDto.getHoraD(),servDto.getMinD());		//junta la hora y el minuto en LocalTime
		LocalTime hasta = LocalTime.of(servDto.getHoraH(),servDto.getMinH());
		DayOfWeek dia = getDayOfWeek(servDto.getDia());
		
		Horario horario = new Horario(dia,dia,desde,hasta);
		
		Servicio servicioCgp = new Servicio(servDto.getNombre());
		servicioCgp.agregarHorario(horario);
		
		return servicioCgp;
	}
	
	private String convertirHorario(int hora, int min){
		return String.valueOf(hora) + ":" + String.format("%02d", min);
	}
}
