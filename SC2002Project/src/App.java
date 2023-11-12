import CAMS.Data.Database;
import CAMS.Operator.NoLogin;
import CAMS.Operator.StudentOperator;
import CAMS.Operator.UserOperator;

public class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    Database database = new Database();
    UserOperator operator = new NoLogin();

    operator = new StudentOperator("EXPLSTU001");
    database.studentToString();
    operator.doOperation(database);
    database.studentToString();
  }
}

