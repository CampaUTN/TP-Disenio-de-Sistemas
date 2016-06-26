package tpAnual.procesos.operaciones;

public abstract class Proceso {
	private String nombre; // necesito que tengan nombre para mandar el mensaje de error.

	public String getNombre() {
		return nombre;
	}
}
