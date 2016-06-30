package tpAnual.procesos;

import java.time.LocalDate;
import java.time.LocalTime;
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
		proceso.reiniciarIntentos();  //Como se ejecuto OK, reinicio los intentos.
	}
	
	/**
	 * Lo llama el Lanzador para avisar que un proceso fallo al ejecutarse.
	 */
	public void informarEjecucionFallida(Proceso proceso){
		if(superoLimiteFallos(proceso)){
			manejarFallo(proceso);
			this.generarResultado(proceso, true);
		}else{
			this.reintentarEjecucion(proceso);
			generarResultado(proceso, false);
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
	
	public ResultadoEjecucionProceso generarResultado(Proceso proceso, boolean fallo){
		return new ResultadoEjecucionProceso(proceso.getNombre(), LocalDate.now(), LocalTime.now(), fallo);
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
	
	public void activarAvisoPorMail(){
		envioDeMailActivado = true;
	}
	
	public void desactivarAvisoPorMail(){
		envioDeMailActivado = false;
	}
}
