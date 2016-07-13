package tpAnual.batch.procesos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import tpAnual.Mapa;
import tpAnual.Terminal;

public class ActivacionPorComuna extends CriterioActivacion{
	
	Integer comuna;
	Map<String,AccionTerminal> acciones = new HashMap<String, AccionTerminal>();
	
	public ActivacionPorComuna(Integer numeroComuna,Map<String,AccionTerminal> acciones){
		this.acciones = acciones;
		this.comuna = numeroComuna;
	}
	
	public void ejecutar(){
		terminales = this.seleccionarTerminales();
		super.ejecutar();
	}
	
	private Set<Terminal> seleccionarTerminales(){
		Set<Terminal> terminales = new HashSet<Terminal>();
		terminales = Mapa.getInstance().terminales();
		
		return terminales.stream()
				.filter(terminal -> terminal.getNumeroComuna().equals(comuna)).collect(Collectors.toSet());
		
	}
}
