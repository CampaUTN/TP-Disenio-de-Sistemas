package tpAnual.batch.procesos;

import java.util.List;

import javax.persistence.Entity;

import tpAnual.Terminal;

@Entity
public class ActivarMails extends AccionTerminal{
	
	public void realizarAccion(List<Terminal> terminales){
		terminales.forEach(terminal -> terminal.activarMails());
	}
	
}
