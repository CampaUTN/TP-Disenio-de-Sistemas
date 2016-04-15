package tpAnual;

import java.util.*;
import org.uqbar.geodds.Point;

public class Poi {

	private Point ubicacion;
	@SuppressWarnings("unused")
	private String nombre;
	private TipoPoi tipo;
	private Set<String> tags = new HashSet<String>();

	public Poi(TipoPoi tipo, Point ubicacion, String nombre, Set<String> tags) {
		this.tipo = tipo;
		this.ubicacion=ubicacion;
		this.nombre = nombre;
		this.tags = tags;
		tags.add(nombre);
	}

	// Disponibilidad:
	public boolean estaDisponible() {
		return tipo.estaDisponible();
	}

	public boolean estaDisponible(String servicio) {
		return tipo.estaDisponible(servicio);
	}

	// Distancia:
	public boolean estaCerca(Point unPunto) {
		return tipo.estaCerca(unPunto, this.ubicacion);
	}

	// Tags:
	public boolean tieneTag(String clave) {
		return tags.stream()
				.anyMatch(tag -> tag.toLowerCase().contains(clave.toLowerCase()));
	}

	public void agregarTag(String nuevoTag) {
		tags.add(nuevoTag);
	}
}
