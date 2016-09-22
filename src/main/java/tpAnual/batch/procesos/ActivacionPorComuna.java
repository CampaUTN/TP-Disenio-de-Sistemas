package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tpAnual.Mapa;
import tpAnual.Terminal;

@Entity
public class ActivacionPorComuna extends Proceso{
	
	@OneToMany
	private List<Terminal> terminales = new ArrayList<Terminal>();
	
	private Integer comuna;
	
	@OneToMany
	List<AccionTerminal> acciones = new ArrayList<AccionTerminal>();
 	
 	public ActivacionPorComuna(Integer numeroComuna,List<AccionTerminal> acciones){
 		this.acciones = acciones;
 		this.comuna = numeroComuna;
 	}
 	
 	public void ejecutar(){
 		terminales = this.seleccionarTerminales();
 		acciones.forEach(accion -> accion.realizarAccion(terminales));
 	}
 	
 	private List<Terminal> seleccionarTerminales(){
 		List<Terminal> terminales = new ArrayList<Terminal>();
 		terminales = Mapa.getInstance().getTerminales();
 		
 		return terminales.stream()
 				.filter(terminal -> terminal.getNumeroComuna().equals(comuna)).collect(Collectors.toList());
 	}

	public List<Terminal> getTerminales() {
		return terminales;
	}

	public void setTerminales(List<Terminal> terminales) {
		this.terminales = terminales;
	}

	public Integer getComuna() {
		return comuna;
	}

	public void setComuna(Integer comuna) {
		this.comuna = comuna;
	}

	public List<AccionTerminal> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionTerminal> acciones) {
		this.acciones = acciones;
	}
 }