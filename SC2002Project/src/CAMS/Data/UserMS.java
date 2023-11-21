package CAMS.Data;

import CAMS.Data.Utils.PrintHelper;

import java.io.Console;
import java.util.Arrays;
import java.util.Objects;

public abstract class UserMS {

  // TODO: should be private, this is only for demonstration
  protected final String userID;

  public UserMS(String id) {
    userID = id;
  }


  public int changePassword() {
    System.out.println("\n## PASSWORD CHANGING ##\n");
    Console console = System.console();
    System.out.println(
      "Password should not contain empty characters in the start nor the end.");
    String passwd1 =
      Arrays.toString(console.readPassword(/*format*/"Type in your password: "))
        .strip();
    while (passwd1.isEmpty()) {
      PrintHelper.printError("Password should not be empty.");
      passwd1 = Arrays.toString(
        console.readPassword(/*format*/"Type in your password: ")).strip();
    }
    String passwd2 =
      Arrays.toString(console.readPassword(/*format*/"Type in your password: "))
        .strip();
    while (passwd2.isEmpty()) {
      PrintHelper.printError("Password should not be empty.");
      passwd2 = Arrays.toString(
        console.readPassword(/*format*/"Type in your password: ")).strip();
    }

    if (!passwd1.equals(passwd2)) {
      PrintHelper.printError("Input is not equivalent.");
      return 0;
    }

    switch (Objects.requireNonNull(Database.findUser(userID)).first()) {
      case STAFF, STUDENT -> {
        Objects.requireNonNull(Database.findUser(userID)).second()
          .setPassword(passwd1);
      }
      default -> {
        PrintHelper.printError(STR. """
        Error: user \{ userID } not found in Databse.
        Please re-login.""" );
        return 2;
      }
    }
    return 0;
  }

  public int viewInformation() {
    System.out.println("## VIEW INFORMATION ##");
    switch (Objects.requireNonNull(Database.findUser(userID)).first()) {
      case STAFF -> {
        Objects.requireNonNull(Database.findStaff(userID)).printSelf();
      }
      case STUDENT -> {
        Objects.requireNonNull(Database.findStudent(userID)).printSelf();
      }
      default -> {
        PrintHelper.printError(STR. """
        Error: user \{ userID } not found in Databse.
        Please re-login.""" );
        return 2;
      }
    }
    return 0;
  }
}
