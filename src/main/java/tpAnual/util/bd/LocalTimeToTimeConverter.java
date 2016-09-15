package tpAnual.util.bd;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalTimeToTimeConverter implements AttributeConverter<LocalTime, Time> {

	@Override
	public Time convertToDatabaseColumn(LocalTime time) {
		return Time.valueOf(time);
	}

	@Override
	public LocalTime convertToEntityAttribute(Time time) {
		return time.toLocalTime();
	}
}