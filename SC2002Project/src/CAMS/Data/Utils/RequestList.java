package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

class RequestList {
  private final List<String> requests;

  RequestList() {
    this.requests = new ArrayList<>();
  }

  // Method to add a request to the list
  void addCampRequest(String request) {
    requests.add(request);
  }

  // Method to get the request list
  List<String> requests() {
    return new ArrayList<>(requests);
    // Return a copy of the list to protect encapsulation
  }

  void printSelf() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder("REQUEST_LIST:\n");
    for (int i = 0; i < requests.size(); i += 1) {
      strBuilder.append("    ").append(i + 1).append(". ")
        .append(requests.get(i)).append("\n");
    }
    return strBuilder.toString().strip();
  }
}
