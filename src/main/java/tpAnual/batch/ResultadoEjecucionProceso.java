package tpAnual.batch;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.batch.procesos.FinEjecucion;
import tpAnual.batch.procesos.Proceso;

public class ResultadoEjecucionProceso {
	private String proceso;
	private LocalDate fecha;
	private LocalTime hora;
	private FinEjecucion estado;
	
	public ResultadoEjecucionProceso(Proceso proceso){
		this.proceso = proceso.getNombre();
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		this.estado = proceso.getEstado();
	}
}
