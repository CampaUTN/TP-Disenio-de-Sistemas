package tpAnual.batch.procesos;

import java.util.HashMap;
import java.util.Map;

public class CreadorAcciones {
	Map<String,AccionTerminal> acciones = new HashMap<String,AccionTerminal>();
	
	public void activarMails(){
		this.acciones.put("ActivarMail",new ActivarMails());
	}
	public void desactivarMails(){
		this.acciones.put("DesactivarMail",new DesactivarMails());
	}
	public void activarRegistros(){
		this.acciones.put("ActivarRegistros",new ActivarRegistros());
	}
	public void desactivarRegistros(){
		this.acciones.put("DesactivarRegistros",new DesactivarRegistros());
	}
	
	public Map<String,AccionTerminal> dameAcciones(){
		return acciones;
	}
	
}