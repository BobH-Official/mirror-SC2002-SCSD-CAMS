package CAMS.Data;

import CAMS.Data.Utils.Pair;
import CAMS.Data.Utils.PrintHelper;
import CAMS.Operator.*;

import java.io.Console;
import java.util.Arrays;

public class LoginMS {
  public static void storeDB(String path) {
    Database.storeDB(path);
  }

  public static UserOperator login() {
    System.out.println("\n## LOGIN ##\n");
    Console console = System.console();
    System.out.println(
      "Login to the system using user ID(the part before @ in email).");
    String username = console.readLine("User ID: ").strip();
    while (username.isEmpty()) {
      PrintHelper.printError("User ID should not be empty.");
      username = console.readLine("User ID: ").strip();
    }
    String passwd = console.readLine("Password: ").strip();
    while (passwd.isEmpty()) {
      PrintHelper.printError("Password should not be empty.");
      passwd = Arrays.toString(console.readPassword("Password: ")).strip();
    }

    if (!Database.isInDatabase(username)) {
      PrintHelper.printError("User ID NOT Found");
      return new NoLogin();
    }

    Pair<CAMS.Data.UserType, UserData> userdata = Database.findUser(username);
    assert userdata != null;
    switch (userdata.first()) {
      case STUDENT -> {
        if (!userdata.second().isPasswordCorrect(passwd)) {
          PrintHelper.printError("Password incorrect.");
          return new NoLogin();
        }
        if (((StudentData) userdata.second()).isCommitteeMember()) {
          return new CommitteeMemberOperator(userdata.second().id(),
            managerCampMS);
        }
        return new StudentOperator(userdata.second().id());
      }
      case STAFF -> {
        if (!userdata.second().isPasswordCorrect(passwd)) {
          PrintHelper.printError("Password incorrect.");
          return new NoLogin();
        }
        return new StaffOperator(userdata.second().id());
      }
      case NOT_FOUND -> {
        PrintHelper.printError("User ID NOT Found");
        return new NoLogin();
      }
    }
    return new NoLogin();
  }

  public static UserOperator changeToCM(String id) {
    if (!Database.isInDatabase(id)) {
      return new StudentOperator(id);
    }
    StudentData student = Database.findStudent(id);
    assert student != null;
    if (!student.isCommitteeMember()) {
      return new StudentOperator(id);
    }
    return new CommitteeMemberOperator(id, managerCampMS);
  }
}
