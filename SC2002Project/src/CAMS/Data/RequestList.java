package CAMS.Data;

import java.util.ArrayList;
import java.util.List;

class RequestList {
  private final List<String> requests;

  RequestList() {
    this.requests = new ArrayList<>();
  }

  String toCsv() {
    return String.join("&", requests);
  }

  // Method to add a request to the list
  void add(String request) {
    requests.add(request);
  }

  boolean delete(String request) {
    return requests.remove(request);
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
