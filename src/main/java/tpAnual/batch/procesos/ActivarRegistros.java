package tpAnual.batch.procesos;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import tpAnual.Terminal;

@Entity
@DiscriminatorValue("activ_registro")
public class ActivarRegistros extends AccionTerminal{
	
	@GeneratedValue
	private long activ_registro;
	
	public void realizarAccion(Set<Terminal> terminales){
		terminales.forEach(terminal -> terminal.activarRegistros());
	}
	
}
