package tpAnual.batch.procesos;

import java.util.Set;

import tpAnual.Terminal;

public class DesactivarRegistros implements AccionTerminal{
	
	public void realizarAccion(Set<Terminal> terminales){
		terminales.forEach(terminal -> terminal.desactivarRegistros());
	}
	
}
