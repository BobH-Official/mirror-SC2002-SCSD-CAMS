package CAMS.Data;

import CAMS.Data.Utils.PrintHelper;

import java.util.ArrayList;
import java.util.List;

class CampBlackList {
  private final List<String> members;

  CampBlackList() {
    this.members = new ArrayList<>();
  }

  String toCsv() {
    return String.join("&", members);
  }

  // Method to add a student to blacklist
  boolean addMember(String userID) {
    if (!contains(userID)) {
      members.add(userID);
      return true;
    } else {
      PrintHelper.printError("Student is already in the blacklist.");
      return false;
    }
  }

  // Method to check if a student is on blacklist
  boolean contains(String userID) {
    return members.contains(userID);
  }

  // Method to get blacklist
  List<String> get() {
    // Return a copy of the list to protect encapsulation
    return new ArrayList<>(members);
  }

  void printSelf() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder("BLACKLIST:\n");
    for (int i = 0; i < members.size(); i += 1) {
      strBuilder.append("    ").append(i + 1).append(". ")
        .append(members.get(i)).append("\n");
    }
    return strBuilder.toString().strip();
  }
}
