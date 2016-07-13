package tpAnual.batch.procesos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import tpAnual.Mapa;
import tpAnual.Terminal;


public class ProcesoActivadorAcciones extends Proceso{
	
	Map<String,Runnable> activarMapa = new HashMap<String, Runnable>();
	Map<String,Runnable> desactivarMapa = new HashMap<String, Runnable>();
	
	
	private ProcesoActivadorAcciones(Set<Terminal> terminalesFiltradas,Set<String> activar,Set<String> desactivar){	//Para que no se pueda instanciar sin criterio
//		this.terminales = terminalesFiltradas;
//		this.activar = activar;
//		this.desactivar = desactivar;
//		
//		this.activarMapa.put("Mail",()->this.activarMail());
//		this.activarMapa.put("Registro",()->this.activarRegistros());
//		this.desactivarMapa.put("Mail",()->this.desactivarMail());
//		this.desactivarMapa.put("Registro",()->this.desactivarRegistros());
	}
	
//	public static ProcesoActivadorAcciones EnComuna(Integer numeroComuna,Set<String> activar,Set<String> desactivar){
//		Set<Terminal> terminales = Mapa.getInstance().terminales();
//		
//		Stream<Terminal> filtradas = terminales.stream().filter(terminal -> terminal.getNumeroComuna().equals(numeroComuna));
//		Set<Terminal> fil = new HashSet<>();
//		fil.add(filtradas);
//		
//		return new ProcesoActivadorAcciones(terminales,activar,desactivar);
//	}
//	
//	public static ProcesoActivadorAcciones EnTodos(Set<String> activar,Set<String> desactivar){
//		Set<Terminal> terminales = Mapa.getInstance().terminales();
//		return new ProcesoActivadorAcciones(terminales,activar,desactivar);
//	}
//	
//	public static ProcesoActivadorAcciones EnSeleccion(Set<Terminal> terminales,Set<String> activar,Set<String> desactivar){
//		return new ProcesoActivadorAcciones(terminales,activar,desactivar);
//	}
	
	@Override
	public void ejecutar(){
//		criterio.ejecutar();
//		activar.forEach(palabra->this.activarMapa.get(palabra).run());
//		desactivar.forEach(palabra->this.desactivarMapa.get(palabra).run());
	}

	
//	private Runnable activarMail(){
//		this.terminales.forEach(terminal->terminal.activarMails());
//		return null;
//	}
//	private Runnable desactivarMail(){
//		this.terminales.forEach(terminal->terminal.desactivarMails());
//		return null;
//	}
//	private Runnable activarRegistros(){
//		this.terminales.forEach(terminal->terminal.activarRegistros());
//		return null;
//	}
//	private Runnable desactivarRegistros(){
//		this.terminales.forEach(terminal->terminal.desactivarRegistros());
//		return null;
//	}
}