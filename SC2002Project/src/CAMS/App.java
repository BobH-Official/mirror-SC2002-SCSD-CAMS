package CAMS;

import CAMS.Data.LoginMS;
import CAMS.Data.Utils.CLIArgs;
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

