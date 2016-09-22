package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tpAnual.Terminal;

@Entity
public class ActivacionSeleccion extends Proceso {
	
	@OneToMany
	List<AccionTerminal> acciones = new ArrayList<AccionTerminal>();
	@OneToMany
	List<Terminal> terminalesSeleccion = new ArrayList<Terminal>();
	
	public ActivacionSeleccion(List<Terminal> terminales,List<AccionTerminal> acciones){
		this.acciones = acciones;
		this.terminalesSeleccion = terminales;
	}
	
	public void ejecutar(){
		acciones.forEach(accion -> accion.realizarAccion(terminalesSeleccion));
	}

	public List<AccionTerminal> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionTerminal> acciones) {
		this.acciones = acciones;
	}

	public List<Terminal> getTerminalesSeleccion() {
		return terminalesSeleccion;
	}

	public void setTerminalesSeleccion(List<Terminal> terminalesSeleccion) {
		this.terminalesSeleccion = terminalesSeleccion;
	}
	
	
}
