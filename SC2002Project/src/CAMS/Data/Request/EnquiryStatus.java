package CAMS.Data;

enum EnquiryStatus implements CAMS.Data.RequestStatus {

  PENDING, REPLIED;

  void printSelf() {
    switch (this) {
      case PENDING -> {
        System.out.println("Status: PENDING");
      }
      case REPLIED -> {
        System.out.println("Status: REPLIED");
      }
      default -> {
        System.out.println("Status: UNKNOWN");
      }
    }
  }

  @Override
  public String toString() {
    switch (this) {
      case PENDING -> {
        return "Status: PENDING";
      }
      case REPLIED -> {
        return "Status: REPLIED";
      }
      default -> {
        return "Status: UNKNOWN";
      }
    }
  }
}
