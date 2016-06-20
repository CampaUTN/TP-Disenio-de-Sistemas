package tpAnual;

import java.util.HashSet;

import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.externo.sistemasExternos.Consultora;

public class RepositorioBuscador {
	private HashSet<Consultora> consultoras = new HashSet<Consultora>();
	private static RepositorioBuscador instance = null;
	
	private RepositorioBuscador(){
		//Para evitar que sea instanciada esta clase.
		consultoras.add(new CGPAdapter());
		consultoras.add(new BancoAdapter());
		consultoras.add(new BuscadorLocal(new Mapa()));
	}
	
	public static RepositorioBuscador getInstance(){
		if(instance==null){
			instance = new RepositorioBuscador();
		}
		return instance;
	}
	
	public void agregarConsultora(Consultora adapter){
		consultoras.add(adapter);
	}

	public HashSet<Consultora> getConsultoras() {
		return consultoras;
	}
}