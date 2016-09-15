package tpAnual.batch.procesos;

import java.util.Set;
import javax.persistence.Entity;

import tpAnual.Terminal;

@Entity
public class ActivarMails extends AccionTerminal{
	
	public void realizarAccion(Set<Terminal> terminales){
		terminales.forEach(terminal -> terminal.activarMails());
	}
	
}
