package tpAnual.batch.errorCatch;

import tpAnual.batch.Lanzador;
import tpAnual.batch.procesos.Proceso;

/**
 * Rejecuta un proceso una cierta cantidad de veces hasta
 * que se ejecute correctamente o se alcance el limite configurado para dicho proceso.
 */
public class ReLanzador implements Accion{
	private int cantidadMaximaIntentos;
	private int vecesConsecutivasQueFallo;

	private IEmailSenderFallo sender;

	public void setSender(IEmailSenderFallo sender) {
		this.sender = sender;
	}

	private ReLanzador(int limite){
		this.cantidadMaximaIntentos = limite;
		this.vecesConsecutivasQueFallo = 0;
	}
	
	private static ReLanzador build(int limite, IEmailSenderFallo sender){
		ReLanzador reLanzador = new ReLanzador(limite);
		reLanzador.setSender(sender);
		return reLanzador;
	}
	
	//Factory methods
	public static ReLanzador ReLanzadorConMail(int limite){
		return build(limite, EmailSenderFallo.getInstance());
	}
	
	public static ReLanzador ReLanzadorSinMail(int limite){
		// Le seteo un sender que no hace nada, tanto para testear con mockito como para desactivar
		// el envio de mails despues de alcanzar todos los intentos.
		return build(limite, new MockEmailSenderFallo());
	}
	
	/**
	 * Lo llama el proceso para avisarle que fallo al ejecutarse
	 */
	public void accionar(Proceso proceso){
		if(superoLimiteFallos()){
			sender.accionar(proceso);
		}else{
			reintentarEjecucion(proceso);
		}
	}
	
	private void reintentarEjecucion(Proceso proceso){
		vecesConsecutivasQueFallo++;
		Lanzador.getInstance().solicitudEjecucion(proceso);
	}
	
	public boolean superoLimiteFallos(){
		return vecesConsecutivasQueFallo >= cantidadMaximaIntentos;
	}
	
	public int getVecesConsecutivasQueFallo() {
		return vecesConsecutivasQueFallo;
	}
}
