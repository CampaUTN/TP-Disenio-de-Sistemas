package tpAnual.procesos;

import java.util.ArrayList;
import java.util.List;

import tpAnual.procesos.emailSenderFallo.EmailSenderFallo;
import tpAnual.procesos.emailSenderFallo.IEmailSenderFallo;
import tpAnual.procesos.operaciones.Proceso;

public class ManejadorDeErrores {
	private List<ResultadoEjecucionProceso> resultados;
	private static ManejadorDeErrores instance = null;
	private boolean envioDeMailActivado;
	private int limite;
	//Lo parametrizo pese a ser singleton para poder mockear.
	private IEmailSenderFallo sender = EmailSenderFallo.getInstance();
	
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
	
	public static void resetSingleton(){
	    instance = null;
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
			sender.enviarMensajePorFallo(proceso);
		}
		proceso.reiniciarIntentos(); 
	}
	
	public boolean superoLimiteFallos(Proceso proceso){
		return proceso.getIntentos()>limite;
	}
	
	private void reintentarEjecucion(Proceso proceso){
		proceso.incrementarIntentos();
		Lanzador.getInstance().ejecutarProceso(proceso);
	}

	public List<ResultadoEjecucionProceso> getResultados() {
		return resultados;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	public void setSender(IEmailSenderFallo sender){
		this.sender = sender;
	}
}
