package CAMS;

import CAMS.Operator.NoLogin;
import CAMS.Operator.StudentOperator;
import CAMS.Operator.UserOperator;

public class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    CAMS.Data.Database.initialize();
    UserOperator operator = new NoLogin();

    operator = new StudentOperator("STUDENT001");

    boolean result = true;
//    Database.studentToString();
    while (result) {
      result = operator.doOperation();
    }
    System.out.println("Program exit...");
//    Database.studentToString();
  }
}

