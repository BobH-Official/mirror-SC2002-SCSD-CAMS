package CAMS.Operator;

public abstract class UserOperator {

  private final String id;

  public UserOperator(String id) {
    this.id = id;
  }

  public boolean doOperation() {
    return false;
  }

  String id() {
    return this.id;
  }
}
