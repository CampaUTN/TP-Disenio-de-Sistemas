package tpAnual;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroBusqueda {
	
	List<String> palabrasUtilizadas = new ArrayList<String>();
	Integer tiempoBusqueda;
	LocalDate fecha;
	Integer cantidadEncontrada;
	
	public RegistroBusqueda(List<Poi> pois, List<String> palabras, Integer tiempo){
		palabrasUtilizadas.addAll(palabras);
		cantidadEncontrada = pois.size();
		tiempoBusqueda = tiempo;
		fecha = LocalDate.now();
	}
	
}
