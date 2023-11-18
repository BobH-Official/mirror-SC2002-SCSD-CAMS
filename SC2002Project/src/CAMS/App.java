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

//    Database.studentToString();
    operator.doOperation();
//    Database.studentToString();
  }
}

