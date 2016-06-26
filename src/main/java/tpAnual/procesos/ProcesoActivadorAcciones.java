package tpAnual.procesos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tpAnual.Mapa;
import tpAnual.Terminal;
import tpAnual.procesos.operaciones.Proceso;


public class ProcesoActivadorAcciones extends Proceso{
	
	Set<Terminal> terminales = new HashSet<>();
	Set<String> activar = new HashSet<>();
	Set<String> desactivar = new HashSet<>();
	
	HashMap<String,Runnable> activarMapa = new HashMap<String, Runnable>();
	HashMap<String,Runnable> desactivarMapa = new HashMap<String, Runnable>();
	
	private ProcesoActivadorAcciones(Set<Terminal> terminalesFiltradas,Set<String> activar,Set<String> desactivar){	//Para que no se pueda instanciar sin criterio
		this.terminales = terminalesFiltradas;
		this.activar.addAll(activar);
		this.desactivar.addAll(desactivar);
		
		this.activarMapa.put("Mail",()->this.activarMail());
		this.activarMapa.put("Registro",()->this.activarRegistros());
		this.desactivarMapa.put("Registro",()->this.desactivarMail());
		this.desactivarMapa.put("Registro",()->this.desactivarRegistros());

	}
	
	public static ProcesoActivadorAcciones EnComuna(int numeroComuna,Set<String> activar,Set<String> desactivar){
		Set<Terminal> terminales = Mapa.getInstance().terminales();
		Set<Terminal> terminalesFiltradas = new HashSet<>();
		
		terminales.forEach(terminal ->{
			if(terminal.getNumeroComuna()==numeroComuna){
				terminalesFiltradas.add(terminal);
			}
		});
		return new ProcesoActivadorAcciones(terminalesFiltradas,activar,desactivar);
	}
	
	public static ProcesoActivadorAcciones EnTodos(Set<String> activar,Set<String> desactivar){
		Set<Terminal> terminales = Mapa.getInstance().terminales();
		return new ProcesoActivadorAcciones(terminales,activar,desactivar);
	}
	
	public static ProcesoActivadorAcciones EnSeleccion(Set<Terminal> terminales,Set<String> activar,Set<String> desactivar){
		return new ProcesoActivadorAcciones(terminales,activar,desactivar);
	}
	
	
	public void realizarProceso(){
		activar.forEach(palabra->this.activarMapa.get(palabra).run());
		desactivar.forEach(palabra->this.desactivarMapa.get(palabra).run());
	}
	
	//Acciones
	
	private Runnable activarMail(){
		this.terminales.forEach(terminal->terminal.activarMails());
		return null;
	}
	private Runnable desactivarMail(){
		this.terminales.forEach(terminal->terminal.desactivarMails());
		return null;
	}
	private Runnable activarRegistros(){
		this.terminales.forEach(terminal->terminal.activarRegistros());
		return null;
	}
	private Runnable desactivarRegistros(){
		this.terminales.forEach(terminal->terminal.desactivarRegistros());
		return null;
	}
}