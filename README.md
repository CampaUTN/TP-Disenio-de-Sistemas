# 2016-jm-group-07
TP 2016 del Grupo 7 del curso K3001

------------OpeningHours nueva:
MAVEN: " <dependency>
    	<groupId>com.google.maps</groupId>
	<artifactId>google-maps-services</artifactId>
   	<version>0.1.12</version>
      </dependency> "
      
CLASE: import com.google.maps.model.OpeningHours;

PAGINA CON LOS METODOS EXPLICACION DEFICIENTE: http://googlemaps.github.io/google-maps-services-java/v0.1.8/javadoc/com/google/maps/model/OpeningHours.html#OpeningHours--

------------Clase loca que encontre de la version anterior de OpeningHours

package tpAnual;

import org.joda.time.DateTime;

import java.lang.Object.*;
import com.google.maps.model.OpeningHours;

public class Horario {
	
	public enum DAY {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	  }
	
	public Horario(DAY day, Integer from, Integer to) {
	    this.day = day;
	    this.from = from; // format time using 800 for 8:00am or 2300 for 23:00
	    this.to = to;
	}
	
	@Override
	public String toString() {
	    return "OpeningHours [day=" + day + ", from=" + from + ", to=" + to + ", isAllDay=" + isAllDay + "]";
	}
	
	public DAY day;
	public Integer from;
	public Integer to;
	public boolean isAllDay = false;
	
	public void isOpenx(DateTime start) {
	
	}
	
	public boolean isOpen(DateTime start) {
	
	    if (day.ordinal() != start.getDayOfWeek() - 1) {
	        return false;
	    }
	
	    if (isAllDay)
	        return true;
	
	    String f = String.format("%04d", from);
	    String t = String.format("%04d", to);
	
	    Integer fh = Integer.valueOf(f.substring(0, 2));
	    Integer fm = Integer.valueOf(f.substring(2));
	
	    Integer th = Integer.valueOf(t.substring(0, 2));
	    Integer tm = Integer.valueOf(t.substring(2));
	
	    DateTime intStart = start.withHourOfDay(fh).withMinuteOfHour(fm);
	    DateTime intEnd = start.withHourOfDay(th).withMinuteOfHour(tm);
	
	    if (intStart.equals(start) || intEnd.equals(start)) {
	        return true;
	    }
	    if (intStart.isBefore(start) && intEnd.isAfter(start)) {
	        return true;
	    }
	
	    return false;

		}
	}
