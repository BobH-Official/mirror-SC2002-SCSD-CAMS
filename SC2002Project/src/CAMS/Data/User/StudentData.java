package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

public class StudentData extends CAMS.Data.UserData {
  private List<String> campAttendee;
  private List<String> ownEnquiry; // Assuming integer IDs for enquiries
  private CAMS.Data.DateRangeList datesOccupied; // Assuming DataRangeList is a defined class
  private boolean isCommitteeMember;
  private String campAsCommitteeMember; // Assuming camp ID is a string
  private int pointsForGivingSuggestions;
  private int pointsForApprovedSuggestion;
  private int pointsForReplyingEnquiry;
  private List<String> ownSuggestions; // Assuming integer IDs for suggestions

  StudentData(String name, String email, String faculty, String password) {
    super(name, email, faculty, password);
    this.campAttendee = new ArrayList<>();
    this.ownEnquiry = new ArrayList<>();
    this.datesOccupied = new CAMS.Data.DateRangeList(); // Assuming default constructor
    this.isCommitteeMember = false;
    this.campAsCommitteeMember = "";
    this.pointsForGivingSuggestions = 0;
    this.pointsForApprovedSuggestion = 0;
    this.pointsForReplyingEnquiry = 0;
    this.ownSuggestions = new ArrayList<>();
  }

  public boolean isCommitteeMember() {
    return isCommitteeMember;
  }

  public String inCampCommittee() {
    // Assuming there's a class CampManager with a method findCampById
    return this.campAsCommitteeMember;
  }


  public void increasePointsForGivingSuggestions() {
    this.pointsForGivingSuggestions++;
  }

  public void increasePointsForApprovedSuggestions() {
    this.pointsForApprovedSuggestion++;
  }

  public void increasePointsForReplyingEnquiry() {
    this.pointsForReplyingEnquiry++;
  }

  public int getPointsForGivingSuggestions() {
    return pointsForGivingSuggestions;
  }

  public int getPointsForApprovedSuggestions() {
    return pointsForApprovedSuggestion;
  }

  public int getPointsForReplyingEnquiry() {
    return pointsForReplyingEnquiry;
  }

  public List<CampData> getCampAsAttendee() {
    return campAttendee;
  }

  public List<String> getOwnEnquiries() {
    return ownEnquiry;
  }

  public CAMS.Data.DateRangeList getDatesOccupied() {
    return datesOccupied;
  }

  public List<String> getOwnSuggestions() {
    return ownSuggestions;
  }

  public void joinAsAttendeeOf(CampData camp) {
    this.campAttendee.add(camp);
  }

  public void withdrawFrom(CampData camp) {
    this.campAttendee.remove(camp);
  }

  public void addEnquiry(int enquiryId) {
    this.ownEnquiry.add(enquiryId);
  }

  public void addSuggestion(int suggestionId) {
    this.ownSuggestions.add(suggestionId);
  }


  @Override
  public String toString() {
    return (super.toString() + "Committee Member: " + isCommitteeMember).indent(4);
  }
}
