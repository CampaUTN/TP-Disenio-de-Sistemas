package tpAnual.batch.procesos;

import java.util.HashMap;
import java.util.Map;

import tpAnual.Mapa;

public class ActivacionEnTodas extends CriterioActivacion{
	
	Map<String,AccionTerminal> acciones = new HashMap<String, AccionTerminal>();
	
	public ActivacionEnTodas(Map<String,AccionTerminal> acciones){
		this.acciones = acciones;
	}
	
	public void ejecutar(){
		terminales = Mapa.getInstance().terminales();
		super.ejecutar();
	}
}
