package CAMS.Data;

enum EnquiryStatus implements CAMS.Data.RequestStatus {

  PENDING, REPLIED;

  void printSelf() {
    System.out.println("Enquiry Status: " + this);
  }

  @Override
  public String toString() {
    switch (this) {
      case PENDING -> {
        return "PENDING";
      }
      case REPLIED -> {
        return "REPLIED";
      }
      default -> {
        return "UNKNOWN";
      }
    }
  }
}
