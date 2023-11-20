package CAMS.Data;

import java.io.Console;
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
    String passwd1 = console.readLine("Type in your password: ").strip();
    while (passwd1.isEmpty()) {
      System.err.println("Password should not be empty.");
      passwd1 = console.readLine("Type in your password: ").strip();
    }
    String passwd2 = console.readLine("Type in password, again: ").strip();
    while (passwd2.isEmpty()) {
      System.err.println("Password should not be empty.");
      passwd2 = console.readLine("Type in password, again: ").strip();
    }

    if (!passwd1.equals(passwd2)) {
      System.err.println("Input is not equivalent.");
      return 0;
    }

    switch (Objects.requireNonNull(Database.findUser(userID)).first()) {
      case STAFF, STUDENT -> {
        Objects.requireNonNull(Database.findUser(userID)).second()
          .setPassword(passwd1);
      }
      default -> {
        System.err.println(STR. """
        Error: user \{ userID } not found in Databse.
        Please re-login.""" );
        return 2;
      }
    }
    return 0;
  }

  public int viewInformation() {
    switch (Objects.requireNonNull(Database.findUser(userID)).first()) {
      case STAFF -> {
        Objects.requireNonNull(Database.findStaff(userID)).printSelf();

      }
      case STUDENT -> {
        Objects.requireNonNull(Database.findStudent(userID)).printSelf();
      }
      default -> {
        System.err.println(STR. """
        Error: user \{ userID } not found in Databse.
        Please re-login.""" );
        return 2;
      }
    }
    return 0;
  }
}
