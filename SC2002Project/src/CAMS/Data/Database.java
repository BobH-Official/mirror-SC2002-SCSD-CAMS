package CAMS.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
  private final List<CAMS.Data.StudentData> students;
  private final HashMap<String, CAMS.Data.Pair<CAMS.Data.UserType, Integer>> userMap;

  public Database() {
    this.userMap = new HashMap<>();
    this.students = new ArrayList<>();
    this.readDatabaseFromCSV();
  }

  void readDatabaseFromCSV() {
    // TODO: this code below is only for demonstration
    this.createStudent("EXPLSTU001", "ORIginalPass1111");
    this.createStudent("EXPLSTU002", "ORIginalPass2222");
  }

  void createStudent(String userID, String password) {
    this.students.addLast(new CAMS.Data.StudentData(userID, password));
    this.userMap.put(userID,
      new CAMS.Data.Pair<>(CAMS.Data.UserType.STUDENT,
        this.students.size() - 1));
  }

  CAMS.Data.StudentData findStudent(String userID) {
    return this.students.get(this.userMap.get(userID).getSecond());
  }

  public String studentToString() {
    String str = "";
    for (CAMS.Data.StudentData student : students) {
      str = str.concat(student.toString() + "\n");
    }
    return str;
  }
}
