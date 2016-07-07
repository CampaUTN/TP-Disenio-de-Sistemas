package tpAnual.batch;

import java.util.ArrayList;
import java.util.List;

import tpAnual.batch.procesos.Proceso;

public class Lanzador{
	
	private static Lanzador instance = null;
	private List <Proceso> pendientes = new ArrayList<>();
	private boolean ejecutando = false;
	
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
	
	public void solicitudEjecucion(Proceso unProceso){
		if(!estaEjecutando()){
			
			ejecutando = true;
			ejecutarProceso(unProceso);
			ejecutarPendientes();
			
		}else{
			agregaAPendientes(unProceso);
		}		
	}	
	
	public void ejecutarProceso(Proceso unProceso){
		unProceso.realizarProceso();
		ejecutando= false;					
	}
			
	public void ejecutarPendientes(){
		if(!pendientes.isEmpty()){
			
			Proceso aEjecutar = pendientes.get(0);
			pendientes.remove(aEjecutar);
			ejecutarProceso(aEjecutar);
		}		
	}
	
	/*--------Getters-------*/	
	public boolean estaEjecutando(){
		return ejecutando;
	}
	
	public List<Proceso> getPendientes(){
		return pendientes;
	}
}