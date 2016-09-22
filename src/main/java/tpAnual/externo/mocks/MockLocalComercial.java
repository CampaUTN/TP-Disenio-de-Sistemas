package tpAnual.externo.mocks;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class MockLocalComercial {
	
	public File getFile(){
		return FileUtils.getFile("src/test/resources/localesComerciales.txt");
	}
	
}
