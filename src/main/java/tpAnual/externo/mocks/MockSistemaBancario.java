package tpAnual.externo.mocks;

import java.io.File;

import org.apache.commons.io.FileUtils;


public class MockSistemaBancario{

	public File consultar(String palabra){
		return FileUtils.getFile("src/test/resources/bancos.json");
	}
	
}