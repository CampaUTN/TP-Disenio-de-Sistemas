package tpAnual.externo.mocks;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class MockBajaPoi {
	
	public File consultar(String palabra){
		return FileUtils.getFile("src/test/resources/bajapois.json");
	}

}
