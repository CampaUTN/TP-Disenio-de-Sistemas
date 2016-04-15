package tpAnual;

import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.*;

public class Cgp extends TipoPoi  {
	
	private List<Servicio> servicios;
	
	@Override
	public boolean estaDisponible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
