package tpAnual.bd;

import javax.persistence.AttributeConverter;

import org.uqbar.geodds.Point;

public class PointToDoubleConverter implements AttributeConverter<Point, String>{
	private static final String SEPARATOR = "|";
	
	// TODO: rompe al convertir (en el modelo de objetos ya rompe, aunque no se persista nunca nada)
	
	/**
	 * Punto -> String
	 */
	@Override
	public String convertToDatabaseColumn(Point punto) {
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
		String[] coordenadas = stringPunto.split(SEPARATOR);
		return new Point(new Double(coordenadas[0])*10, Double.parseDouble(coordenadas[1]));
	}
	
}
