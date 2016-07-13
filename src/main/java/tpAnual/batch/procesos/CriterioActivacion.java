package tpAnual.batch.procesos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import tpAnual.Terminal;

public class CriterioActivacion extends Proceso{
	
	Set<Terminal> terminales = new HashSet<Terminal>();
	Map<String,AccionTerminal> accionesMapa = new HashMap<String, AccionTerminal>();
		
	@SuppressWarnings("rawtypes")
	public void ejecutar(){
		
		Iterator it = accionesMapa.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			((AccionTerminal) e.getValue()).ejecutar(terminales);
		}
		
	}
}
