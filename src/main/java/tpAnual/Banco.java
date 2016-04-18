package tpAnual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import org.uqbar.geodds.Point;

public class Banco extends TipoPoi{

	private List<String> servicios;
	private Horario horarioAtencion;
	
	@Override
	public boolean brinda(String servicio)
	{
		return servicios.contains(servicio);
	}
	
	@Override
	public boolean estaDisponible(DayOfWeek dia,String hora) {
		
		return estaEnFranjaHoraria(dia, LocalTime.parse(hora));
	}
	
	@Override
	public boolean estaDisponible(String servicio,DayOfWeek dia,String hora)
	{
		return this.brinda(servicio) && this.estaDisponible(dia,hora);
	}

	public void agregarServicio(String servicio){
		servicios.add(servicio);
	}	
	
	
	public Boolean estaEnFranjaHoraria(DayOfWeek dia,LocalTime hora){
		return horarioAtencion.estaEnFranjaHoraria(dia, hora);
	}
			
	public Banco()
	{
		this.horarioAtencion =new Horario(DayOfWeek.MONDAY,DayOfWeek.FRIDAY,"10:00", "17:00");
	}
	
	//El metodo de estaCerca de banco corresponde al default de 5 cuadras definido en TipoPoi
}
