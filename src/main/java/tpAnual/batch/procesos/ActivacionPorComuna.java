package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import tpAnual.Mapa;
import tpAnual.Terminal;

public class ActivacionPorComuna extends Proceso{
	
	Set<Terminal> terminales = new HashSet<Terminal>();
	Integer comuna;
	Set<AccionTerminal> acciones = new HashSet<AccionTerminal>();
	
	public ActivacionPorComuna(Integer numeroComuna,Set<AccionTerminal> acciones){
		this.acciones = acciones;
		this.comuna = numeroComuna;
	}
	
	public void ejecutar(){
		terminales = this.seleccionarTerminales();
		acciones.forEach(accion -> accion.realizarAccion(terminales));
	}
	
	private Set<Terminal> seleccionarTerminales(){
		Set<Terminal> terminales = new HashSet<Terminal>();
		terminales = Mapa.getInstance().terminales();
		
		return terminales.stream()
				.filter(terminal -> terminal.getNumeroComuna().equals(comuna)).collect(Collectors.toSet());
	}
}
