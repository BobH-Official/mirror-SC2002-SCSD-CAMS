package CAMS.Data;

import CAMS.Data.Utils.DateRange;

import java.util.Date;

class CampInformation {
  private final DateRange dates;
  private Date registrationClosingDate;
  private String faculty;
  private String location;
  private int totalSlots;
  private int committeeSlots;
  private String description;

  CampInformation(Date startDate, Date endDate, Date registrationClosingDate,
                  String userGroup, String location, int totalSlots,
                  int committeeSlots, String description) {
    this.dates = new DateRange(startDate, endDate);
    this.registrationClosingDate = registrationClosingDate;
    this.faculty = userGroup;
    this.location = location;
    this.totalSlots = totalSlots;
    this.committeeSlots = committeeSlots;
    this.description = description;
  }

  String toCsv() {
    return STR. "\{ String.format("%1$tY-%1$tm-%1$td", dates.start()) }," +
      STR. "\{ String.format("%1$tY-%1$tm-%1$td", dates.end()) }," +
      STR. "\{ String.format("%1$tY-%1$tm-%1$td",
        this.registrationClosingDate) }," +
      STR. "\{ this.faculty },\{ this.location },\{ this.totalSlots }," +
      STR. "\{ this.committeeSlots },\{ this.description }" ;
  }

  //Getters & Setters : campDates
  void setStartDate(Date date) {
    this.dates.setStart(date);
  }

  Date startDate() {
    return this.dates.start();
  }

  void setEndDate(Date date) {
    this.dates.setEnd(date);
  }

  Date endDate() {
    return this.dates.end();
  }

  //Getters & Setters : campRegistrationClosingDate
  void setRegistrationClosingDate(Date date) {
    this.registrationClosingDate = date;
  }

  Date registrationClosingDate() {
    return this.registrationClosingDate;
  }

  // Getters & Setters : campUserGroup
  void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  String faculty() {
    return this.faculty;
  }

  // Getters & Setters : campLocation
  void setLocation(String location) {
    this.location = location;
  }

  String location() {
    return this.location;
  }

  // Getters and Setters for campTotalSlots
  void setTotalSlots(int slots) {
    this.totalSlots = slots;
  }

  int totalSlots() {
    return this.totalSlots;
  }

  // Getters and Setters for campCommitteeSlots
  void setCommitteeSlots(int slots) {
    this.committeeSlots = slots;
  }

  int committeeSlots() {
    return this.committeeSlots;
  }

  // Getters & Setters: campDescription
  void setDescription(String description) {
    this.description = description;
  }

  String description() {
    return this.description;
  }

  // Additional methods
  void printSelf() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    return ("CAMP_INFORMATION:\n    dates: " + this.dates +
      "\n    registration closing date: " + this.registrationClosingDate +
      "\n    user group: " + this.faculty + "\n    location: " + this.location +
      "\n    attendee slots limit: " + this.totalSlots +
      "\n    committee slots limit: " + this.committeeSlots +
      "\n    description: " + this.description).strip();
  }
}
