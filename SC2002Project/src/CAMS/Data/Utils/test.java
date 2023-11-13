package CAMS.Data;

import CAMS.Data.Utils.DateHelper;

import java.text.ParseException;

public class test {
  public static void main(String[] args) {

    CAMS.Data.DateRange date =
      new CAMS.Data.DateRange(DateHelper.get(2023, 11, 1),
        DateHelper.get(2023, 11, 3));
    CAMS.Data.DateRange date2 =
      null;
    try {
      date2 = new CAMS.Data.DateRange(DateHelper.get(2023, 10, 10),
        DateHelper.get("2023", "10", "32"));
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }

    System.out.println(date);
    System.out.println(date2);
    System.out.println(date.isClashing(date2));
  }
}
