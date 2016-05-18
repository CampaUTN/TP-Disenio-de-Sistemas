package tpAnual;

import org.json.simple.JSONObject;


public interface EntidadesBancarias {
	public JSONObject consultar(String nombre, String servicio);
}
