package CAMS.Operator;

public abstract class UserOperator {

  private final String id;

  public UserOperator(String id) {
    this.id = id;
  }

  public int doOperation() {
    return 2;
  }

  String id() {
    return this.id;
  }
}
