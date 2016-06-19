package tpAnual;

import java.util.ArrayList;
import java.util.List;

import tpAnual.POIs.Poi;
import tpAnual.com.*;
import tpAnual.reportes.RegistroBusqueda;

public class Terminal {
	private int numeroTerminal;
	private String nombre;
	private EmailSender sender = EmailSenderBusqueda.getInstance();
	private List<RegistroBusqueda> registros = new ArrayList<RegistroBusqueda>();
	
	public boolean equals(Terminal terminal){
		return this.numeroTerminal==terminal.getNumeroTerminal();
	}
	
	// Mail
	public void informar(Long tiempo, Long limite){
		if(tiempo>limite){
			sender.enviarMensajePorDemora(limite);
		}
	}
	
	public void activarAvisoPorMail(){
		sender = EmailSenderBusqueda.getInstance();
	}
	
	public void desactivarAvisoPorMail(){
		sender = MockEmailSenderBusqueda.getInstance();
	}
	
	// Registros
	public void agregarRegistro(List<Poi> listaPois,List<String> palabras,Long tiempoEmpleado){
		registros.add(new RegistroBusqueda(listaPois,palabras,tiempoEmpleado,this));
	}
	
	// Constructor
	public Terminal(int numeroTerminal){
		this.numeroTerminal = numeroTerminal;
	}
	
	// Setters & Getters
	public void setSender(EmailSender sender) {
		this.sender = sender;
	}
	
	public int getNumeroTerminal(){
		return numeroTerminal;
	}
	
	public String getNombre(){
		return nombre;
	}

	public List<RegistroBusqueda> getRegistros() {
		return registros;
	}
}
