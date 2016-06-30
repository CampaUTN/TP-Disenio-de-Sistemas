package tpAnual.externo.sistemasExternos;

public class PoiAEliminarDTO {
	private int id;
    private String fechaEliminado;
    
    public int getId(){
    	return id;
    }
    
    public void setId(int identificador){
    	this.id=identificador;
    }
    
    public String getFechaEliminado(){
    	return fechaEliminado;
    }
    
    public void setFechaEliminado(String fechaEliminacion){
    	this.fechaEliminado = fechaEliminacion;
    }

}
