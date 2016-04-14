package tpAnual;


public class Colectivo extends TipoPoi {
	
	@Override
	public boolean estaDisponible(){
		return true;
	}

	@Override
	public boolean estaCerca(int altitud, int latitud, int altitudPoi, int latitudPoi) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
