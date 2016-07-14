package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import tpAnual.Terminal;

public class ActivacionSeleccion extends Proceso {
	
	Set<AccionTerminal> acciones = new HashSet<AccionTerminal>();
	Set<Terminal> terminalesSeleccion = new HashSet<Terminal>();
	
	public ActivacionSeleccion(Set<Terminal> terminales,Set<AccionTerminal> acciones){
		this.acciones = acciones;
		this.terminalesSeleccion = terminales;
	}
	
	public void ejecutar(){
		acciones.forEach(accion -> accion.realizarAccion(terminalesSeleccion));
	}
	
	
}
