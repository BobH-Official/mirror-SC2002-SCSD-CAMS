package CAMS.Data;

import CAMS.Data.Utils.PrintHelper;

import java.util.Date;

class CampData {
  private final String name;
  private final String staff;
  private final CAMS.Data.CampInformation information;
  private final CAMS.Data.CampStudentList attendees;
  private final CAMS.Data.CampStudentList committeeMembers;
  private final CAMS.Data.CampBlackList blacklist;
  private final CAMS.Data.RequestList enquiries;
  private final CAMS.Data.RequestList suggestions;
  private boolean visibility;
  private String description;
  private String location;

  CampData(String name, String staff, String userGroup, boolean visibility,
           String description, Date startDate, Date endDate,
           Date registrationClosingDate, String location, int campTotalSlots,
           int committeeSlots) {
    if (campTotalSlots > 10 || campTotalSlots <= 0) {
      System.out.println(
        "Camp Total Slots input is invalid, using default value 10.");
      campTotalSlots = 10;
    }
    if (committeeSlots > 40 || committeeSlots <= 0) {
      System.out.println(
        "Committee Total Slots input is invalid, using default value 40.");
      committeeSlots = 40;
    }
    this.name = name;
    this.staff = staff;
    this.visibility = visibility;


    this.information =
      new CAMS.Data.CampInformation(startDate, endDate, registrationClosingDate,
        userGroup, location, campTotalSlots, committeeSlots, description);

    // We assume that the number of camp committee is counted into total slots.
    this.attendees =
      new CAMS.Data.CampStudentList(campTotalSlots - committeeSlots);
    this.committeeMembers = new CAMS.Data.CampStudentList(committeeSlots);
    this.blacklist = new CAMS.Data.CampBlackList();
    this.enquiries = new CAMS.Data.RequestList();
    this.suggestions = new CAMS.Data.RequestList();
  }

  String toCsv() {
    return STR. "\{ this.name },\{ this.staff },\{ this.visibility }," +
      STR. "\{ this.information.toCsv() },\{ this.attendees.toCsv() }," +
      STR. "\{ this.committeeMembers.toCsv() },\{ this.blacklist.toCsv() }," +
      STR. "\{ this.enquiries.toCsv() },\{ suggestions.toCsv() }\n" ;
  }


  boolean addAttendee(String userID) {
    // Check if the user is on the blacklist.
    if (blacklist.contains(userID)) {
      PrintHelper.printError(/*msg*/
        "Cannot add attendee. User is on the blacklist.");
      return false;
    }
    // Check if the user is already a committee member.
    if (committeeMembers.contains(userID)) {
      PrintHelper.printError(
        "Cannot add attendee. User is a committee member.");
      return false;
    }
    // If checks pass, add the user as an attendee.
    return attendees.addMember(userID);
  }

  boolean addCommitteeMember(String userID) {
    // Check if the user is on the blacklist.
    if (blacklist.contains(userID)) {
      PrintHelper.printError(
        "Cannot add committee member. User is on the blacklist.");
      return false;
    }
    // Check if the user is already an attendee.
    if (attendees.contains(userID)) {
      PrintHelper.printError(
        "Cannot add committee member. User is an attendee.");
      return false;
    }
    // If checks pass, add the user as a committee member.
    return committeeMembers.addMember(userID);
  }

  boolean withdrawAttendee(String userID) {
    // Check if the user is an attendee before attempting to remove.
    if (!attendees.contains(userID)) {
      PrintHelper.printError(
        "Cannot withdraw attendee. User is not an attendee.");
      return false;
    }
    // If the check passes, remove the user from the attendee list.
    return attendees.withdrawMember(userID) && blacklist.addMember(userID);
  }

  void addEnquiry(String id) {
    enquiries.add(id);
  }

  boolean deleteEnquiry(String id) {
    return enquiries.delete(id);
  }

  void addSuggestion(String id) {
    suggestions.add(id);
  }

  boolean deleteSuggestion(String id) {
    return enquiries.delete(id);
  }

  String name() {
    return this.name;
  }

  boolean isVisible() {
    return this.visibility;
  }

  void toggleVisibility() {
    if (attendees.isEmpty() && committeeMembers.isEmpty()) {
      this.visibility = !this.visibility;
    }
    PrintHelper.printError(/*msg*/
      "There are members in the camp, cannot turn off");
  }

  String staff() {
    return this.staff;
  }

  CAMS.Data.CampInformation information() {
    return this.information;
  }

  CAMS.Data.CampStudentList attendees() {
    return this.attendees;
  }

  CAMS.Data.CampStudentList committeeMembers() {
    return committeeMembers;
  }

  CAMS.Data.CampBlackList blacklist() {
    return blacklist;
  }

  CAMS.Data.RequestList enquiries() {
    return enquiries;
  }

  CAMS.Data.RequestList suggestions() {
    return suggestions;
  }

  void printSelf() {
    System.out.println(this);
  }

  void setLocation(String location) {
    this.location = location;
}

  void setDescription(String description) {
    this.description = description;
}


  @Override
  public String toString() {
    return STR. """
      \033[1CAMP_DATA\033[1:
          name: \{ this.name }
          visibility: \{ this.visibility }
          staff: \{ this.staff }
      \{ this.information.toString().replace("CAMP_INFORMATION", "information")
      .indent(4).strip() }
      \{ this.attendees.toString().replace("CAMP_STUDENT_LIST", "attendees")
      .indent(4).strip() }
      \{ this.committeeMembers.toString()
      .replace("CAMP_STUDENT_LIST", "committeeMembers").indent(4).strip() }
      \{ this.blacklist.toString().replace("BLACKLIST", "blacklist").indent(4)
      .strip() }
      \{ this.enquiries.toString().replace("REQUEST_LIST", "enquiries")
      .indent(4).strip() }
      \{ this.suggestions.toString().replace("REQUEST_LIST", "suggestions")
      .indent(4).strip() }
      """ .indent(4).strip();
//    return ("CAMP_DATA:\n" +
//      ("name: " + this.name + "\nvisibility: " + this.visibility + "\nstaff: " +
//        this.staff + "\n" +
//        this.information.toString().replace("CAMP_INFORMATION", "information")
//          .indent(4).strip() + "\n" +
//        this.attendees.toString().replace("CAMP_STUDENT_LIST", "attendees")
//          .indent(4).strip() + "\n" + this.committeeMembers.toString()
//        .replace("CAMP_STUDENT_LIST", "committeeMembers").indent(4).strip() +
//        "\n" +
//        this.blacklist.toString().replace("BLACKLIST", "blacklist").indent(4)
//          .strip() + "\n" +
//        this.enquiries.toString().replace("REQUEST_LIST", "enquiries").indent(4)
//          .strip() + "\n" +
//        this.suggestions.toString().replace("REQUEST_LIST", "suggestions")
//          .indent(4).strip()).indent(4)).strip();
  }

}
