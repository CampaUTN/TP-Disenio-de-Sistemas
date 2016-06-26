package tpAnual.procesos;

import java.util.ArrayList;
import java.util.List;

import tpAnual.acciones.com.EmailSenderBusqueda;
import tpAnual.procesos.operaciones.Proceso;

public class ManejadorDeErrores {
	private List<ResultadoEjecucionProceso> resultados;
	private static ManejadorDeErrores instance = null;
	private boolean enviarMail;
	private int limite;
	
	
	private ManejadorDeErrores(){
		resultados = new ArrayList<ResultadoEjecucionProceso>();
		enviarMail = false;
		limite = 1;
	}
	
	public static ManejadorDeErrores getInstance(){
		if(instance==null){
			instance = new ManejadorDeErrores();
		}
		return instance;
	}
	
	/**
	 * Lo llama el Lanzador para avisar que un proceso fallo al ejecutarse.
	 */
	public boolean informarFallo(Proceso proceso){
		if(superoLimiteFallos(proceso)){
			this.enviarMailFallo(proceso);
		}else{
			this.manejarFallo(proceso);
		}
		return false;
	}
	
	private void enviarMailFallo(Proceso proceso){
		EmailSenderBusqueda.getInstance().enviarMensaje("Proceso fallido",
				"El proceso "+proceso.getNombre()
				+" falló luego de intentar ejecutarlo "
				+proceso.getIntentos()+" veces.");
	}
	
	private boolean superoLimiteFallos(Proceso proceso){
		return proceso.getIntentos()>limite;
	}
	
	private void manejarFallo(Proceso proceso){
		proceso.incrementarIntentos();
		//Lanzador.getInstance().Ejecuta(proceso); // TODO descomentar cuando este el lanzador,
		// y ponerle al metodo el nombre que corresponda.
	}

	public List<ResultadoEjecucionProceso> getResultados() {
		return resultados;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
}
