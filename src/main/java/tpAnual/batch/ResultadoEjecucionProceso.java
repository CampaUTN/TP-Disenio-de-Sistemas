package tpAnual.batch;

import java.time.LocalDate;
import java.time.LocalTime;

public class ResultadoEjecucionProceso {
	private String  nombreProceso;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean seEjecutoCorrectamente;
	
	
	public ResultadoEjecucionProceso(String nombre, LocalDate fecha, LocalTime hora, boolean seEjecutoCorrectamente){
		this.nombreProceso = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.seEjecutoCorrectamente = seEjecutoCorrectamente;
	}
}
