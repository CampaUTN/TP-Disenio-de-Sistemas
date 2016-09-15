package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tpAnual.Mapa;
import tpAnual.Terminal;

@Entity
public class ActivacionPorComuna extends Proceso{
	
	@OneToMany
	Set<Terminal> terminales = new HashSet<Terminal>();
	
	Integer comuna;
	
	@OneToMany
	Set<AccionTerminal> acciones = new HashSet<AccionTerminal>();
 	
 	public ActivacionPorComuna(Integer numeroComuna,Set<AccionTerminal> acciones){
 		this.acciones = acciones;
 		this.comuna = numeroComuna;
 	}
 	
 	public void ejecutar(){
 		terminales = this.seleccionarTerminales();
 		acciones.forEach(accion -> accion.realizarAccion(terminales));
 	}
 	
 	private Set<Terminal> seleccionarTerminales(){
 		Set<Terminal> terminales = new HashSet<Terminal>();
 		terminales = Mapa.getInstance().terminales();
 		
 		return terminales.stream()
 				.filter(terminal -> terminal.getNumeroComuna().equals(comuna)).collect(Collectors.toSet());
 	}

	public Set<Terminal> getTerminales() {
		return terminales;
	}

	public void setTerminales(Set<Terminal> terminales) {
		this.terminales = terminales;
	}

	public Integer getComuna() {
		return comuna;
	}

	public void setComuna(Integer comuna) {
		this.comuna = comuna;
	}

	public Set<AccionTerminal> getAcciones() {
		return acciones;
	}

	public void setAcciones(Set<AccionTerminal> acciones) {
		this.acciones = acciones;
	}
 }