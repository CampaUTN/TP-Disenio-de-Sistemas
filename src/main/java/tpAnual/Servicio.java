package tpAnual;

public class Servicio {
	private String nombre;
	// TODO horarios

	public Servicio(String nombre) {
		this.nombre = nombre;
	}

	public boolean es(String unNombre) {
		return nombre.equals(unNombre);
	} // para poder seleccionar el servicio pedido y luego preguntarle la hora,
		// al buscarlo en una lista de servicios.
}
