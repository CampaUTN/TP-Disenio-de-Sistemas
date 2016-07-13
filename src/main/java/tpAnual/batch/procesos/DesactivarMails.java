package tpAnual.batch.procesos;

import java.util.Set;

import tpAnual.Terminal;

public class DesactivarMails implements AccionTerminal{
	
	public void ejecutar(Set<Terminal> terminales){
		terminales.forEach(terminal -> terminal.desactivarMails());
	}

}
