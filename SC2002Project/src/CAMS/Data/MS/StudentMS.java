package CAMS.Data;

public class StudentMS {
  private final String userID;

  public StudentMS(String id) {
    userID = id;
  }


  public void changePassword(Database database, String password) {
    database.findStudent(this.userID).setPassword(password);
  }
}
