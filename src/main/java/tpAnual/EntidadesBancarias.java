package tpAnual;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.util.List;

public interface EntidadesBancarias {
	public BufferedReader consultar(String tags);
}
