package tpAnual.bd;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tpAnual.BuscadorTexto;
import tpAnual.Terminal;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
 	
public class TestPersistirBusquedas {

	@BeforeClass
	public static void init() {
		BuscadorTexto buscador = new BuscadorTexto();
		Terminal terminal = new Terminal(1);
		
		buscador.buscarSegunTexto("colectivo", terminal);
	}
	
}
