package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tpAnual.Mapa;
import tpAnual.Terminal;

@Entity
public class ActivacionEnTodas extends Proceso{
	
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
