package tpAnual;

import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.*;

@SuppressWarnings("unused")

public class Cgp extends TipoPoi {

private Polygon comuna;
	
	public Cgp(Point puntoA, Point puntoB, Point puntoC){ //Pedimos 3 puntos al constructor para generar al menos un triangulo como una comuna
		List<Point> puntosComu = new ArrayList<Point>();
		puntosComu.add(puntoA);
		puntosComu.add(puntoB);
		puntosComu.add(puntoC);
		
		comuna = new Polygon(puntosComu); 
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
	
	public void agregarServicio(Servicio servicio){
		servicios.add(servicio);
	}
}
