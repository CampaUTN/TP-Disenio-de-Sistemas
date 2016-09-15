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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Poi;
import tpAnual.externo.mocks.MockSistemaBancario;
import tpAnual.externo.sistemasExternos.BancoExterno;
import tpAnual.externo.sistemasExternos.Consultora;
import tpAnual.utils.PointWrapper;


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
	
	public Poi bancoExternoToPOI(BancoExterno bancoExterno){
		Double posX = Double.parseDouble(bancoExterno.getX());
		Double posY = Double.parseDouble(bancoExterno.getY());
		PointWrapper ubicacion = new PointWrapper(posX,posY);
		String nombre = bancoExterno.getBanco();
		Set<String> servicios = new HashSet<String>(Arrays.asList(bancoExterno.getServicios()));
		
		return new Banco(ubicacion, nombre, servicios);
	}

}
