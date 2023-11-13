package CAMS.Data;

import java.util.ArrayList;
import java.util.List;


public class CampStudentList {
    private List<String> members;
    private int remainingSlots;
    private final int slotLimit;

    // Constructor with slot limit parameter
    public CampStudentList(int slotLimit) {
        this.slotLimit = slotLimit;
        this.members = new ArrayList<>();
        this.remainingSlots = this.slotLimit;
    }

    // Method to add a student to the list, throws IllegalStateException if the operation is not possible
    void addMember(String userID) throws IllegalStateException {
        if (remainingSlots <= 0 || members.contains(userID)) {
            throw new IllegalStateException("Cannot add student. No remaining slots or student is already in the list.");
        }
        members.add(userID);
        remainingSlots--;
    }

    // Method to remove a student from the list, throws IllegalStateException if the student is not found
    void withdrawMember(String userID) throws IllegalStateException {
        if (!members.remove(userID)) {
            throw new IllegalStateException("Cannot remove student. Student not found in the list.");
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
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CampStudentList:\n");
        sb.append("  slotLimit: ").append(slotLimit).append(";\n");
        sb.append("  remainingSlots: ").append(remainingSlots).append(";\n");
        sb.append("  members:\n");
        for (String member : members) {
            sb.append("    - ").append(member).append("\n");
        }
        return sb.toString();
    }

}


