package CAMS.Data;

import CAMS.Data.MS.UserMS;

public class StudentMS extends UserMS {
  public StudentMS(String id) {
    super(id);
  }

  public void changePassword(String password) {
    // TODO: should use getter, this is only for demonstration
    CAMS.Data.UserData user = Database.findUser(userID).second();
    if (user != null) {
      ((CAMS.Data.UserData) user).setPassword(password);
    }
  }
}
