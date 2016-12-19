package tpAnual.ui;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import tpAnual.Terminal;

public class RepositorioTerminales implements WithGlobalEntityManager, TransactionalOps{

	private static RepositorioTerminales instancia = null;

	public void agregar(Terminal terminal) {
		entityManager().persist(terminal);
	}
	
	public void baja(Terminal terminal){
		entityManager().remove(entityManager().contains(terminal) ? terminal : entityManager().merge(terminal));
		//entityManager().remove(terminal);
	}
	
	public Terminal buscarPorId(long id){
		return entityManager().find(Terminal.class, id);
	}
	
	public void modificar(Terminal nuevo){
		entityManager().merge(nuevo);
	}
	
	private RepositorioTerminales(){}
	
	public static RepositorioTerminales getInstance(){
		if(instancia==null){
			instancia = new RepositorioTerminales();
		}
		return instancia;
	}
	
	public static void resetSingleton(){
		RepositorioTerminales.getInstance().eliminarTerminales();
		instancia = null;
	}
	
	public List<Terminal> listar() {
		
		//TODO borrar ejemplitos de prueba
//		Terminal terminalPrueba1 = new Terminal();
//		terminalPrueba1.setNombre("terminal Abasto");
//		terminalPrueba1.setNumeroComuna(1);
//		
//		Terminal terminalPrueba2 = new Terminal();
//		terminalPrueba2.setNombre("terminal DOT");
//		terminalPrueba2.setNumeroComuna(2);

		//Reseter.resetSingletons(); lo comento xq sino al ir a esta pantalla, se pierde lo hecho en otras.
//		entityManager().getTransaction().begin();
//		entityManager().persist(terminalPrueba1);
//		entityManager().persist(terminalPrueba2);
		
		List<Terminal> terminales =  entityManager().createQuery("from Terminal where numeroTerminal<>null", Terminal.class).getResultList();
//		entityManager().getTransaction().rollback();

//		Reseter.resetSingletons();
		return terminales;
	}
	
	public List<Terminal> getTerminalesPorComuna(int comuna){
		return this.listar().stream().filter(t->t.getNumeroComuna().equals(comuna)).collect(Collectors.toList());
	}
	
	public Terminal buscarPorNombre(String nombre){
		
		Terminal terminalPrueba1 = new Terminal();
		terminalPrueba1.setNombre("abasto");
		
		Terminal terminalPrueba2 = new Terminal();
		terminalPrueba2.setNombre("dot");

		//Reseter.resetSingletons(); lo comento xq sino al ir a esta pantalla, se pierde lo hecho en otras.
		entityManager().getTransaction().begin();
		entityManager().persist(terminalPrueba1);

		entityManager().persist(terminalPrueba2);
		
		Terminal resultado =  entityManager().createQuery("FROM Terminal where nombre_terminal= :nombre ", Terminal.class).
				setParameter("nombre", nombre).getResultList().get(0);
		entityManager().getTransaction().rollback();

				
		//Reseter.resetSingletons(); lo comento xq sino al ir a esta pantalla, se pierde lo hecho en otras.
		return resultado;
		
	}
	
	private void eliminarTerminales(){
		withTransaction(() ->{
			List<Terminal> terminalesAEliminar = new ArrayList<Terminal>();
			terminalesAEliminar.addAll(RepositorioTerminales.getInstance().listar());
			terminalesAEliminar.forEach(terminal -> RepositorioTerminales.getInstance().baja(terminal)); //Doy de baja los POIs de la BD
		});
	}
}