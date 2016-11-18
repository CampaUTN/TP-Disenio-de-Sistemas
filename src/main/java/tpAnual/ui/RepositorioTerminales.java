package tpAnual.ui;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tpAnual.Terminal;


public class RepositorioTerminales implements WithGlobalEntityManager{

	public static RepositorioTerminales instancia = new RepositorioTerminales();

	public void agregar(Terminal terminal) {
		entityManager().persist(terminal);
	}
	
	
	public Terminal buscar(long id){
		return entityManager().find(Terminal.class, id);
	}
	
	
	public List<Terminal> listar() {
		
		//TODO borrar ejemplitos de prueba
		Terminal terminalPrueba1 = new Terminal();
		terminalPrueba1.setNombre("terminal Abasto");
		
		Terminal terminalPrueba2 = new Terminal();
		terminalPrueba2.setNombre("terminal DOT");
		
		entityManager().getTransaction().begin();
		entityManager().persist(terminalPrueba1);

		entityManager().persist(terminalPrueba2);
		
		List<Terminal> terminales =  entityManager()
										.createQuery("from Terminal", Terminal.class)
										.getResultList();
		entityManager().getTransaction().rollback();
		
		return terminales;
	}
	
	public Terminal buscarPorNombre(String nombre){
		
		return entityManager().find(Terminal.class, nombre);
		
	}
}