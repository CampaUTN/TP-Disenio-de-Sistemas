package tpAnual.busquedas;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tpAnual.POIs.Banco;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.externo.sistemasExternos.BancoDTO;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;
import tpAnual.util.wrapper.PointWrapper;

import org.mongodb.morphia.Datastore;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioPersistente extends RepositorioBusqueda implements WithGlobalEntityManager {
	
	private static Datastore datastore;
	
	public void registrarBusqueda(String palabrasIngresadas, List<Poi> pois){
		
		MongoDatastoreSingleton.
			getDatastore("busquedas").
			save(new Busqueda(palabrasIngresadas,pois));
	}
	
	public List<Busqueda> getBusquedas(){
		List<Busqueda> busquedas = new ArrayList<Busqueda>();
//		busquedas = datastore.createQuery(Busqueda.class).asList();
		busquedas.add(new Busqueda("palabras1", null));
		busquedas.add(new Busqueda("palabras2", null));
		return busquedas;
	}
	
	public List<Busqueda> listar(String fechaDesde, String fechaHasta) {
		//Borrar esto de prueba
		List<Poi> pois = new ArrayList<>();
		
		Poi poi1 = (Poi)new Negocio(new PointWrapper(0, 0),"Negocio",null,"compras",5);
		Poi poi2 = (Poi) new Banco(new PointWrapper(2, 2), "Banco Santander" , null);
		
		poi1.setCalle("Strangford");
		poi1.setDireccion(1857);
		
		poi2.setCalle("Avenida Rivadavia");
		poi2.setDireccion(458);
		
		pois.add(poi1);
		pois.add(poi2);
		
		Busqueda b1 = new Busqueda("hola", pois);
		b1.setFecha(Date.valueOf(LocalDate.now()));
		
		//TODO BORRAR ESTO DE PRUEBA
		List<Busqueda> busqueda = new ArrayList<>();
		busqueda.add(b1);
		
		datastore.save(busqueda);
		
		List<Busqueda> busquedas = datastore.createQuery(Busqueda.class).asList();
		return busquedas;
	}
	
	
}
