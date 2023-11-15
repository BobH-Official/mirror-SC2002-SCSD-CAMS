package CAMS.Data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

abstract class RequestData<T extends CAMS.Data.RequestStatus> {
  // TODO: functions should be `protected`
  private final String id;
  private final String sender;
  private final String camp;
  private T status;
  private String message;


  protected RequestData(T status, String sender, String message, String camp) {
    String id1;
    Random random = new Random();
    try {
      id1 = new String(MessageDigest.getInstance(/*algorithm*/"SHA-256").digest(
        (sender + message + camp +
          random.nextInt(/*origin*/1000, /*bound*/10000)).getBytes(
          StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    } catch (Exception e) {
      id1 = "" + random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) +
        random.nextInt(10000000, 99999999) + /*\n*/
        random.nextInt(10000000, 99999999);
    }

    this.id = id1;
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

  protected String message() {
    return this.message;
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
    return "REQUEST:\n" +
      ("sender: " + this.sender + ";\ncamp: " + this.camp + ";\nstatus: " +
        this.status.toString() + ";\nmessage: " + this.message()).indent(4);
  }
}
