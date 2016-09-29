package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import tpAnual.batch.postEjecucionProceso.LoggerProcesos;
import tpAnual.batch.postEjecucionProceso.PostEjecucionProceso;

@Entity
@Table(name="Proceso")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Proceso{
	
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	
//	@OneToMany // TODO QUE ONDA CON ESTO?
	@Transient
	private List<PostEjecucionProceso> accionesPostFallo;
	
	@Transient
	private FinEjecucion estado;
	
	
	protected Proceso(){
		accionesPostFallo = new ArrayList<PostEjecucionProceso>();
		estado = FinEjecucion.CORRECTO;
	}
	
	
	/**
	 * Metodo que entienden todos los Procesos, que el lanzador llama para ejecutarlos.
	 */
	public void realizarProceso(){
		try{
			this.ejecutar();
			estado = FinEjecucion.CORRECTO;
		}catch(Exception e){
			estado = FinEjecucion.FALLIDO;
			accionesPostFallo.forEach(obs->obs.accionar(this));
		}
		LoggerProcesos.getInstance().accionar(this);
	}
	/* El logger se ejecuta siempre, aunque al tener la misma interface que las demas acciones,
	podria agregarlo a la lista de observers si en un futuro quisieran solo registrarse fallos,
	tambien sería facil hacer un logger modificado que registre ciertos datos extras en caso de fallo. */
	
	
	/**
	 * El metodo mediante el cual cada proceso hace lo que deba hacer
	 */
	public abstract void ejecutar();
	
	/**
	 * Mediante la GUI se agregarian observadores como el envio de mail si falla, que se reejecute
	 * si falla, o cualquier otra accion que en un futuro se defina.
	 */
	public void agregarAccionPostFallo(PostEjecucionProceso accion){
		accionesPostFallo.add(accion);
	}
	
	// Setters & getters
	public String getNombre() {
		return nombre;
	}

	public FinEjecucion getEstado() {
		return estado;
	}
	
	public long getId(){
		return this.id;
	}


	public List<PostEjecucionProceso> getAccionesPostFallo() {
		return accionesPostFallo;
	}


	public void setAccionesPostFallo(List<PostEjecucionProceso> accionesPostFallo) {
		this.accionesPostFallo = accionesPostFallo;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setEstado(FinEjecucion estado) {
		this.estado = estado;
	}
}