package tpAnual;

import java.util.ArrayList;
import java.util.List;

public class ExternoCPO {
	
	public List<CentroDTO> centrosDTO = new ArrayList<CentroDTO>();
	
	CentroDTO dto1 = new CentroDTO();
	CentroDTO dto2 = new CentroDTO();
	
	List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
	ServicioDTO serv1 = new ServicioDTO("Cheques",1,7,0,19,0);
	ServicioDTO serv2 = new ServicioDTO("Tramites",3,9,0,15,0);
	
	public List<CentroDTO> consultar(String palabra){
		servicios.add(serv1);
		servicios.add(serv2);
		dto1.setServicios(servicios);
		dto2.setServicios(servicios);
		
		centrosDTO.add(dto1);
		centrosDTO.add(dto2);
		
		return centrosDTO;
	}
}
