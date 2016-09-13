package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import tpAnual.Terminal;

@Entity
@DiscriminatorValue("proc_activ_seleccion")
public class ActivacionSeleccion extends Proceso {
	
	@GeneratedValue
	@Column(name = "proc_activ_seleccion")
	private long numeroProceso;
	
	@OneToMany
	Set<AccionTerminal> acciones = new HashSet<AccionTerminal>();
	@OneToMany
	Set<Terminal> terminalesSeleccion = new HashSet<Terminal>();
	
	public ActivacionSeleccion(Set<Terminal> terminales,Set<AccionTerminal> acciones){
		this.acciones = acciones;
		this.terminalesSeleccion = terminales;
	}
	
	public void ejecutar(){
		acciones.forEach(accion -> accion.realizarAccion(terminalesSeleccion));
	}
	
	
}
