package tpAnual;

public class CGPAdapter {
	private Object CGPExterno; //TODO es un mock, no Object.
		
	private Cgp adaptar(CentroDTO json){
		return null;
		//TODO adapta el CentroDTO a un banco
	}
		
	// Consultar es un metodo de la interface externa que me da el JSON que esto debe adaptar
	public Cgp consultar(String criterioBusqueda){
		return null;
//		return this.adaptar(CGPExterno.consultar(criterioBusqueda));
	}
}
