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
import org.mongodb.morphia.Datastore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Poi;
import tpAnual.externo.mocks.MockSistemaBancario;
import tpAnual.externo.sistemasExternos.BancoDTO;
import tpAnual.externo.sistemasExternos.Buscador;
import tpAnual.externo.sistemasExternos.Consultora;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;

public class BancoAdapter extends Buscador implements Consultora{

	private MockSistemaBancario sistemaBancoExterno = new MockSistemaBancario();

	public BancoAdapter(){
		this.base = "busquedas";
	}
	// TODO para evitar la repeticion, puedo hacer una abstract, aunque primero
	// tener todo andando.
	public List<Poi> consultar(List<String> palabras) {
		this.persistirPoisExternos();
		
		List<Poi> poisConServicio = new ArrayList<Poi>();
		List<Poi> poisConNombre = new ArrayList<Poi>();
		palabras.forEach(palabra -> poisConServicio.addAll(this.bancosConServicio(palabra)));
		palabras.forEach(palabra -> poisConNombre.addAll(this.bancosConNombre(palabra)));
//		return poisConServicio
//				.stream()
//				.filter(p -> poisConNombre.contains(p))
//				.collect(Collectors.toSet())
//				.stream()
//				.collect(Collectors.toList());
		poisConNombre.addAll(poisConServicio);
		return poisConNombre;
	}

	private List<Poi> bancosConServicio(String servicio) {
		return this.convertirAPois(
					this.getDataBase()
					.createQuery(BancoDTO.class)
					.field("servicios")
					.containsIgnoreCase(servicio)
					.asList());
	}
	
	private List<Poi> bancosConNombre(String nombre) {
		return this.convertirAPois(
					this.getDataBase()
					.createQuery(BancoDTO.class)
					.field("banco")
					.equalIgnoreCase(nombre)
					.asList());
	}
	
	public void persistirPoisExternos(){
		Datastore datastore = MongoDatastoreSingleton.getDatastore("busquedas");
		
		//Borro todos
		datastore.getDB().getCollection("BancoDTO").drop();

		
		//Los traigo
		List<BancoDTO> bancosDto = new ArrayList<BancoDTO>();
		bancosDto.addAll(this.adaptar(sistemaBancoExterno.consultar(null)));
		
		//Los persisto
		bancosDto.forEach(banco -> datastore.save(banco));
	}
	
	public List<Poi> getPois() {
		return this.convertirAPois(adaptar(sistemaBancoExterno.consultar("")));
	}
	

	public List<BancoDTO> adaptar(File reader) {
		String contenido;
		try {
			contenido = FileUtils.readFileToString(reader, Charset.defaultCharset());
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}

		return new Gson().fromJson(contenido, new TypeToken<List<BancoDTO>>() {
		}.getType());
	}

	public List<Poi> convertirAPois(List<BancoDTO> bancosExternos) {
		return bancosExternos.stream().map(banco -> bancoExternoToPOI(banco)).collect(Collectors.toList());
	}

	public Poi bancoExternoToPOI(BancoDTO bancoExterno) {
		Double posX = Double.parseDouble(bancoExterno.getX());
		Double posY = Double.parseDouble(bancoExterno.getY());
		PointWrapper ubicacion = new PointWrapper(posX, posY);
		String nombre = bancoExterno.getBanco();
		Set<String> servicios = new HashSet<String>(Arrays.asList(bancoExterno.getServicios()));

		return new Banco(ubicacion, nombre, servicios);
	}
}
