package CAMS.Data;

import java.util.Date;

class DateRange {
  // TODO: functions should be `private-level private`, ie no annotation
  private Date start;
  private Date end;

  DateRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }


  Date start() {
    return this.start;
  }

  void setStart(Date date) {
    this.start = date;
  }

  Date end() {
    return this.end;
  }

  void setEnd(Date date) {
    this.end = date;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof DateRange)) {
      return false;
    }
    return this.start.getTime() == ((DateRange) o).start.getTime() &&
      this.end.getTime() == ((DateRange) o).end.getTime();
  }

  @Override
  public String toString() {
    //    return "DateRange: " + this.start + " to " + this.end;
    return STR. "DateRange: \{ this.start } to \{ this.end }" ;
  }

  boolean isClashing(CAMS.Data.DateRange range) {
    return (this.start.getTime() <= range.end.getTime()) &&
      (this.end.getTime() >= range.start.getTime());
  }

  boolean isNotClashing(CAMS.Data.DateRange range) {
    return !((this.start.getTime() <= range.end.getTime()) &&
      (this.end.getTime() >= range.start.getTime()));
  }

  boolean isClashing(Date date) {
    return (this.start.getTime() <= date.getTime()) &&
      (this.end.getTime() >= date.getTime());
  }

  boolean isNotClashing(Date date) {
    return !((this.start.getTime() <= date.getTime()) &&
      (this.end.getTime() >= date.getTime()));
  }

  void printSelf() {
    System.out.println(this);
  }
}
