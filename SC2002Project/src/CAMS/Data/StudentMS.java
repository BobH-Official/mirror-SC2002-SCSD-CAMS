package CAMS.Data;

public class StudentMS extends CAMS.Data.UserMS {
  public StudentMS(String id) {
    super(id);
  }

  public boolean addEnquiry(String id) {
    return true;
  }

  public String joinCamp() {
    return "";
  }

  public void addCamp(String camp) {
    // this is for the StudentOperation to delete camp in StudentData,
    // if StudentCampMS failed to add
  }

  public String withdrawCamp() {
    return "";
  }

  public void deleteCamp(String camp) {
    // this is for the StudentOperation to delete camp in StudentData,
    // if StudentCampMS failed to add
  }

  public String joinCampAsCM() {
    return "";
  }

  public void becomeCMOf(String camp) {
  }

  public boolean deleteEnquiry(String enquiry) {
    return true;
  }

  public void deleteSuggestion(String suggestion) {
  }
}
