package CAMS.Data;

import CAMS.Data.Utils.PrintHelper;

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
  boolean addMember(String userID) {
    if (remainingSlots <= 0) {
      PrintHelper.printError("Cannot add student. No remaining slots.");
      return false;
    }
    if (members.contains(userID)) {
      PrintHelper.printError(
        "Cannot add student. Student is already in the list.");
      return false;
    }
    members.add(userID);
    remainingSlots--;
    return true;
  }

  // Method to remove a student from the list,
  // throws `IllegalStateException` if the student is not found
  boolean withdrawMember(String userID) {
    if (!members.remove(userID)) {
      PrintHelper.printError(
        "Cannot remove student. Student not found in the list.");
      return false;
    }
    remainingSlots++;
    return true;
  }

  boolean contains(String userID) {
    return members.contains(userID);
  }

  boolean isEmpty() {
    return members.isEmpty();
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


