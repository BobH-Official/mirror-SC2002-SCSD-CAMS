package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

public class CampBlackList {
    private List<StudentData> members;

    public CampBlackList() {
        this.members = new ArrayList<>();
    }

    // Method to add a student to blacklist
    public void addMember(StudentData student) {
        if (!isBlackList(student)) {
            members.add(student);
        } else {
            throw new IllegalStateException("Student is already in the blacklist.");
        }
    }

    // Method to check if a student is on blacklist
    public boolean isInBlackList(StudentData student) {
        return members.contains(student);
    }

    // Method to get blacklist
    public List<StudentData> getBlackList() {
        // Return a copy of the list to protect encapsulation
        return new ArrayList<>(members); 
    }
