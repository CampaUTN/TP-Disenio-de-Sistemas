package tpAnual;

import java.io.BufferedReader;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.omg.CORBA.portable.InputStream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ExternoEntidadesBancarias implements EntidadesBancarias {

	@Override
	public BufferedReader consultar(String palabra){
        try {
                BufferedReader reader = new BufferedReader(new FileReader("bancos.json"));
                return reader;
                 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
}