package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

class CampRequestList {
    private List<String> campRequestList;

    public CampRequestList() {
        this.campRequestList = new ArrayList<>();
    }

    // Method to add a request to the list
    void addCampRequest(String request) {
        campRequestList.add(request);
    }

    // Method to get the request list
    List<String> getCampRequestList() {
        return new ArrayList<>(campRequestList); 
        // Return a copy of the list to protect encapsulation
    }

    void printSelf(){
        System.out.println(this.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CampRequestList:\n");
        for (String request : campRequestList) {
            sb.append("  - ").append(request).append("\n");
        }
        return sb.toString();
    }





}