package CAMS.Data;

import java.util.Date;

class CampData {
  private final String name;
  private boolean visibility;
  private final String staff;
  private final CAMS.Data.CampInformation information;
  private final CAMS.Data.CampStudentList attendees;
  private final CAMS.Data.CampStudentList committeeMembers;
  private final CAMS.Data.CampBlackList blacklist;
  private final CAMS.Data.RequestList enquiries;
  private final CAMS.Data.RequestList suggestions;

  CampData(String name, String staff, String userGroup, boolean visibility,
           String description, Date startDate, Date endDate,
           Date registrationClosingDate, String location, int campTotalSlots,
           int committeeSlots) {
    this.name = name;
    this.staff = staff;
    this.visibility = visibility;


    this.information =
      new CAMS.Data.CampInformation(startDate, endDate, registrationClosingDate,
        userGroup, location, campTotalSlots, committeeSlots, description);

    this.attendees = new CAMS.Data.CampStudentList(campTotalSlots);
    this.committeeMembers = new CAMS.Data.CampStudentList(committeeSlots);
    this.blacklist = new CAMS.Data.CampBlackList();
    this.enquiries = new CAMS.Data.RequestList();
    this.suggestions = new CAMS.Data.RequestList();
  }


  void addAttendee(String userID) throws IllegalStateException {
    // Check if the user is on the blacklist.
    if (blacklist.contains(userID)) {
      throw new IllegalStateException(
        "Cannot add attendee. User is on the blacklist.");
    }
    // Check if the user is already a committee member.
    if (committeeMembers.contains(userID)) {
      throw new IllegalStateException(
        "Cannot add attendee. User is a committee member.");
    }
    // If checks pass, add the user as an attendee.
    attendees.addMember(userID);
  }

  void addCommitteeMember(String userID) throws IllegalStateException {
    // Check if the user is on the blacklist.
    if (blacklist.contains(userID)) {
      throw new IllegalStateException(
        "Cannot add committee member. User is on the blacklist.");
    }
    // Check if the user is already an attendee.
    if (attendees.contains(userID)) {
      throw new IllegalStateException(
        "Cannot add committee member. User is an attendee.");
    }
    // If checks pass, add the user as a committee member.
    committeeMembers.addMember(userID);
  }

  void withdrawAttendee(String userID) throws IllegalStateException {
    // Check if the user is an attendee before attempting to remove.
    if (!attendees.contains(userID)) {
      throw new IllegalStateException(
        "Cannot withdraw attendee. User is not an attendee.");
    }
    // If the check passes, remove the user from the attendee list.
    attendees.withdrawMember(userID);
    blacklist.addMember(userID);
  }

  void addEnquiry(String enquiry) {
    enquiries.addCampRequest(enquiry);
  }

  void addSuggestion(String suggestion) {
    suggestions.addCampRequest(suggestion);
  }

  String name() {
    return this.name;
  }

  boolean isVisible() {
    return this.visibility;
  }

  void toggleVisibility() {
    this.visibility = !this.visibility;
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

  @Override /*target*//*replacement*/ public String toString() {
    return ("CAMP_DATA:\n" +
      ("name: " + this.name + "\nvisibility: " + this.visibility + "\nstaff: " +
        this.staff + "\n" + this.information.toString()
        .replace(/*target*/"CAMP_INFORMATION", /*replacement*/"information")
        .indent(4).strip() + "\n" + this.attendees.toString()
        .replace(/*target*/"CAMP_STUDENT_LIST", /*replacement*/"attendees")
        .indent(4).strip() + "\n" + this.committeeMembers.toString()
        .replace(/*target*/"CAMP_STUDENT_LIST", /*replacement*/
          "committeeMembers").indent(4).strip() + "\n" +
        this.blacklist.toString().replace(/*target*/
          "BLACKLIST", /*replacement*/"blacklist").indent(4).strip() + "\n" +
        this.enquiries.toString()
          .replace(/*target*/"REQUEST_LIST", /*replacement*/
            "enquiries").indent(4).strip() + "\n" + this.suggestions.toString()
        .replace(/*target*/"REQUEST_LIST", /*replacement*/"suggestions")
        .indent(4).strip()).indent(4)).strip();
  }
}
