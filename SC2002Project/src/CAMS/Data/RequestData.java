package CAMS.Data;

import CAMS.Data.Utils.HashingHelper;

import java.util.Random;

abstract class RequestData<T extends CAMS.Data.RequestStatus> {
  // TODO: functions should be `protected`
  private final String id;
  private final String sender;
  private final String camp;
  private T status;
  private String message;


  protected RequestData(T status, String sender, String message, String camp) {
    Random random = new Random();

    this.id = HashingHelper.sha256(
      (sender + message + camp + random.nextInt(1000, 10000)));
    this.sender = sender;
    this.status = status;
    this.message = message;
    this.camp = camp;
  }

  protected String id() {
    return this.id;
  }

  protected String sender() {
    return this.sender;
  }

  protected String camp() {
    return this.camp;
  }

  protected void setMessage(String msg) {
    this.message = msg;
  }

  protected T status() {
    return this.status;
  }

  protected void setStatus(T status) {
    this.status = status;
  }

  protected void printSelf() {
    System.out.print(this);
  }

  @Override
  public String toString() {
    return STR. """
      REQUEST:
          sender: \{ this.sender }
          camp: \{ this.camp }
          status: \{ this.status.toString() }
          message: \{ this.message() }
      """ .strip();
//    return "REQUEST:\n" +
//      ("sender: " + this.sender + ";\ncamp: " + this.camp + ";\nstatus: " +
//        this.status.toString() + ";\nmessage: " + this.message()).indent(4);
  }

  protected String message() {
    return this.message;
  }
}
