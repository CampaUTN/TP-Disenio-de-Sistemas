package tpAnual.batch.postEjecucionProceso;

import tpAnual.batch.procesos.Proceso;

/**
 * Interface para que el manejador se comunique con sus observadores
 */
public interface PostEjecucionProceso {
	
	/**
	 * Le informa el resultado de una ejecucucion para cierto proceso a los observador que implementen este metodo
	 */
	public void accionar(Proceso proceso);
	
}
