package tpAnual.bd;

import javax.persistence.AttributeConverter;

import org.uqbar.geodds.Point;

public class PointToDoubleConverter implements AttributeConverter<Point, String>{
	private static final String SEPARATOR = "_";
	
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
		System.out.println("punto: "+punto.latitude()+", "+punto.longitude()+"--->string: "+stringCoordenadas.toString());
		return stringCoordenadas.toString();
	}

	/**
	 * String -> Punto
	 */
	@Override
	public Point convertToEntityAttribute(String stringPunto) {
		String[] coordenadas = stringPunto.split(SEPARATOR);
		System.out.println("coord[0]: "+coordenadas[0]+", coord[1]: "+coordenadas[1]);
		Point punto = new Point(new Double(coordenadas[0]), Double.parseDouble(coordenadas[1]));
		System.out.println("coord[0]: "+coordenadas[0]+", coord[1]: "+coordenadas[1]+"--->Point: lat: "+punto.latitude()+", long: "+punto.longitude());
		return punto;
	}
	
}
