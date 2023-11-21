package CAMS.Data.Utils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class DateRangeList {
  // TODO: functions should be `package-level private`, ie no annotation

  private final HashSet<DateRange> dates;

  public DateRangeList(List<DateRange> dates) {
    this.dates = new HashSet<>(dates);
  }

  public DateRangeList() {
    this.dates = new HashSet<>();
  }

  public void addDateRange(DateRange range) throws Exception {
    if (this.isNotClashing(range)) {
      this.dates.add(range);
    } else {
      throw new Exception("Dates Clashing");
    }
  }

  public boolean isNotClashing(DateRange range) {
    for (DateRange rg : this.dates) {
      if (rg.isNotClashing(range)) {
        return true;
      }
    }
    return false;
  }

  public void removeDateRange(DateRange range) {
    dates.remove(range);
  }

  public boolean isClashing(DateRange range) {
    for (DateRange rg : this.dates) {
      if (rg.isClashing(range)) {
        return true;
      }
    }
    return false;
  }

  public boolean isClashing(Date date) {
    for (DateRange rg : this.dates) {
      if (rg.isClashing(date)) {
        return true;
      }
    }
    return false;
  }

  public boolean isNotClashing(Date date) {
    for (DateRange rg : this.dates) {
      if (rg.isNotClashing(date)) {
        return true;
      }
    }
    return false;
  }

  public void printSelf() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    String str = "";
    for (DateRange date : this.dates) {
      str = str.concat(date.toString()).concat("\n");
    }

    return ("DATE RANGE:\n" + str.indent(4)).strip();
  }
}
