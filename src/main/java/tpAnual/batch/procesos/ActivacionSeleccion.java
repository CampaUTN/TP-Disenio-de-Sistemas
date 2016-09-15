package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tpAnual.Terminal;

@Entity
public class ActivacionSeleccion extends Proceso {
	
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

	public Set<AccionTerminal> getAcciones() {
		return acciones;
	}

	public void setAcciones(Set<AccionTerminal> acciones) {
		this.acciones = acciones;
	}

	public Set<Terminal> getTerminalesSeleccion() {
		return terminalesSeleccion;
	}

	public void setTerminalesSeleccion(Set<Terminal> terminalesSeleccion) {
		this.terminalesSeleccion = terminalesSeleccion;
	}
	
	
}
