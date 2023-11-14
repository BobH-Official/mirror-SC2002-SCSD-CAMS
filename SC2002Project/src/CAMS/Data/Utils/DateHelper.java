package CAMS.Data.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
  public static Date get(String year, String month, String day)
    throws ParseException {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    return df.parse(year + "-" + month + "-" + day + "T00:00:00");
  }

  public static Date get(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, day, 0, 0, 0);
    return calendar.getTime();
  }
}
