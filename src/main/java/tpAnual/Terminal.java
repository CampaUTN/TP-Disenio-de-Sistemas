package tpAnual;

import java.util.List;

import tpAnual.POIs.Poi;
import tpAnual.acciones.RepositorioRegistros;
import tpAnual.acciones.com.EmailSender;
import tpAnual.acciones.com.EmailSenderBusqueda;

public class Terminal {
	private int numeroTerminal;
	private String nombre;
	private EmailSender sender = EmailSenderBusqueda.getInstance(); //Esta parametrizado en vez de usar siempre el singleton para que se pueda testear con mockito
	private boolean tieneRegistrosActivados = true;
	private boolean tieneMailsActivados = true;

	public boolean equals(Terminal terminal) {
		return this.numeroTerminal == terminal.getNumeroTerminal();
	}

	public void informarBusqueda(List<Poi> pois, List<String> palabras, Long duracionBusqueda) {
		notificarRegistro(pois, palabras, duracionBusqueda);
		enviarMailDemora(duracionBusqueda);
	}

	private void enviarMailDemora(Long duracionBusqueda) {
		if (this.tieneMailsActivados()) {
			sender.enviarMensajePorDemora(duracionBusqueda);
		}
	}

	private void notificarRegistro(List<Poi> pois, List<String> palabras, Long duracionBusqueda) {
		if (this.tieneRegistrosActivados()) {
			RepositorioRegistros.getInstance().agregarRegistro(pois, palabras, duracionBusqueda, this);
		}
	}

	// Constructor
	public Terminal(int numeroTerminal) {
		this.numeroTerminal = numeroTerminal;
	}

	// Getters
	public int getNumeroTerminal() {
		return numeroTerminal;
	}

	public String getNombre() {
		return nombre;
	}
	
	// Setters
	public void setSender(EmailSender sender){
		this.sender = sender;
	}
	
	// Activacion y desactivación dinamica:
	// Estos metodos si o si tienen que estar. Si no es con booleanos, hay que usar strategy con stubs,
	// Asi que se termina agregando complejidad sin mejorar la funcionalidad.
	
	// Reportes
	private boolean tieneRegistrosActivados() {
		return tieneRegistrosActivados;
	}

	public void activarRegistros() {
		tieneRegistrosActivados = true;
	}

	public void desactivarRegistros() {
		tieneRegistrosActivados = false;
	}

	// Mails
	private boolean tieneMailsActivados() {
		return tieneMailsActivados;
	}
	
	public void activarMails() {
		tieneMailsActivados = true;
	}

	public void desactivarMails() {
		tieneMailsActivados = false;
	}
}
