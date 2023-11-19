package CAMS.Data;

import java.util.ArrayList;
import java.util.List;


public class CampStudentList {
  private final List<String> members;
  private final int slotLimit;
  private int remainingSlots;

  // Constructor with slot limit parameter
  CampStudentList(int slotLimit) {
    this.slotLimit = slotLimit;
    this.members = new ArrayList<>();
    this.remainingSlots = this.slotLimit;
  }

  String toCsv() {
    return String.join("&", members);
  }

  // Method to add a student to the list, throws IllegalStateException if the operation is not possible
  void addMember(String userID) throws IllegalStateException {
    if (remainingSlots <= 0) {
      throw new IllegalStateException(
        "Cannot add student. No remaining slots.");
    }
    if (members.contains(userID)) {
      throw new IllegalStateException(
        "Cannot add student. Student is already in the list.");
    }
    members.add(userID);
    remainingSlots--;
  }

  // Method to remove a student from the list,
  // throws `IllegalStateException` if the student is not found
  void withdrawMember(String userID) throws IllegalStateException {
    if (!members.remove(userID)) {
      throw new IllegalStateException(
        "Cannot remove student. Student not found in the list.");
    }
    remainingSlots++;
  }

  boolean contains(String userID) {
    return members.contains(userID);
  }

  // Method to get the list of students
  List<String> getMemberList() {
    return new ArrayList<>(members);
  }

  // Method to get the number of remaining slots
  int getRemainingSlots() {
    return remainingSlots;
  }

  void printSelf() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder("CAMP_STUDENT_LIST:\n");
    strBuilder.append("    slotLimit: ").append(slotLimit).append("\n");
    strBuilder.append("    remainingSlots: ").append(remainingSlots)
      .append("\n");
    strBuilder.append("    members:\n");
    for (int i = 0; i < members.size(); i += 1) {
      strBuilder.append("        ").append(i + 1).append(". ")
        .append(members.get(i)).append("\n");
    }
    return strBuilder.toString().strip();
  }
}


