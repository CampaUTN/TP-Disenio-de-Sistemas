package tpAnual.batch.procesos;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import tpAnual.Terminal;

@Entity
@DiscriminatorValue("activ_mail")
public class ActivarMails extends AccionTerminal{
	
	@GeneratedValue
	private long activ_mail;
	
	public void realizarAccion(Set<Terminal> terminales){
		terminales.forEach(terminal -> terminal.activarMails());
	}
	
}
