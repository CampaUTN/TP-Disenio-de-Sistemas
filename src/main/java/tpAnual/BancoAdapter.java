package tpAnual;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.uqbar.geodds.Point;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class BancoAdapter {
	
	private ExternoEntidadesBancarias sistemaBancoExt = new ExternoEntidadesBancarias();
	
	public List<Poi> consultar(List<String> palabras){
		List<BancoExterno> bancosExternos = new ArrayList<BancoExterno>();
		palabras.forEach(palabra->bancosExternos.addAll(this.adaptar(sistemaBancoExt.consultar(palabra))));
		
		return this.convertirAPois(bancosExternos);
	}
	
	public List<BancoExterno> adaptar(BufferedReader reader){
			List<BancoExterno> bancosExternos = new ArrayList<BancoExterno>();
			try {
				Gson gson = new Gson();
				bancosExternos = gson.fromJson(reader, new TypeToken<List<BancoExterno>>() {}.getType()); 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return bancosExternos;
	}
	
	public List<Poi> convertirAPois(List<BancoExterno> bancosExternos){
		List<Poi> poisExternos = new ArrayList<Poi>();
		bancosExternos.forEach(banco-> poisExternos.add( convertirUnPoi(banco)));
		return poisExternos;
	}
	
	public Poi convertirUnPoi(BancoExterno bancoExt){
		Banco banco = new Banco();
		int posX = Integer.parseInt(bancoExt.getX());
		int posY = Integer.parseInt(bancoExt.getY());
		Point ubicacion = new Point(posX,posY);
		String nombre = bancoExt.getBanco();
		Set<String> servicios = new HashSet<String>(Arrays.asList(bancoExt.getServicios()));
		
		return new Poi(banco, ubicacion, nombre, servicios);
	}
	

}