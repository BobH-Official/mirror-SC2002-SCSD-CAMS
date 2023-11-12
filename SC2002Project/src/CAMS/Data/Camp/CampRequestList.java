package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

public class CampRequestList {
    private List<RequestData> campRequestList;

    public CampRequestList() {
        this.campRequestList = new ArrayList<>();
    }

    // Method to add a request to the list
    public void addCampRequest(RequestData request) {
        campRequestList.add(request);
    }

    // Method to get the request list
    public List<RequestData> getCampRequestList() {
        return new ArrayList<>(campRequestList); // Return a copy of the list to protect encapsulation
    }
