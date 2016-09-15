package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import tpAnual.Mapa;
import tpAnual.Terminal;

@Entity
@DiscriminatorValue("proc_activ_todos")
public class ActivacionEnTodas extends Proceso{
	
	@GeneratedValue
	@Column(name = "proc_activ_todos")
	private long numeroProceso;
	
	@OneToMany
	Set<Terminal> terminales = new HashSet<Terminal>();
//	@Transient
	@OneToMany
	Set<AccionTerminal> acciones= new HashSet<AccionTerminal>();
	
	public ActivacionEnTodas(Set<AccionTerminal> acciones){
		this.acciones = acciones;
	}
	
	public void ejecutar(){
		terminales = Mapa.getInstance().terminales();
		acciones.forEach(accion -> accion.realizarAccion(terminales));
	}

	public long getNumeroProceso() {
		return numeroProceso;
	}

	public void setNumeroProceso(long numeroProceso) {
		this.numeroProceso = numeroProceso;
	}

	public Set<Terminal> getTerminales() {
		return terminales;
	}

	public void setTerminales(Set<Terminal> terminales) {
		this.terminales = terminales;
	}

	public Set<AccionTerminal> getAcciones() {
		return acciones;
	}

	public void setAcciones(Set<AccionTerminal> acciones) {
		this.acciones = acciones;
	}
}
