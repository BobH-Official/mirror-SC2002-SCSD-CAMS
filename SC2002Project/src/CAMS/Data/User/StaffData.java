package CAMS.Data;

import java.util.List;
import java.util.ArrayList;

class StaffData extends UserData {
    private List<String> campsUnderManagement;

    // Constructor
    StaffData(String name, String email, String faculty, List<String> campsUnderManagement) {
        super(name, email, faculty);
        this.campsUnderManagement = new ArrayList<>(campsUnderManagement);
    }

    // Methods
    boolean isinChargeOf(String campID) {
        return campsUnderManagement.contains(campID);
    }

    void addCampToManagement(String campID) {
        if (!isinChargeOf(campID)) {
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
        return (super.toString() + "Camps under management: " + campsUnderManagement.toString()).indent(4);
    }
}
