package tpAnual;

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class BancoAdapter {
	private Object bancoExterno; //TODO es un mock, no Object.
	
	
	private Banco adaptar(JSONObject json){
		//TODO adapta el JSON a un banco
		return new Banco(); // TODO borrar esta linea
	}
	
	// Consultar es un metodo de la interface externa que me da el JSON que esto debe adaptar
	public Banco consultar(String nombre, String servicio){
//		return this.adaptar(bancoExterno.consultar(nombre, servicio));
		return new Banco(); // TODO borrar esta linea
	}
}