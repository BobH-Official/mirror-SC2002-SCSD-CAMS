package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

class CampBlackList {
    private List<String> members;

    public CampBlackList() {
        this.members = new ArrayList<>();
    }

    // Method to add a student to blacklist
    void addMember(String userID) throws IllegalStateException{
        if (!contains(userID)) {
            members.add(userID);
        } else {
            throw new IllegalStateException("Student is already in the blacklist.");
        }
    }

    // Method to check if a student is on blacklist
    boolean contains(String userID) {
        return members.contains(userID);
    }

    // Method to get blacklist
    List<String> getBlackList() {
        // Return a copy of the list to protect encapsulation
        return new ArrayList<>(members); 
    }

    void printSelf(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CampBlackList:\n");
        for (String member : members) {
            sb.append("  - ").append(member).append("\n");
        }
        return sb.toString();
    }
}
