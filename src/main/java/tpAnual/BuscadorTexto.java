package tpAnual;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.Consultora;


public class BuscadorTexto{
	
	private RepositorioBuscador repositorio; 
//	private static EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	public List<Poi> buscarSegunTexto(String palabrasIngresadas, Terminal terminal){
		
		repositorio = RepositorioBuscador.getInstance();
		
		Long timerInicio = System.currentTimeMillis();
		
		List<Poi> poisDeTodosOrigenes = new ArrayList<Poi>();
		List<String> palabras = separaLaBusqueda(palabrasIngresadas);
		
		HashSet<Consultora> consultoras = repositorio.getConsultoras();
		consultoras.forEach(consultora->poisDeTodosOrigenes.addAll(consultora.consultar(palabras)));
		
		Long timerFin = System.currentTimeMillis();
		
		terminal.informarBusqueda(poisDeTodosOrigenes, palabras, timerFin - timerInicio);
		
//		Busqueda busqueda = new Busqueda(palabrasIngresadas,poisDeTodosOrigenes);
//		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
//		entityManager.persist(busqueda); 
		
		return poisDeTodosOrigenes;
	}
	
	private List<String> separaLaBusqueda(String Busqueda) {
		return Arrays.asList(Busqueda.split(" "));
	}
	
	public RepositorioBuscador getRepositorio(){
		return repositorio;
	}
	
}