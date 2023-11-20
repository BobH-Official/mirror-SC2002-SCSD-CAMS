package CAMS.Data;

public class StudentCampMS {
  private final String userID;

  public StudentCampMS(String id) {
    this.userID = id;
  }

  public boolean addEnquiry(String id) {
    String camp = Database.findEnquiry(id).camp();
    Database.findCamp(camp).addEnquiry(id);
    return true;
  }

  public boolean deleteEnquiry(String id) {
    if (Database.facultyOf(id) == null) {
      return false;
    }
    String camp = Database.facultyOf(id);
    return Database.findCamp(camp).deleteEnquiry(id);
  }

  public boolean addStudent(String camp) {
    return false;
  }
}
