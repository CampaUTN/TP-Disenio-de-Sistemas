package tpAnual.batch.procesos;

import java.util.ArrayList;
import java.util.List;

import tpAnual.batch.observers.Accion;
import tpAnual.batch.observers.LoggerProcesos;

public abstract class Proceso{
	private String nombre; // necesito que tengan nombre para mandar el mensaje de error.
	private List<Accion> observadores;
	private FinEjecucion estado;
	
	
	protected Proceso(){
		observadores = new ArrayList<Accion>();
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
			observadores.forEach(obs->obs.accionar(this));
		}
		LoggerProcesos.getInstance().accionar(this);
	}
	// El logger se ejecuta siempre, aunque al tener la misma interface que las demas acciones,
	// podria agregarlo a la lista de observers si en un futuro quisieran solo registrarse fallos,
	// o sería facil hacer un logger modificado que registre ciertos datos extras en caso de fallo.
	
	
	/**
	 * El metodo mediante el cual cada proceso hace lo que deba hacer
	 */
	public abstract void ejecutar();
	
	/**
	 * Mediante la GUI se agregarian observadores como el envio de mail si falla, que se reejecute
	 * si falla, o cualquier otra accion que en un futuro se defina.
	 */
	public void agregarObservador(Accion observador){
		observadores.add(observador);
	}
	
	// Setters & getters
	public String getNombre() {
		return nombre;
	}

	public FinEjecucion getEstado() {
		return estado;
	}
}