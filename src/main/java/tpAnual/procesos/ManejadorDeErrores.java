package tpAnual.procesos;

import java.util.ArrayList;
import java.util.List;

import tpAnual.acciones.emailSenderBusqueda.EmailSenderBusqueda;
import tpAnual.procesos.operaciones.Proceso;

public class ManejadorDeErrores {
	private List<ResultadoEjecucionProceso> resultados;
	private static ManejadorDeErrores instance = null;
	private boolean envioDeMailActivado;
	private int limite;
	
	
	private ManejadorDeErrores(){
		resultados = new ArrayList<ResultadoEjecucionProceso>();
		envioDeMailActivado = false;
		limite = 1;
	}
	
	public static ManejadorDeErrores getInstance(){
		if(instance==null){
			instance = new ManejadorDeErrores();
		}
		return instance;
	}
	
	/**
	 * Lo llama el Lanzador para avisar que un proceso se ejecuto correctamente.
	 */
	public void informarEjecucionCorrecta(Proceso proceso){
		proceso.reiniciarIntentos();  //Como se ejecuto OK, reinicio los intentos.
	}
	
	/**
	 * Lo llama el Lanzador para avisar que un proceso fallo al ejecutarse.
	 */
	public void informarEjecucionFallida(Proceso proceso){
		if(superoLimiteFallos(proceso)){
			manejarFallo(proceso);
		}else{
			this.reintentarEjecucion(proceso);
		}
	}
	
	private void manejarFallo(Proceso proceso) {
		if(envioDeMailActivado){
			enviarMailFallo(proceso);
		}
		proceso.reiniciarIntentos(); 
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
	
	private void reintentarEjecucion(Proceso proceso){
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