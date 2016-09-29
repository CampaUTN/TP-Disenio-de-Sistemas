package tpAnual.batch;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.batch.procesos.FinEjecucion;
import tpAnual.batch.procesos.Proceso;

public class ResultadoEjecucionProceso {
	@SuppressWarnings("unused")
	private String proceso;
	@SuppressWarnings("unused")
	private LocalDate fecha;
	@SuppressWarnings("unused")
	private LocalTime hora;
	@SuppressWarnings("unused")
	private FinEjecucion estado;
	
	
	public ResultadoEjecucionProceso(Proceso proceso){
		this.proceso = proceso.getNombre();
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		this.estado = proceso.getEstado();
	}
}
