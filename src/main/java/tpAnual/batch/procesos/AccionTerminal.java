package tpAnual.batch.procesos;

import java.util.Set;

import javax.persistence.Entity;

import tpAnual.Terminal;

@Entity
public abstract class AccionTerminal {
	
	public abstract void realizarAccion(Set<Terminal> terminales);
	
}
