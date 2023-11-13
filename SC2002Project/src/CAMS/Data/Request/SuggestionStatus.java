package CAMS.Data;

enum SuggestionStatus implements CAMS.Data.RequestStatus {

  PENDING, APPROVED, REJECTED;

  void printSelf() {
    System.out.println("Suggestion Status: " + this);
  }

  @Override
  public String toString() {
    switch (this) {
      case PENDING -> {
        return "PENDING";
      }
      case APPROVED -> {
        return "APPROVED";
      }
      case REJECTED -> {
        return "REJECTED";
      }
      default -> {
        return "UNKNOWN";
      }
    }
  }
}
