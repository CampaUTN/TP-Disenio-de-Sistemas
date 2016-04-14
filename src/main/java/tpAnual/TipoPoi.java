package tpAnual;

public abstract class TipoPoi {
	
	public abstract boolean estaDisponible();

	public abstract boolean estaCerca(int altitud, int latitud, int altitudPoi, int latitudPoi);

}
