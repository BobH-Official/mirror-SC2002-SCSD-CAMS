package CAMS.Data;

class EnquiryData extends CAMS.Data.RequestData<CAMS.Data.EnquiryStatus> {

  private String replyMessage;

  EnquiryData(String sender, String message, String camp) {
    super(CAMS.Data.EnquiryStatus.PENDING, sender, message, camp);
    this.replyMessage = "";
  }

  String replyMessage() {
    return this.replyMessage;
  }

  void reply(String msg) {
    this.setStatus(CAMS.Data.EnquiryStatus.REPLIED);
    this.replyMessage = msg;
  }

  @Override
  public String toString() {
    return (super.toString().replace("REQUEST", "ENQUIRY") +
      (this.status() == CAMS.Data.EnquiryStatus.PENDING
        ? "\n    There is no reply."
        : ";\n    reply: " + this.replyMessage)).strip();
  }
}
