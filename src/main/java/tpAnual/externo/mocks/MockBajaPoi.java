package tpAnual.externo.mocks;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import tpAnual.externo.sistemasExternos.PoiAEliminarDTO;


public class MockBajaPoi {
	
	public List<PoiAEliminarDTO> consultar(){
		List<PoiAEliminarDTO> pois = new ArrayList<PoiAEliminarDTO>();
		PoiAEliminarDTO poiElim = new PoiAEliminarDTO();
		poiElim.setId(1);
		pois.add(poiElim);
		return pois;
	}

}
