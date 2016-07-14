package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import tpAnual.Mapa;
import tpAnual.Terminal;

public class ActivacionEnTodas extends Proceso{
	
	Set<Terminal> terminales = new HashSet<Terminal>();
	Set<AccionTerminal> acciones= new HashSet<AccionTerminal>();
	
	public ActivacionEnTodas(Set<AccionTerminal> acciones){
		this.acciones = acciones;
	}
	
	public void ejecutar(){
		terminales = Mapa.getInstance().terminales();
		acciones.forEach(accion -> accion.realizarAccion(terminales));
	}
}
