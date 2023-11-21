package CAMS.Data;

import CAMS.Data.Utils.DateRangeList;

import java.util.HashSet;
import java.util.List;

class StudentData extends UserData {
  private final HashSet<String> campAttendee;
  private final HashSet<String> ownEnquiry;
  // Assuming integer IDs for enquiries
  private final DateRangeList datesOccupied;
  private final HashSet<String> ownSuggestions;
  // Assuming DataRangeList is a defined class
  private boolean isCommitteeMember;
  private String campAsCommitteeMember; // Assuming camp ID is a string
  private int pointsForGivingSuggestions;
  private int pointsForApprovedSuggestion;
  private int pointsForReplyingEnquiry;
  // Assuming integer IDs for suggestions

  StudentData(String name, String email, String faculty, String password) {
    super(name, email, faculty, password);
    this.campAttendee = new HashSet<>();
    this.ownEnquiry = new HashSet<>();
    this.datesOccupied = new DateRangeList(); // Assuming default constructor
    this.isCommitteeMember = false;
    this.campAsCommitteeMember = "";
    this.pointsForGivingSuggestions = 0;
    this.pointsForApprovedSuggestion = 0;
    this.pointsForReplyingEnquiry = 0;
    this.ownSuggestions = new HashSet<>();
  }

  boolean isCommitteeMember() {
    return isCommitteeMember;
  }

  String campCommittee() {
    // Assuming there's a class CampManager with a method findCampById
    return this.campAsCommitteeMember;
  }


  void increasePointsForGivingSuggestions() {
    this.pointsForGivingSuggestions++;
  }

  void increasePointsForApprovedSuggestions() {
    this.pointsForApprovedSuggestion++;
  }

  void increasePointsForReplyingEnquiry() {
    this.pointsForReplyingEnquiry++;
  }

  int pointsForGivingSuggestions() {
    return pointsForGivingSuggestions;
  }

  int pointsForApprovedSuggestions() {
    return pointsForApprovedSuggestion;
  }

  int pointsForReplyingEnquiry() {
    return pointsForReplyingEnquiry;
  }

  List<String> getCampAsAttendee() {
    return campAttendee.stream().toList();
  }

  String getCampAsCommitteeMember() {
    return campAsCommitteeMember;
  }

  List<String> getOwnEnquiries() {
    return ownEnquiry.stream().toList();
  }

  DateRangeList getDatesOccupied() {
    return datesOccupied;
  }

  List<String> getOwnSuggestions() {
    return ownSuggestions.stream().toList();
  }

  boolean joinAsAttendeeOf(String camp) {
    return this.campAttendee.add(camp);
  }

  void joinAsCommitteeMemberOf(String camp) {
    this.isCommitteeMember = true;
    this.campAsCommitteeMember = camp;
    this.campAttendee.remove(camp);
  }

  void withdrawFrom(String camp) {
    this.campAttendee.remove(camp);
  }

  void addEnquiry(String enquiryId) {
    this.ownEnquiry.add(enquiryId);
  }

  void removeEnquiry(String enquiryId) {
    this.ownEnquiry.remove(enquiryId);
  }

  void addSuggestion(String suggestionId) {
    this.ownSuggestions.add(suggestionId);
  }

  @Override
  String toCsv() {
    return STR. "\{ super.toCsv() },\{ this.isCommitteeMember }\n" ;
  }

  @Override
  public String toString() {
    if (this.isCommitteeMember) {
      return STR. """
      \{ super.toString() }
          Camp joined: \{ this.campAttendee.toString().replace("]", "")
        .replace("[", "") }
          Enquiries number: \{ this.ownEnquiry.size() }
          Committee Member: \{ this.isCommitteeMember }
          \{ this.isCommitteeMember ? "" : STR. "Camp Committee: \{ }" }
      """ .strip();
    } else {
      return STR. """
      \{ super.toString() }
          Committee Member: \{ this.isCommitteeMember }
          Camp Committee: \{ this.campAsCommitteeMember }
          Suggestions number: \{ this.ownSuggestions.size() }
          Point:
              Replying enquiry:     \{ this.pointsForReplyingEnquiry }
              Giving suggestion"    \{ this.pointsForGivingSuggestions }
              Approved suggestions: \{ this.pointsForApprovedSuggestion }
      """ .strip();
    }
  }
}
