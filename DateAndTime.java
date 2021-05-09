
package potplayer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class DateAndTime {
    public static String readDateAndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date dateTime = new Date(System.currentTimeMillis());

        return formatter.format(dateTime);
    }

    public static String readDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();

        return formatter.format(date);
    }

    public static String readTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.now();

        return formatter.format(time);
    }

    public static String readWebDateAndTime(String webUrl, String format) {
        if (webUrl == null) {
            webUrl = "http://www.baidu.com";
        }

        try {
            String wUrl = webUrl;
            URL url = new URL(wUrl);
            URLConnection urlConnect = url.openConnection();
            urlConnect.setConnectTimeout(3000);
            urlConnect.setReadTimeout(3000);
            urlConnect.connect();

            long unformatDate = urlConnect.getDate();
            Date date = new Date(unformatDate);

            String type = null;
            if (format.equals("all")) {
                type = "yyyy-MM-dd HH:mm:ss";
            } else if (format.equals("time")) {
                type = "HH:mm:ss";
            } else if (format.equals("date")) {
                type = "yyyy-MM-dd";
            } else {
                type = format;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(type, Locale.CHINA);
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
