package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tpAnual.Mapa;
import tpAnual.Terminal;

@Entity
public class ActivacionEnTodas extends Proceso{
	
	@OneToMany
	private List<Terminal> terminales = new ArrayList<Terminal>();

	@OneToMany
	private List<AccionTerminal> acciones= new ArrayList<AccionTerminal>();
	
	
	public ActivacionEnTodas(List<AccionTerminal> acciones){
		this.acciones = acciones;
	}
	
	public void ejecutar(){
		terminales = Mapa.getInstance().getTerminales();
		acciones.forEach(accion -> accion.realizarAccion(terminales));
	}

	public List<Terminal> getTerminales() {
		return terminales;
	}

	public void setTerminales(List<Terminal> terminales) {
		this.terminales = terminales;
	}

	public List<AccionTerminal> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionTerminal> acciones) {
		this.acciones = acciones;
	}
}
