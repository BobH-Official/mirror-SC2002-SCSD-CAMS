package CAMS.Data;

enum SuggestionStatus implements CAMS.Data.RequestStatus {

  PENDING, APPROVED, REJECTED;

  void printSelf() {
    switch (this) {
      case PENDING -> {
        System.out.println("Status: PENDING");
      }
      case APPROVED -> {
        System.out.println("Status: APPROVED");
      }
      case REJECTED -> {
        System.out.println("Status: REJECTED");
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
      case APPROVED -> {
        return "Status: APPROVED";
      }
      case REJECTED -> {
        return "Status: REJECTED";
      }
      default -> {
        return "Status: UNKNOWN";
      }
    }
  }
}
