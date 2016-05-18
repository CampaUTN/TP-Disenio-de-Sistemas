package tpAnual;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.omg.CORBA.portable.InputStream;

import java.util.List;

public class MockEntidadesBancarias implements EntidadesBancarias {
	private Object json = null;
	
	@Override
	public JSONObject consultar(String palabra){
        try {
        	json = (new JSONParser()).parse(new FileReader("bancos.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (JSONObject)json;
	}
	
//	public JSONObject consultar(String palabra){
//        try {
//        	InputStream is = 
//                    JsonParsing.class.getResourceAsStream( "sample-json.txt");
//            String jsonTxt = IOUtils.toString( is );
//
//            JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt ); 
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (JSONObject)json;
//	} 
}
