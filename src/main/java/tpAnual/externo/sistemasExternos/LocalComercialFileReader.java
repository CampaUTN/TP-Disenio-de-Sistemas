package tpAnual.externo.sistemasExternos;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class LocalComercialFileReader {

	private String direccion;
	
	
	public LocalComercialFileReader(String direccion){
		this.direccion = direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	
	public File getFile(){
		return FileUtils.getFile(direccion);
	}
}
