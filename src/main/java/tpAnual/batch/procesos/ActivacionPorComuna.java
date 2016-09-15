package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import tpAnual.Mapa;
import tpAnual.Terminal;

@Entity
@DiscriminatorValue("proc_activ_comuna")
public class ActivacionPorComuna extends Proceso{
	
	@GeneratedValue
	@Column(name = "proc_activ_comuna")
	private long numeroProceso;
	
	@OneToMany
	Set<Terminal> terminales = new HashSet<Terminal>();
	Integer comuna;
//	@Transient
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