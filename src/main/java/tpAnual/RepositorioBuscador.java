package tpAnual;

import java.util.HashSet;

import tpAnual.externo.adapters.BancoAdapter;
import tpAnual.externo.adapters.CGPAdapter;
import tpAnual.externo.sistemasExternos.Consultora;

public class RepositorioBuscador {
	private HashSet<Consultora> consultoras = new HashSet<Consultora>();
	
	public RepositorioBuscador(){
		consultoras.add(new CGPAdapter());
		consultoras.add(new BancoAdapter());
		consultoras.add(new BuscadorLocal(new Mapa()));
	}
	
	public void agregarConsultora(Consultora adapter){
		consultoras.add(adapter);
	}

	public HashSet<Consultora> getConsultoras() {
		return consultoras;
	}
}
