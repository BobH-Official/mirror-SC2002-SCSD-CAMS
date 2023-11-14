package CAMS.Data;

class SuggestionData extends CAMS.Data.RequestData<CAMS.Data.SuggestionStatus> {
  SuggestionData(String sender, String message, String camp) {
    super(CAMS.Data.SuggestionStatus.PENDING, sender, message, camp);
  }

  void approve() {
    super.setStatus(CAMS.Data.SuggestionStatus.APPROVED);
  }

  void reject() {
    super.setStatus(CAMS.Data.SuggestionStatus.REJECTED);
  }

  @Override
  public String toString() {
    return super.toString().replace("REQUEST", "SUGGESTION");
  }
}
