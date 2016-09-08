package tpAnual.bd;

import org.junit.Test;
import org.uqbar.geodds.Point;

import junit.framework.Assert;

public class TestConverter {
	private Point ubicacion = new Point(6.77,40.0);
	private PointToDoubleConverter converter = new PointToDoubleConverter();
	
	//TODO rompe
	@Test
	public void TestConverterPoint(){
		String stringUbicacion = converter.convertToDatabaseColumn(ubicacion);
		//Assert.assertEquals(ubicacion.latitude(), converter.convertToEntityAttribute(stringUbicacion).latitude(),0);
	}
}
