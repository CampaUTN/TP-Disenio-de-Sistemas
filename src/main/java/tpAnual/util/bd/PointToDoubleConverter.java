package tpAnual.util.bd;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.uqbar.geodds.Point;

@Converter
public class PointToDoubleConverter implements AttributeConverter<Point, String>{
	private static final String SEPARATOR = "_";
	
	// TODO: rompe al convertir (en el modelo de objetos ya rompe, aunque no se persista nunca nada)
	
	/**
	 * Punto -> String
	 */
	@Override
	public String convertToDatabaseColumn(Point punto) {
		if (punto == null)
            return null;
		StringBuilder stringCoordenadas = new StringBuilder();
		stringCoordenadas.append(String.valueOf(punto.latitude()))
				.append(SEPARATOR)
				.append(punto.longitude());
		return stringCoordenadas.toString();
	}

	/**
	 * String -> Punto
	 */
	@Override
	public Point convertToEntityAttribute(String stringPunto) {
		if (stringPunto == null)
            return null;
		String[] coordenadas = stringPunto.split(SEPARATOR);
		return new Point(new Double(coordenadas[0]), Double.parseDouble(coordenadas[1]));
	}
	
}
