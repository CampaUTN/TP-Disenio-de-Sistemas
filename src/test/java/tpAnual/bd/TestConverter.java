package tpAnual.bd;

import org.junit.Test;
import org.uqbar.geodds.Point;

import junit.framework.Assert;

public class TestConverter {
	private Point ubicacion = new Point(6.77,40);
	private PointToDoubleConverter converter = new PointToDoubleConverter();
	
	@Test
	public void TestConverterPointLatitud(){
		String stringUbicacion = converter.convertToDatabaseColumn(ubicacion);
		Assert.assertEquals(ubicacion.latitude(), converter.convertToEntityAttribute(stringUbicacion).latitude(),0);
	}
	
	@Test
	public void TestConverterPointLongitud(){
		String stringUbicacion = converter.convertToDatabaseColumn(ubicacion);
		Assert.assertEquals(ubicacion.longitude(), converter.convertToEntityAttribute(stringUbicacion).longitude(),0);
	}
}
