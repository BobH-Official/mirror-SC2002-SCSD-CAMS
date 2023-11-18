package CAMS.Data;

public class StudentMS extends CAMS.Data.UserMS {
  public StudentMS(String id) {
    super(id);
  }

  public void changePassword(String password) {
    // TODO: should use getter, this is only for demonstration

    CAMS.Data.Utils.Pair<CAMS.Data.UserType, CAMS.Data.UserData> user =
      Database.findUser(userID);
    if (user != null) {
      user.second().setPassword(password);
    }
  }

  public boolean addEnquiry(String id) {
    return true;
  }

}
