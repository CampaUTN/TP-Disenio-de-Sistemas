package tpAnual.externo.adapters;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.uqbar.geodds.Point;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Poi;
import tpAnual.externo.mocks.MockSistemaBancario;
import tpAnual.externo.sistemasExternos.BancoExterno;
import tpAnual.externo.sistemasExternos.Consultora;


public class BancoAdapter implements Consultora {
	
	private MockSistemaBancario sistemaBancoExt = new MockSistemaBancario();
	
	public List<Poi> consultar(List<String> palabras){
		List<BancoExterno> bancosExternos = new ArrayList<BancoExterno>();
		palabras.forEach(palabra->bancosExternos.addAll(this.adaptar(sistemaBancoExt.consultar(palabra))));
		return this.convertirAPois(bancosExternos);
	}
	
	public List<BancoExterno> adaptar(File reader){
			List<BancoExterno> bancosExternos = new ArrayList<BancoExterno>();
			
			Gson gson = new Gson();
			String contenido = null;
			try {
				contenido = FileUtils.readFileToString(reader,Charset.defaultCharset());
			} catch (IOException e) {
				throw new UnsupportedOperationException(e);
			}
			bancosExternos = gson.fromJson(contenido, new TypeToken<List<BancoExterno>>() {}.getType());
			
			return bancosExternos;
	}
	
	public List<Poi> convertirAPois(List<BancoExterno> bancosExternos){
		return bancosExternos
				.stream()
				.map(banco -> bancoExternoToPOI(banco))
				.collect(Collectors.toList());
	}
	
	public Poi bancoExternoToPOI(BancoExterno bancoExt){
		Banco banco = new Banco();
		Double posX = Double.parseDouble(bancoExt.getX());
		Double posY = Double.parseDouble(bancoExt.getY());
		Point ubicacion = new Point(posX,posY);
		String nombre = bancoExt.getBanco();
		Set<String> servicios = new HashSet<String>(Arrays.asList(bancoExt.getServicios()));
		
		return new Poi(banco, ubicacion, nombre, servicios);
	}

}
