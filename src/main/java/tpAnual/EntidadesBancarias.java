package tpAnual;

import org.json.simple.JSONObject;
import java.util.List;

public interface EntidadesBancarias {
	public JSONObject consultar(List<String> tags);
}
