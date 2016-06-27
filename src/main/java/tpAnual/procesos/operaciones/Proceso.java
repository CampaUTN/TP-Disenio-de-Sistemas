package tpAnual.procesos.operaciones;

import tpAnual.procesos.Lanzador;

public abstract class Proceso{
	private String nombre; // necesito que tengan nombre para mandar el mensaje de error.
	private int intentos=0;
	private Lanzador lanzador = Lanzador.getInstance();
	
	public void incrementarIntentos(){
		intentos++;
	}
	
	public void reiniciarIntentos(){
		intentos=0;
	}
	
	public int getIntentos(){
		return intentos;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public abstract void realizarProceso();

	public Lanzador getLanzador(){
		return lanzador;
	}
}