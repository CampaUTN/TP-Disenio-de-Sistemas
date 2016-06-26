package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.procesos.operaciones.Proceso;

public class ResultadoEjecucionProceso {
	private String  nombreProceso;
	private LocalDate fecha;
	private LocalTime hora;
	private String resultado;
	
	
	public ResultadoEjecucionProceso(Proceso proceso, LocalDate fecha, LocalTime hora, String resultado){
		this.nombreProceso = proceso.getNombre();
		this.fecha = fecha;
		this.hora = hora;
		this.resultado = resultado;
	}
}
