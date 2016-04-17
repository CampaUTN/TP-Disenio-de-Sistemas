package tpAnual;

import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.*;

@SuppressWarnings("unused")

public class Cgp extends TipoPoi {

	private Polygon comuna = new Polygon(); //Se puede definir aca, o en Poi. Si fuese en Poi habria que hacer una sobrecarga de metodos, que permita enviar la comuna a TipoPoi para saber si esta cerca, pero generar en todos los tipoPoi un excepcion por si se manda mal
	
	public Cgp(Point puntoA, Point puntoB, Point puntoC){ //Pedimos 3 puntos al constructor para generar al menos un triangulo como una comuna
		comuna.add(puntoA);
		comuna.add(puntoB);
		comuna.add(puntoC);
	}

	private List<Servicio> servicios;

	@Override
	public boolean estaDisponible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		return comuna.isInside(puntoPoi) && comuna.isInside(unPunto);
	}

}
