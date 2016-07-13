package tpAnual.batch.procesos;

import java.util.Set;

import tpAnual.Terminal;

public interface AccionTerminal {
	
	public void ejecutar(Set<Terminal> terminales);
	
}
