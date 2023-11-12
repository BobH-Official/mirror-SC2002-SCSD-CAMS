package CAMS.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
  static private  List<CAMS.Data.StudentData> students= new ArrayList<>();
  static private  HashMap<String, CAMS.Data.Pair<CAMS.Data.UserType, Integer>> userMap= new HashMap<>();


  static public  void initialize() {
    userMap = new HashMap<>();
    students = new ArrayList<>();
    readDatabaseFromCSV();
  }

  static void readDatabaseFromCSV() {
    // TODO: this code below is only for demonstration
    createStudent("EXPLSTU001", "ORIginalPass1111");
    createStudent("EXPLSTU002", "ORIginalPass2222");
  }

  static void createStudent(String userID, String password) {
    students.addLast(new CAMS.Data.StudentData(userID, password));
    userMap.put(userID,
      new CAMS.Data.Pair<>(CAMS.Data.UserType.STUDENT,
        students.size() - 1));
  }

  static CAMS.Data.StudentData findStudent(String userID) {
    return students.get(userMap.get(userID).getSecond());
  }

  static public String studentToString() {
    String str = "";
    for (CAMS.Data.StudentData student : students) {
      str = str.concat(student.toString() + "\n");
    }
    return str;
  }
}
