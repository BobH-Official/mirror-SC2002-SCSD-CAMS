package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

public class CampStudentList {
    private List<StudentData> members;
    private int remainingSlots;
    private final int slotLimit;

    // Constructor with default slot limit
    public CampStudentList() {
        this.slotLimit = 10; // Default slot limit set to 10
        this.members = new ArrayList<>();
        this.remainingSlots = slotLimit;
    }

    // Method to add a student to the list
    public void addMember(StudentData student) {
        if (remainingSlots > 0 && !members.contains(student)) {
            members.add(student);
            remainingSlots--;
        } else {
            // Handle the case when there are no remaining slots or student is already in the list
            throw new IllegalStateException("Cannot add student. No remaining slots or student is already in the list.");
        }
    }

    // Method to remove a student from the list
    public void withdrawMember(StudentData student) {
        if (members.remove(student)) {
            remainingSlots++;
        } else {
            // Handle the case when the student is not found in the list
            throw new IllegalStateException("Cannot remove student. Student not found in the list.");
        }
    }

    // Method to get the list of students
    public List<StudentData> getMemberList() {
        return new ArrayList<>(members); // Return a copy of the list to protect the encapsulation
    }

    // Method to get the number of remaining slots
    public int getRemainingSlots() {
        return remainingSlots;
    }
}