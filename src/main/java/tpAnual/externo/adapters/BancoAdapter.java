package tpAnual.externo.adapters;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Poi;
import tpAnual.externo.mocks.MockSistemaBancario;
import tpAnual.externo.sistemasExternos.BancoExterno;
import tpAnual.externo.sistemasExternos.CentroDTO;
import tpAnual.externo.sistemasExternos.Consultora;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;

public class BancoAdapter implements Consultora {

	private MockSistemaBancario sistemaBancoExterno = new MockSistemaBancario();

	// TODO para evitar la repeticion, puedo hacer una abstract, aunque primero
	// tener todo andando.
	public List<Poi> consultar(List<String> palabras) {
		List<Poi> pois = new ArrayList<Poi>();
		palabras.forEach(palabra -> pois.addAll(this.bancosConServicio(palabra)));
		return pois;
	}

	private List<Poi> bancosConServicio(String servicio) {
		List<Poi> bancos = new ArrayList<Poi>();

			bancos = this.convertirAPois(MongoDatastoreSingleton.getDatastore("Bancos")
					.createQuery(BancoExterno.class)
					.field("servicios")
					.containsIgnoreCase(servicio)
					.asList());
		return bancos;
	}

	public List<Poi> getPois() {
		return this.convertirAPois(adaptar(sistemaBancoExterno.consultar("")));
	}
	
	public void actualizarTodos() {
		this.getPois()
			.stream()
			.collect(Collectors.toSet())
			.forEach(poi -> MongoDatastoreSingleton.getDatastore("Bancos").save(poi));;
	}

	public List<BancoExterno> adaptar(File reader) {
		String contenido;
		try {
			contenido = FileUtils.readFileToString(reader, Charset.defaultCharset());
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}

		return new Gson().fromJson(contenido, new TypeToken<List<BancoExterno>>() {
		}.getType());
	}

	public List<Poi> convertirAPois(List<BancoExterno> bancosExternos) {
		return bancosExternos.stream().map(banco -> bancoExternoToPOI(banco)).collect(Collectors.toList());
	}

	public Poi bancoExternoToPOI(BancoExterno bancoExterno) {
		Double posX = Double.parseDouble(bancoExterno.getX());
		Double posY = Double.parseDouble(bancoExterno.getY());
		PointWrapper ubicacion = new PointWrapper(posX, posY);
		String nombre = bancoExterno.getBanco();
		Set<String> servicios = new HashSet<String>(Arrays.asList(bancoExterno.getServicios()));

		return new Banco(ubicacion, nombre, servicios);
	}
}
