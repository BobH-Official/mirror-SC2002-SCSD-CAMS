package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

class StaffData extends CAMS.Data.UserData {
  private final List<String> campsUnderManagement;

  // Constructor
  StaffData(String name, String email, String faculty, String password,
            List<String> campsUnderManagement) {
    super(name, email, faculty, password);
    this.campsUnderManagement = new ArrayList<>(campsUnderManagement);
  }

  // Methods
  boolean isInChargeOf(String campID) {
    return campsUnderManagement.contains(campID);
  }

  void addCampToManagement(String campID) {
    if (!campsUnderManagement.contains(campID)) {
      campsUnderManagement.add(campID);
    }
  }

  void removeCampFromManagement(String campID) {
    campsUnderManagement.remove(campID);
  }

  // Getters
  List<String> getCampsUnderManagement() {
    return new ArrayList<>(campsUnderManagement); // Return a copy of the list
  }

  void printSelf() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    return (super.toString() + "Camps under management: " +
      campsUnderManagement.toString()).indent(4);
  }
}
