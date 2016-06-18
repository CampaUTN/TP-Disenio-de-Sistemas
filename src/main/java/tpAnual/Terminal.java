package tpAnual;

import tpAnual.com.*;

public class Terminal {
	private int id;
	private String nombre;
	private EmailSender sender = new EmailSenderBusqueda();
	
	public void informar(Long tiempo, Long limite){
		if(tiempo>limite){
			sender.enviarMensajePorDemora(limite);
		}
	}
	
	public Terminal(int idParametro){
		id = idParametro;
	}
	
	public int getId(){
		return id;
	}
	
	public String getNombre(){
		return nombre;
	}

	public void setSender(EmailSender sender) {
		this.sender = sender;
	}
}
