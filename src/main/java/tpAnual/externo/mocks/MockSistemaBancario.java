package tpAnual.externo.mocks;

import java.io.File;
import org.apache.commons.io.FileUtils;
public class MockSistemaBancario{

	public File consultar(String palabra){
		File file = FileUtils.getFile("src/test/resources/bancos.json");
        //BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/bancos.json"));
      	return file;
	}
	
}