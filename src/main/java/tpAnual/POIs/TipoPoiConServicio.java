package tpAnual.POIs;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import tpAnual.Servicio;

public abstract class TipoPoiConServicio extends TipoPoi{
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
	
	private boolean tieneServicio(String palabra){
		return this.getServicios().stream()
								.anyMatch(unNombreServicio-> unNombreServicio == palabra);
	}
	
	// Otros
	public void agregarServicio(Servicio servicio){
		servicios.add(servicio);
	}
}	