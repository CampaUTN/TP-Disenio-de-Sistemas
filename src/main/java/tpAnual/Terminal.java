package tpAnual;

import tpAnual.com.*;

public class Terminal {
	private int numeroTerminal;
	private String nombre;
	private EmailSender sender = EmailSenderBusqueda.getInstance();
	
	public void informar(Long tiempo, Long limite){
		if(tiempo>limite){
			sender.enviarMensajePorDemora(limite);
		}
	}
	
	public Terminal(int numeroTerminal){
		this.numeroTerminal = numeroTerminal;
	}
	
	public void setSender(EmailSender sender) {
		this.sender = sender;
	}
	
	public int getNumeroTerminal(){
		return numeroTerminal;
	}
	
	public String getNombre(){
		return nombre;
	}
}
