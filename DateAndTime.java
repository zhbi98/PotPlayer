
package potplayer;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


public class DateAndTime {
    public static String readDateAndTime() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date dateTime = new Date(System.currentTimeMillis());

        return formatter.format(dateTime);
    }

    public static String readDate() {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();

        return formatter.format(date);
    }

    public static String readTime() {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.now();

        return formatter.format(time);
    }
}
