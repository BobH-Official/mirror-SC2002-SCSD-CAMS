package CAMS.Data;

class StudentData extends CAMS.Data.UserData {

  private final String userID;
  private String password;

  StudentData(String id, String passwd) {
    this.userID = id;
    this.password = passwd;
  }


  // TODO: functions should be `package-level private`, ie no annotation
  void setPassword(String passwd) {
    this.password = passwd;
  }

  void printSelf() {
    System.out.println("STUDENT  : ");
    System.out.println("  ID     : " + this.userID);
    System.out.println("  PASSWD : " + this.password);
  }
  public String toString() {
//    printSelf()
    return "STUDENT  : \n  ID     : " + this.userID + "\n  PASSWD : " + this.password;
  }
}
