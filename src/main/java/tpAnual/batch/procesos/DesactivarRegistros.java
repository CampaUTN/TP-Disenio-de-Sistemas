package tpAnual.batch.procesos;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import tpAnual.Terminal;

@Entity
@DiscriminatorValue("desactiv_registro")
public class DesactivarRegistros extends AccionTerminal{
	
	@GeneratedValue
	private long desactiv_registro;
	
	public void realizarAccion(Set<Terminal> terminales){
		terminales.forEach(terminal -> terminal.desactivarRegistros());
	}
	
}
