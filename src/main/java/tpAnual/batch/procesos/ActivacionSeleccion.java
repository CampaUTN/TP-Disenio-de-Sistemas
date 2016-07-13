package tpAnual.batch.procesos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tpAnual.Terminal;

public class ActivacionSeleccion extends CriterioActivacion {

	Map<String,AccionTerminal> acciones = new HashMap<String, AccionTerminal>();
	Set<Terminal> terminalesSeleccion = new HashSet<Terminal>();
	
	public ActivacionSeleccion(Set<Terminal> terminales,Map<String,AccionTerminal> acciones){
		this.acciones = acciones;
		this.terminalesSeleccion = terminales;
	}
	
	public void ejecutar(){
		super.ejecutar();
	}
	
	
}
