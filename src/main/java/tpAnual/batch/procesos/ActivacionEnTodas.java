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
	@OneToMany
	Set<AccionTerminal> acciones= new HashSet<AccionTerminal>();
	
	public ActivacionEnTodas(Set<AccionTerminal> acciones){
		this.acciones = acciones;
	}
	
	public void ejecutar(){
		terminales = Mapa.getInstance().terminales();
		acciones.forEach(accion -> accion.realizarAccion(terminales));
	}
}
