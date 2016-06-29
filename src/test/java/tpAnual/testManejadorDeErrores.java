package tpAnual;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.procesos.Lanzador;
import tpAnual.procesos.ManejadorDeErrores;
import tpAnual.procesos.ProcesoActivadorAcciones;

public class testManejadorDeErrores {
	private Set<Terminal> terminales;
	private Set<String> activar;
	private Set<String> desactivar;
	private int limite;
		
	private ProcesoActivadorAcciones proceso1;
	private LocalComercialAdapter proceso2;
	
	@Before
	public void init(){
		terminales = new HashSet<>();
		activar = new HashSet<>();
		desactivar = new HashSet<>();
		limite=3;
		ManejadorDeErrores.getInstance().setLimite(limite);
		terminales.add(new Terminal(0));
		activar.add("hola");
		desactivar.add("chau");
				
		proceso1 = ProcesoActivadorAcciones.EnComuna(0, activar, desactivar);
		proceso2 = new LocalComercialAdapter();
	}
	
	@After
	public void finalizar(){
		ManejadorDeErrores.resetSingleton();
		Lanzador.resetSingleton();
	}
	
	@Test
	public void testLimiteAlcanzado(){
		// Seteo todo en null para que de null pointer exception y se tenga que reejecutar
		proceso1 = ProcesoActivadorAcciones.EnComuna(9, null, null);
		Lanzador.getInstance().ejecutarProceso(proceso1);
		//se va a intentar reejecutar hasta superar el limite.
		Assert.assertEquals(limite, proceso1.getIntentos(),0);
	}
	
	@Test
	public void noCuentaReintentosSiNoSeReejecuta(){
		proceso1 = ProcesoActivadorAcciones.EnComuna(3, activar, desactivar);
		Lanzador.getInstance().ejecutarProceso(proceso1);
		limite=0;
		ManejadorDeErrores.getInstance().setLimite(limite);
		Assert.assertEquals(limite, proceso1.getIntentos(),0);
	}
	
	@Test
	public void ignoraElLimiteSiSeEjecutaCorrectamente(){
		proceso1 = ProcesoActivadorAcciones.EnComuna(3, activar, desactivar);
		Lanzador.getInstance().ejecutarProceso(proceso1);
		limite=-1;
		ManejadorDeErrores.getInstance().setLimite(limite);
		Assert.assertEquals(0, proceso1.getIntentos(),0);
	}
}
