package tpAnual.procesos;

import java.util.ArrayList;
import java.util.List;

import tpAnual.procesos.operaciones.Proceso;

public class Lanzador{
	
	private static Lanzador instance = null;
	private ManejadorDeErrores manejador = ManejadorDeErrores.getInstance();
	private List <Proceso> pendientes = new ArrayList<>();
	private IPlanificador planificador;
	
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
		
	public void agregaAPendientes(Proceso unProceso){
		pendientes.add(unProceso);
	}
	
	public void ejecutarProceso(Proceso unProceso){

		try{ //flujo normal
			
			unProceso.realizarProceso(); 
			manejador.informarEjecucionCorrecta(unProceso);
			ejecutarPendientes();
			
		} catch (Exception e) { //flujo alternativo
		
			manejador.informarEjecucionFallida(unProceso);
		}
	}
			
	private void ejecutarPendientes() {
		if(!pendientes.isEmpty()){
			
			Proceso aEjecutar = pendientes.get(0);
			pendientes.remove(aEjecutar);
			ejecutarProceso(aEjecutar);
			
		}		
	}
	
	/*--------Getters-------*/	
	public List<Proceso> getPendientes(){
		return pendientes;
	}
	
	public ManejadorDeErrores getManejador(){
		return manejador;
	}	
	
}