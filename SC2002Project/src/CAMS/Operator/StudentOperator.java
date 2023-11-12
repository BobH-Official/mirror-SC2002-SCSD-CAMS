package CAMS.Operator;

import CAMS.Data.Database;
import CAMS.Data.StudentMS;

public class StudentOperator extends UserOperator {

  private final StudentMS studentMS;

  public StudentOperator(String id) {
    studentMS = new StudentMS(id);

  }

  @Override
  public boolean doOperation(Database database) {
    int choice = 0;
    switch (choice) {

      case 0 -> {
        studentMS.changePassword(database, "new pass");
        return true;
      }

      case 1 -> {
        return true;
      }

      default -> {
        return false;
      }
    }
  }
}
