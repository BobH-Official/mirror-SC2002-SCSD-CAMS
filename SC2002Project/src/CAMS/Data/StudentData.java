package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

class StudentData extends CAMS.Data.UserData {
  private final List<String> campAttendee;
  private final List<String> ownEnquiry; // Assuming integer IDs for enquiries
  private final CAMS.Data.DateRangeList datesOccupied;
  // Assuming DataRangeList is a defined class
  private final boolean isCommitteeMember;
  private final String campAsCommitteeMember; // Assuming camp ID is a string
  private final List<String> ownSuggestions;
  private int pointsForGivingSuggestions;
  private int pointsForApprovedSuggestion;
  private int pointsForReplyingEnquiry;
  // Assuming integer IDs for suggestions

  StudentData(String name, String email, String faculty, String password) {
    super(name, email, faculty, password);
    this.campAttendee = new ArrayList<>();
    this.ownEnquiry = new ArrayList<>();
    this.datesOccupied =
      new CAMS.Data.DateRangeList(); // Assuming default constructor
    this.isCommitteeMember = false;
    this.campAsCommitteeMember = "";
    this.pointsForGivingSuggestions = 0;
    this.pointsForApprovedSuggestion = 0;
    this.pointsForReplyingEnquiry = 0;
    this.ownSuggestions = new ArrayList<>();
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
    return campAttendee;
  }

  List<String> getOwnEnquiries() {
    return ownEnquiry;
  }

  CAMS.Data.DateRangeList getDatesOccupied() {
    return datesOccupied;
  }

  List<String> getOwnSuggestions() {
    return ownSuggestions;
  }

  void joinAsAttendeeOf(String camp) {
    this.campAttendee.add(camp);
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
  public String toString() {
    if (this.isCommitteeMember) {
      return STR. """
      \{ super.toString() }
          Camp joined: \{ this.campAttendee.toString().replace("]", "")
        .replace("[", "") }
          Committee Member: \{ this.isCommitteeMember }
          \{ this.isCommitteeMember ? "" : STR. "Camp Committee: \{ }" }
      """ .strip();
    } else {
      return STR. """
      \{ super.toString() }
          Committee Member: \{ this.isCommitteeMember }
      """ .strip();
    }

  }
}
