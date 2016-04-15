package tpAnual;


public abstract class TipoPoi {
	
	public abstract boolean estaDisponible();

	public abstract boolean estaCerca(int altitud, int latitud, int altitudPoi, int latitudPoi);

	public boolean brinda(String servicio)
	{
		return false;
	}
	
	public boolean estaDisponible(String servicio)
	{
		
		// como banco y GCP manejan distinto los tramites, implementan de diferente forma este metodo
		// entoces cada una de esas subclases lo redefine a gusto. Las demas, por default, tiran excepcion, porque no tienen tramites.
		return false; //TODO
	}

}
