package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import tpAnual.procesos.operaciones.Proceso;

public class Planificador {

	private static Planificador instance = null;
	private Lanzador lanzador = Lanzador.getInstance();
	
	public void programarProceso(Proceso proceso,LocalDate fecha,LocalTime hora, int veces){
		lanzador.agregarProceso(proceso);
	}
		
	private Planificador(){}

}