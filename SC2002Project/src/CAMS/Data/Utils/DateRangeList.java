package CAMS.Data;

import java.util.Date;
import java.util.List;

class DateRangeList {
  // TODO: functions should be `package-level private`, ie no annotation

  final List<CAMS.Data.DateRange> dates;

  DateRangeList(List<CAMS.Data.DateRange> dates) {
    this.dates = dates;
  }

  void addDateRange(CAMS.Data.DateRange range) throws Exception {
    if (this.isNotClashing(range)) {
      this.dates.add(range);
    } else {
      throw new Exception("Dates Clashing");
    }
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

  boolean isNotClashing(CAMS.Data.DateRange range) {
    for (CAMS.Data.DateRange rg : this.dates) {
      if (rg.isNotClashing(range)) {
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
}
