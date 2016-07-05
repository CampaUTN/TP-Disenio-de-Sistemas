package tpAnual.batch;

import tpAnual.batch.observers.EmailSenderFallo;
import tpAnual.batch.observers.IEmailSenderFallo;
import tpAnual.batch.observers.LoggerProcesos;
import tpAnual.batch.procesos.Proceso;

public class ManejadorDeErrores {
	private static ManejadorDeErrores instance = null;
	private boolean envioDeMailActivado;
	private int limite;
	//Lo parametrizo pese a ser singleton para poder mockear.
	private IEmailSenderFallo sender = EmailSenderFallo.getInstance();
	
	private ManejadorDeErrores(){
		envioDeMailActivado = true;
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
		//LoggerProcesos.getInstance().registrarEjecucionExitosa(proceso);
		proceso.reiniciarIntentos();  //Como se ejecuto OK, reinicio los intentos.
	}
	
	/**
	 * Lo llama el Lanzador para avisar que un proceso fallo al ejecutarse.
	 */
	public void informarEjecucionFallida(Proceso proceso){
		LoggerProcesos.getInstance().registrarEjecucionFallida(proceso);
		if(superoLimiteFallos(proceso)){
			manejarFallo(proceso);
		}else{
			reintentarEjecucion(proceso);
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
		Lanzador.getInstance().solicitudEjecucion(proceso);
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	public void setSender(IEmailSenderFallo sender){
		this.sender = sender;
	}
	
	public void activarAvisoPorMail(){
		envioDeMailActivado = true;
	}
	
	public void desactivarAvisoPorMail(){
		envioDeMailActivado = false;
	}
}
