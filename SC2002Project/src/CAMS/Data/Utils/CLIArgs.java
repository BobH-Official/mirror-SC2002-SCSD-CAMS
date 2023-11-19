package CAMS.Data.Utils;

import java.util.List;

public class CLIArgs {
  private final String staff;
  private final String camp;
  private final String request;
  private final String db;
  private final String student;
  private final String storeTo;

  public CLIArgs(List<String> args) {
    int type = 0;
    String student1 = "";
    String staff1 = "";
    String camp1 = "";
    String request1 = "";
    String db1 = "";
    String store1 = "data.camsdb";
    for (String arg : args) {
      if (type >= 1) {
        if (arg.contains(".csv") || arg.contains(".camsdb") ||
          arg.contains(".cams") || arg.contains(".db")) {
          switch (type) {
            case 1 -> {
              student1 = arg;
            }
            case 2 -> {
              staff1 = arg;
            }
            case 3 -> {
              camp1 = arg;
            }
            case 4 -> {
              request1 = arg;
            }
            case 5 -> {
              db1 = arg;
            }
            case 6 -> {
              store1 = arg;
            }
            default -> {
              System.err.println("Cannot parse argument.");
              System.exit(1);
            }
          }
          type = 0;
          continue;
        } else {
          System.err.println("Cannot parse argument.");
          System.exit(1);
        }
      }
      switch (arg) {
        case "--student", "-s" -> {
          type = 1;
        }
        case "--staff", "-t" -> {
          type = 2;
        }
        case "--camp", "-c" -> {
          type = 3;
        }
        case "--request", "-q" -> {
          type = 4;
        }
        case "--db", "-d" -> {
          type = 5;
        }
        case "--output", "-o" -> {
          type = 6;
        }
        default -> {
          System.err.println("Cannot parse argument.");
          System.exit(1);
        }
      }
    }
    this.student = student1;
    this.staff = staff1;
    this.camp = camp1;
    this.request = request1;
    this.db = db1;
    this.storeTo = store1;
  }

  public String student() {
    return student;
  }

  public String staff() {
    return staff;
  }

  public String camp() {
    return camp;
  }

  public String request() {
    return request;
  }

  public String db() {
    return db;
  }

  public String storeTo() {
    return storeTo;
  }
}
