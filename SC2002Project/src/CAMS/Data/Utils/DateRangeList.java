package CAMS.Data;

import java.util.Date;
import java.util.List;

class DateRangeList {
  // TODO: functions should be `package-level private`, ie no annotation

  final List<CAMS.Data.DateRange> dates;

  DateRangeList(List<CAMS.Data.DateRange> dates) {
    this.dates = dates;
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
    return false;
  }

}
