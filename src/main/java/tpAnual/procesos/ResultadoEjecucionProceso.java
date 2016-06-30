package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.procesos.operaciones.Proceso;

public class ResultadoEjecucionProceso {
	private String  nombreProceso;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean fallo;
	
	
	public ResultadoEjecucionProceso(String nombre, LocalDate fecha, LocalTime hora, boolean fallo){
		this.nombreProceso = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.fallo = fallo;
	}
}
