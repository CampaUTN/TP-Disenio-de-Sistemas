package tpAnual;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.List;

public class MockEntidadesBancarias implements EntidadesBancarias {
	private Object json = null;
	
	@Override
	public JSONObject consultar(String palabra){
        try {
        	json = (new JSONParser()).parse(new FileReader("banco.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (JSONObject)json;
	}
}
