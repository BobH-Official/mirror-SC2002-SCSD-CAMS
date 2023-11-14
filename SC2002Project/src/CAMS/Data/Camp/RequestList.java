package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

class RequestList {
    private List<String> campRequestList;

    RequestList() {
        this.campRequestList = new ArrayList<>();
    }

    // Method to add a request to the list
    void addCampRequest(String request) {
        campRequestList.add(request);
    }

    // Method to get the request list
    List<String> requests() {
        return new ArrayList<>(campRequestList); 
        // Return a copy of the list to protect encapsulation
    }

    void printSelf(){
        System.out.println(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CampRequestList:\n");
        for (String request : campRequestList) {
            sb.append("  - ").append(request).append("\n");
        }
        return sb.toString().indent(4);
    }





}