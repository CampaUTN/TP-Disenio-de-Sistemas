package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.ManyToMany;

import org.uqbar.geodds.Point;

import tpAnual.Servicio;



public abstract class PoiConServicios extends Poi{
	public PoiConServicios(Point ubicacion, String nombre, Set<String> tags) {
		super(ubicacion, nombre, tags);
		// TODO Auto-generated constructor stub
	}

	@ManyToMany
	protected Set<Servicio> servicios = new HashSet<>();
	
	//Busqueda
	
	public boolean cumpleBusqueda(List<String> palabras){
		return palabras.stream()
					.anyMatch(palabra -> this.tieneServicio(palabra));
	}
	
	// Disponibilidad sin nombre de servicio
	public boolean estaDisponible(DayOfWeek dia,LocalTime hora) {
		return servicios.stream()
				.anyMatch(unServicio -> unServicio.disponible(dia, hora));
	}
	
	// Disponibilidad con nombre de servicio
	@Override
	public boolean estaDisponibleConServicio(String nombreServ, DayOfWeek dia,LocalTime hora) {
		return servicios.stream()
				.anyMatch(unServicio -> unServicio.tienePorNombre(nombreServ) && unServicio.disponible(dia,hora));
	}
	
	// Getters:
	@Override
	public Set<String> getServicios(){
		return servicios.stream()
						.map(servicio -> servicio.getNombre())
						.collect(Collectors.toSet());
	}
	
	
	
	private boolean tieneServicio(String nombreServicio){
		return this.getServicios().stream()
								.anyMatch(unNombreServicio-> unNombreServicio.equalsIgnoreCase(nombreServicio));
	}
	
	public void agregarServicio(Servicio servicio){
		servicios.add(servicio);
	}
	
	@Override
	public Set<String> getTags(){
		return Stream.concat(super.getTags().stream(),this.getServicios().stream())
					 .collect(Collectors.toSet());
	}
	
	//Así las subclases pueden sobreescribirlo.
	@Override
	public boolean estaCerca(Point ubicacion) {
		return super.estaCerca(ubicacion);
	}
}	