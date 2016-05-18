package tpAnual;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.uqbar.geodds.Point;


public class BancoAdapter {
	
	private MockEntidadesBancarias bancoExterno;
	
	// Consultar es un metodo de la interface externa que me da el JSON que esto debe adaptar
	public List<Poi> consultar(List<String> tags){
		return this.adaptar(bancoExterno.consultar(tags));
	}
	
    public List<Poi> adaptar(JSONObject jsonObject){
    	
    	JSONParser parser = new JSONParser();
    	List<Poi> poisExternos = new ArrayList<Poi>();
	 
	     try {
	 
	         String banco = (String) jsonObject.get("banco");
	         String x = (String) jsonObject.get("x");
	         String y = (String) jsonObject.get("y");
	         String sucursal = (String) jsonObject.get("sucursal");
	         String gerente = (String) jsonObject.get("gerente");
	           
	         String servicios = (String) jsonObject.get("servicios");
	            
	         Integer puntoX = Integer.parseInt(x);
	         Integer puntoY = Integer.parseInt(y);
	
	         Banco tipoBanco = new Banco();
	         Point ubicacion = new Point(puntoX,puntoY);
	         Poi poiExterno = new Poi(tipoBanco, ubicacion, servicios, null);
	         poisExternos.add(poiExterno);
	
	         JSONArray companyList = (JSONArray) jsonObject.get("Company List");
	         Iterator<String> iterator = companyList.iterator();
	         while (iterator.hasNext()) {
	        	 System.out.println(iterator.next());
	         }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return poisExternos;
    }

}