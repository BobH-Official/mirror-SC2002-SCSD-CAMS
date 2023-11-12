package CAMS.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
  static private List<CAMS.Data.StudentData> students = new ArrayList<>();
  static private List<CAMS.Data.StudentData> staffs = new ArrayList<>();
  static private HashMap<String, CAMS.Data.Pair<CAMS.Data.UserType, Integer>> userMap = new HashMap<>();


  static public void initialize() {
    userMap = new HashMap<>();
    students = new ArrayList<>();
    staffs = new ArrayList<>();
    readDatabaseFromCSV();
  }

  static void readDatabaseFromCSV() {
    // TODO: this code below is only for demonstration
    createStudent("EXPLSTU001", "ORIginalPass1111");
    createStudent("EXPLSTU002", "ORIginalPass2222");
  }

  // TODO: this code below is only for demonstration
  static void createStudent(String userID, String password) {
    students.addLast(new CAMS.Data.StudentData(userID, password));
    userMap.put(userID,
      new CAMS.Data.Pair<>(CAMS.Data.UserType.STUDENT,
        students.size() - 1));
  }

  static CAMS.Data.UserData find(String userID) {
    CAMS.Data.Pair<CAMS.Data.UserType, Integer> result = userMap.get(userID);

    switch (result.first()) {
      case CAMS.Data.UserType.STUDENT -> {
        return students.get(userMap.get(userID).second());
      }
      case CAMS.Data.UserType.STAFF -> {
        return staffs.get(userMap.get(userID).second());
      }
    }

    return null;
  }

  static public String studentToString() {
    String str = "";
    for (CAMS.Data.StudentData student : students) {
      str = str.concat(student.toString() + "\n");
    }
    return str;
  }
}
