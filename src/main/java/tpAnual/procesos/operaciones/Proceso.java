package tpAnual.procesos.operaciones;

public abstract class Proceso {
	private String nombre; // necesito que tengan nombre para mandar el mensaje de error.
	private int intentos=0;
	
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
}
