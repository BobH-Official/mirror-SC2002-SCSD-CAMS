package CAMS;

import CAMS.Data.LoginMS;
import CAMS.Data.Utils.CLIArgs;
import CAMS.Operator.CommitteeMemberOperator;
import CAMS.Operator.NoLogin;
import CAMS.Operator.StudentOperator;
import CAMS.Operator.UserOperator;

import java.util.Arrays;

public class App {
  public static void main(String[] args) {
    CLIArgs cliArgs = new CLIArgs(Arrays.stream(args).toList());
    if (System.console() == null) {
      System.err.println(
        "No Console found, please run the program in a terminal.");
      System.exit(1);
    }
    System.out.println("Hello World!");

//    CAMS.Data.LoginMS loginMS = new CAMS.Data.LoginMS();
    LoginMS.storeDB(cliArgs.storeTo());
    CAMS.Data.Database.initialize();
    UserOperator operator = new NoLogin();


    int result = 0;
//    Database.studentToString();
    while (result == 0) {
      operator = new StudentOperator("STUDENT001");
      while (result == 0) {
        result = operator.doOperation();
        if (result == 3) {
          operator = new CommitteeMemberOperator("STUDENT001");
          result = 0;
        }
      }
    }
    System.out.println("Program exit...");
//    Database.studentToString();
  }
}

