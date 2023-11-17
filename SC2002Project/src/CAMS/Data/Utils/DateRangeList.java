package CAMS.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class DateRangeList {
  // TODO: functions should be `package-level private`, ie no annotation

  final List<CAMS.Data.DateRange> dates;

  DateRangeList(List<CAMS.Data.DateRange> dates) {
    this.dates = dates;
  }

  DateRangeList() {
    this.dates = new ArrayList<>();
  }

  void addDateRange(CAMS.Data.DateRange range) throws Exception {
    if (this.isNotClashing(range)) {
      this.dates.add(range);
    } else {
      throw new Exception("Dates Clashing");
    }
  }

  boolean isNotClashing(CAMS.Data.DateRange range) {
    for (CAMS.Data.DateRange rg : this.dates) {
      if (rg.isNotClashing(range)) {
        return true;
      }
    }
    return false;
  }

  void removeDateRange(CAMS.Data.DateRange range) {
    dates.remove(range);
  }

  boolean isClashing(CAMS.Data.DateRange range) {
    for (CAMS.Data.DateRange rg : this.dates) {
      if (rg.isClashing(range)) {
        return true;
      }
    }
    return false;
  }

  boolean isClashing(Date date) {
    for (CAMS.Data.DateRange rg : this.dates) {
      if (rg.isClashing(date)) {
        return true;
      }
    }
    return false;
  }

  boolean isNotClashing(Date date) {
    for (CAMS.Data.DateRange rg : this.dates) {
      if (rg.isNotClashing(date)) {
        return true;
      }
    }
    return false;
  }

  void printSelf() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    String str = "";
    for (CAMS.Data.DateRange date : this.dates) {
      str = str.concat(date.toString()).concat("\n");
    }

    return ("DATE RANGE:\n" + str.indent(4)).strip();
  }
}
