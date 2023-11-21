package CAMS.Data;

abstract class UserData {

  private final String faculty;
  private final String name;
  private final String email;
  private final String id;
  private String password;

  protected UserData(String userName, String email, String faculty,
                     String password) {
    this.name = userName;
    this.email = email;
    this.faculty = faculty;
    this.id = email.split("@", 2)[0].toUpperCase();
    this.password = password;
  }

  boolean isPasswordIncorrect(String passwd) {
    return !this.password.equals(passwd);
  }

  void setPassword(String password) {
    this.password = password;
  }

  String id() {
    return this.id;
  }

  String faculty() {
    return this.faculty;
  }

  String email() {
    return this.email;
  }

  String name() {
    return this.name;
  }

  String password() {
    return this.password;
  }

  String toCsv() {
    return STR. "\{ this.id },\{ this.password },\{ this.name }," +
      STR. "\{ this.email },\{ this.faculty }\n" ;
  }

  void printSelf() {
    System.out.println("STUDENT  : ");
    System.out.println("  ID     : " + this.id);
    System.out.println("  NAME : " + this.name);
    System.out.println(this);
  }

  @Override
  public String toString() {
    return STR. """
      USER:
          ID: \{ this.id }
          NAME: \{ this.name }
          FACULTY: \{ this.faculty }
          EMAIL: \{ this.email }
      """ .strip();
  }


}
