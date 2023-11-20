package CAMS;

import CAMS.Data.LoginMS;
import CAMS.Data.Utils.CLIArgs;
import CAMS.Operator.NoLogin;
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

    CAMS.Data.Database.initialize(cliArgs);
    LoginMS.storeDB(cliArgs.storeTo());
    UserOperator operator = new NoLogin();

    int result = 0;

    while (result == 0 || result == 1) {
      while (operator instanceof NoLogin) {
        operator = LoginMS.login();
        result = 0;
      }
      while (result == 0) {
        result = operator.doOperation();
        if (result == 3) {
          System.out.println("CONGRATULATIONS! You became a Committee " +
            "Member. Automatically re-login..");
          operator = LoginMS.changeToCM(operator.id());
          result = 0;
        } else if (result == 1) {
          operator = new NoLogin();
        }
        LoginMS.storeDB(cliArgs.storeTo());
      }
    }
    LoginMS.storeDB(cliArgs.storeTo());
    System.out.println("Program exit...");
  }
}

