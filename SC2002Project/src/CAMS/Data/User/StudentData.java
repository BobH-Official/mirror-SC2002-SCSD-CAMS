package CAMS.Data;

class StudentData extends CAMS.Data.UserData {

  private final String userID;
  private String password;

  StudentData(String id, String passwd) {
    this.userID = id;
    this.password = passwd;
  }


  // TODO: functions should be `private-level private`, ie no annotation
  void setPassword(String passwd) {
    this.password = passwd;
  }

  public String toString() {
    System.out.println("STUDENT  : ");
    System.out.println("  ID     : " + this.userID);
    System.out.println("  PASSWD : " + this.password);
    return "STUDENT  : \n  ID     : " + this.userID + "\n  PASSWD : " + this.password;
  }
}
