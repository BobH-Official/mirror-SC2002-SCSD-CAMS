package CAMS.Data;

class EnquiryData extends CAMS.Data.RequestData<CAMS.Data.EnquiryStatus> {

  private String reply;

  EnquiryData(String sender, String message, String camp) {
    super(CAMS.Data.EnquiryStatus.PENDING, sender, message, camp);
    this.reply = "";
  }

  String reply() {
    return this.reply;
  }

  void setReply(String reply) {
    this.setStatus(CAMS.Data.EnquiryStatus.REPLIED);
    this.reply = reply;
  }

  @Override
  public String toString() {
    return "Suggestion:\n    sender: " + this.sender() + ";\n    camp: " +
      this.camp() + ";\n    status: " + this.status().toString() +
      ";\n    message: " + this.message() +
      (this.status() == CAMS.Data.EnquiryStatus.PENDING
        ? "\n    There is no reply." : ";\n    reply: " + this.reply);
  }
}
