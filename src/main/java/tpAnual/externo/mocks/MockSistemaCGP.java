package tpAnual.externo.mocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tpAnual.externo.sistemasExternos.CentroDTO;
import tpAnual.externo.sistemasExternos.ServicioDTO;

public class MockSistemaCGP {
	
	private List<CentroDTO> centrosDTO = new ArrayList<CentroDTO>();
	private CentroDTO dto1 = new CentroDTO();
	private CentroDTO dto2 = new CentroDTO();
	private List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
	private ServicioDTO serv1 = new ServicioDTO("Cheques",1,10,00,19,00);
	private ServicioDTO serv2 = new ServicioDTO("Tramites",3,11,00,15,00);
	
	public List<CentroDTO> consultar(String palabra){
		Set<String> zonas = new HashSet<String>();
		zonas.add("Palermo");
		zonas.add("Belgrano");
		dto1.setZonas(zonas);
		dto1.setDomicilio("Medrano");
		
		dto2.setZonas(zonas);
		dto2.setDomicilio("Mozart");
		
		servicios.add(serv1);
		servicios.add(serv2);
		dto1.setServicios(servicios);
		dto2.setServicios(servicios);
		centrosDTO.add(dto1);
		centrosDTO.add(dto2);
		return centrosDTO;
	}
}
