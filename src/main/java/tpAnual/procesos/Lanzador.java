package tpAnual.procesos;

import java.util.ArrayList;
import java.util.List;

import tpAnual.procesos.operaciones.Proceso;

public class Lanzador{
	
	private static Lanzador instance = null;
	private ManejadorDeErrores manejador = ManejadorDeErrores.getInstance();
	private List <Proceso> procesos = new ArrayList<>();
	private List <Proceso> pendientes = new ArrayList<>();
	private boolean ejecutando;
	
	private Lanzador(){}
	
	public static Lanzador getInstance(){
		if(instance==null){
			instance = new Lanzador();
		}
		return instance;
	}
	
	public static void resetSingleton(){
	    instance = null;
	}
	
	public void agregarProceso(Proceso unProceso){
		procesos.add(unProceso);
	}
	
	public void agregaAPendientes(Proceso unProceso){
		pendientes.add(unProceso);
	}
	
	public void ejecutarProceso(Proceso unProceso){

		try{ //flujo normal
			
			unProceso.realizarProceso(); 
			manejador.informarEjecucionCorrecta(unProceso);
			
		} catch (Exception e) { //flujo alternativo
		
			manejador.informarEjecucionFallida(unProceso);
		}
	}
			
	private void ejecutarPendientes() {
		if(!pendientes.isEmpty()){

			ejecutando = true;
			Proceso aEjecutar = pendientes.get(0);
			ejecutarProceso(aEjecutar);
		}		
	}

	public void termineDeEjecutar(){
		ejecutando = false;
	}
	
	/*--------Getters-------*/
	public boolean estaEjecutando(){
		return ejecutando;
	}
	
	public List<Proceso> getProcesos(){
		return procesos;
	}

	public List<Proceso> getPendientes(){
		return pendientes;
	}
	
	public ManejadorDeErrores getManejador(){
		return manejador;
	}
}