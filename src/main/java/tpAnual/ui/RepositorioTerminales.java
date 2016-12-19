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
		actualizar(terminal);
	}
	
	public void baja(Terminal terminal){
		entityManager().remove(entityManager().contains(terminal) ? terminal : entityManager().merge(terminal));
		//entityManager().remove(terminal);
	}
	
	public void actualizar(Terminal terminal){
		if(entityManager().getTransaction().isActive()){
			entityManager().flush();			
		}else{
			entityManager().getTransaction().begin();
			entityManager().flush();
			entityManager().getTransaction().commit();
		}
		entityManager().merge(terminal);
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
			instancia.agregarTerminalesPrueba();
		}
		return instancia;
	}
	
	public static void resetSingleton(){
		RepositorioTerminales.getInstance().eliminarTerminales();
		instancia = null;
	}
	
	public List<Terminal> listar() {
		
		return entityManager().
				createQuery("FROM Terminal WHERE numeroTerminal <> null", Terminal.class).
				getResultList();
}
	
	//TODO BORRAR!!
	public void agregarTerminalesPrueba(){	
		Terminal terminalPrueba1 = new Terminal();
		terminalPrueba1.setNombre("abasto");
		
		Terminal terminalPrueba2 = new Terminal();
		terminalPrueba2.setNombre("dot");
		
		RepositorioTerminales.getInstance().agregar(terminalPrueba1);
		RepositorioTerminales.getInstance().agregar(terminalPrueba2);	
	}
	
	public List<Terminal> getTerminalesPorComuna(int comuna){
		return this.listar().stream().filter(t->t.getNumeroComuna().equals(comuna)).collect(Collectors.toList());
	}
	
	public Terminal buscarPorNombre(String nombre){		
		
		return entityManager().createQuery("FROM Terminal WHERE nombre_terminal= :nombre ", Terminal.class).
				setParameter("nombre", nombre).getResultList().get(0);
	}
	
	private void eliminarTerminales(){
		withTransaction(() ->{
			List<Terminal> terminalesAEliminar = new ArrayList<Terminal>();
			terminalesAEliminar.addAll(RepositorioTerminales.getInstance().listar());
			terminalesAEliminar.forEach(terminal -> RepositorioTerminales.getInstance().baja(terminal)); //Doy de baja los POIs de la BD
		});
	}
}