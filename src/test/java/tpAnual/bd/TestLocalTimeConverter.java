package tpAnual.bd;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import tpAnual.util.bd.LocalTimeToTimeConverter;

public class TestLocalTimeConverter {
	
	private LocalTime hora = LocalTime.parse("10:00:30");
	private LocalTimeToTimeConverter converter = new LocalTimeToTimeConverter();
	
	private Time horaEnBD = Time.valueOf("10:00:30");
	
	
	@Test
	public void conviertoLocalTimeATime(){		
		Assert.assertEquals(horaEnBD, converter.convertToDatabaseColumn(hora));
	}
	
	@Test
	public void conviertoTimeALocalTime(){
		Assert.assertEquals(hora, converter.convertToEntityAttribute(horaEnBD));
	}	
}
