package tpAnual;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class CGPAdapter {
	
	private ExternoCPO cpoExterno = new ExternoCPO(); //TODO es un mock, no Object.
		
		
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
		
		centro.getServicios().forEach(servDto-> cgp.agregarServicio( convertirAServicioCgp(servDto)));
		
		Poi nuevoPoi = new Poi(cgp,ubicacion,nombre,null);
		return nuevoPoi;
	}
	
	private Servicio convertirAServicioCgp(ServicioDTO servDto){
		String desde = convertirHorario(servDto.getHoraD(),servDto.getMinD());
		String hasta = convertirHorario(servDto.getHoraH(),servDto.getMinH());
		DayOfWeek dia = getDayOfWeek(servDto.getDia());
		
		Horario horario = new Horario(dia,dia,desde,hasta);
		
		Servicio servicioCgp = new Servicio(servDto.getNombre());
		servicioCgp.agregarHorario(horario);
		
		return servicioCgp;
	}
	
//	@SuppressWarnings("null")
	private String convertirHorario(int hora, int min){
//		String horario = null;
//		horario.concat(String.valueOf(hora));
//		horario.concat(":");
//		horario.concat(String.valueOf(min));
//		return horario;
		return String.valueOf(hora)+":"+String.valueOf(min);
	}
	
	private DayOfWeek getDayOfWeek(int dia){
		switch(dia){
	        case 1:
	        	return DayOfWeek.MONDAY;
	        case 2:
	            return DayOfWeek.TUESDAY;
	        case 3:
	        	return DayOfWeek.WEDNESDAY;
	        case 4:
	        	return DayOfWeek.THURSDAY;
	        case 5:
	        	return DayOfWeek.FRIDAY;
	        case 6:
	        	return DayOfWeek.SATURDAY;
	        case 7:
	        	return DayOfWeek.SUNDAY;
		}
		return null;
	}

}
