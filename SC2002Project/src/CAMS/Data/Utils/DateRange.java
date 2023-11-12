package CAMS.Data;

import java.util.Date;

class DateRange {
  // TODO: functions should be `private-level private`, ie no annotation
  private final Date start;
  private final Date end;

  DateRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  boolean isClashing(CAMS.Data.DateRange range) {
    return (this.start.getTime() <= range.end.getTime())
      && (this.end.getTime() >= range.start.getTime());
  }

  boolean isNotClashing(CAMS.Data.DateRange range) {
    return !((this.start.getTime() <= range.end.getTime())
      && (this.end.getTime() >= range.start.getTime()));
  }

  boolean isClashing(Date date) {
    return (this.start.getTime() <= date.getTime())
      && (this.end.getTime() >= date.getTime());
  }

  boolean isNotClashing(Date date) {
    return !((this.start.getTime() <= date.getTime())
      && (this.end.getTime() >= date.getTime()));
  }

}
