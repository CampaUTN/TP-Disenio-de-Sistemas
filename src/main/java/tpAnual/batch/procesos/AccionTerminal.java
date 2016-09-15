package tpAnual.batch.procesos;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import tpAnual.Terminal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AccionTerminal {
	
	@Id
	@GeneratedValue
	private long id;
	
	public abstract void realizarAccion(Set<Terminal> terminales);
	
}
