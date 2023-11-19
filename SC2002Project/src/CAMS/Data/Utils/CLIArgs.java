package CAMS.Data.Utils;

import java.util.List;

public class CLIArgs {
  private final String staff;
  private final String camp;
  private final String enquiry;
  private final String suggestion;
  private final String db;
  private final String student;
  private final String storeTo;

  public CLIArgs(List<String> args) {
    if (args.contains("--help") || args.contains("-h")) {
      System.out.println(STR."""
        This is Camp Management System.
        To use this app, you must input student data and staff data.
        Arguments:
            --student, -s        input student csv file
            --staff, -t          input staff csv file
            --camp, -c           input camp csv file
            --enquiry, -e        input enquiry csv file
            --suggestion, -g     input suggestion csv file
            --db, -d             input database file
            --output, -o         the path of output database file
            --help, -h           print this message
        If arguments contains "--help" or "-h", program will always print this message,
        The program will read the last input arguments.
        ie, when input "-s student1.csv -s student2.csv",
        program will recognize "student2.csv"
        """.strip());
    }
    int type = 0;
    String student1 = "";
    String staff1 = "";
    String camp1 = "";
    String enquiry1 = "";
    String suggestion1 = "";
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
              enquiry1 = arg;
            }
            case 5 -> {
              suggestion1 = arg;
            }
            case 6 -> {
              db1 = arg;
            }
            case 7 -> {
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
        case "--enquiry", "-e" -> {
          type = 4;
        }
        case "--suggestion", "-g" -> {
          type = 5;
        }
        case "--db", "-d" -> {
          type = 6;
        }
        case "--output", "-o" -> {
          type = 7;
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
    this.enquiry = enquiry1;
    this.suggestion = suggestion1;
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

  public String enquiry() {
    return enquiry;
  }

  public String suggestion() {
    return suggestion;
  }

  public String db() {
    return db;
  }

  public String storeTo() {
    return storeTo;
  }
}
