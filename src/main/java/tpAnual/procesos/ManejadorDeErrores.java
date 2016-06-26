package tpAnual.procesos;

import java.util.ArrayList;
import java.util.List;

import tpAnual.acciones.com.EmailSenderBusqueda;
import tpAnual.procesos.operaciones.Proceso;

public class ManejadorDeErrores {
	private List<ResultadoEjecucionProceso> resultados;
	private static ManejadorDeErrores instance = null;
	private boolean enviarMail = false;
	
	
	private ManejadorDeErrores(){
		resultados = new ArrayList<ResultadoEjecucionProceso>();
	}
	
	public static ManejadorDeErrores getInstance(){
		if(instance==null){
			instance = new ManejadorDeErrores();
		}
		return instance;
	}
	
	private void enviarMailFallo(Proceso proceso, int intentos){
		EmailSenderBusqueda.getInstance().enviarMensaje("Proceso fallido",
				"El proceso "+proceso.getNombre()
				+" falló luego de intentar ejecutarlo "
				+intentos+" veces.");
	}
	
	public boolean informarFallo(Proceso proceso){
		if(enviarMail){
			this.enviarMailFallo(proceso, 2);// TODO sacar el 2.
		}
		return false;
	}

	public List<ResultadoEjecucionProceso> getResultados() {
		return resultados;
	}
}
