package tpAnual;

import java.util.List;
import org.uqbar.geodds.Point;

public class Banco extends TipoPoi{

	private List<String> servicios;
	
	@Override
	public boolean brinda(String servicio)
	{
		return servicios.contains(servicio);
	}
	
	@Override
	public boolean estaDisponible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean estaDisponible(String servicio)
	{
		return this.brinda(servicio) && this.estaDisponible();
	}

	@Override
	public boolean estaCerca(Point unPunto, Point puntoPoi) {
		// TODO Auto-generated method stub
		return false;
	}

}
