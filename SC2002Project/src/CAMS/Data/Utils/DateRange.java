package CAMS.Data.Utils;

import java.util.Date;

public class DateRange {
  // TODO: functions should be `private-level private`, ie no annotation
  private Date start;
  private Date end;

  public DateRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }


  public Date start() {
    return this.start;
  }

  public void setStart(Date date) {
    this.start = date;
  }

  public Date end() {
    return this.end;
  }

  public void setEnd(Date date) {
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

  public boolean isClashing(CAMS.Data.Utils.DateRange range) {
    return (this.start.getTime() <= range.end.getTime()) &&
      (this.end.getTime() >= range.start.getTime());
  }

  public boolean isNotClashing(CAMS.Data.Utils.DateRange range) {
    return !((this.start.getTime() <= range.end.getTime()) &&
      (this.end.getTime() >= range.start.getTime()));
  }

  public boolean isClashing(Date date) {
    return (this.start.getTime() <= date.getTime()) &&
      (this.end.getTime() >= date.getTime());
  }

  public boolean isNotClashing(Date date) {
    return !((this.start.getTime() <= date.getTime()) &&
      (this.end.getTime() >= date.getTime()));
  }

  public void printSelf() {
    System.out.println(this);
  }
}
