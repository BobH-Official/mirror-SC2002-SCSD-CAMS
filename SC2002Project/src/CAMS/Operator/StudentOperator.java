package CAMS.Operator;

import CAMS.Data.Database;
import CAMS.Data.StudentMS;

public class StudentOperator extends UserOperator {

  private final StudentMS studentMS;

  public StudentOperator(String id) {

    studentMS = new StudentMS(id);

  }

  @Override
  public boolean doOperation() {
    int choice = 0;
    switch (choice) {

      case 0 -> {
        studentMS.changePassword( "new pass");
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
