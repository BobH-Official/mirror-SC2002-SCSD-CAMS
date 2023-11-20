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
        \033[47m\033[1m\033[30mCamp Management System\033[0m
        To use this app, you must input student data and staff data.

        \033[1mArguments\033[0m:
            --student,    -s      input student csv file
            --staff,      -t      input staff csv file
            --camp,       -c      input camp csv file
            --enquiry,    -e      input enquiry csv file
            --suggestion, -g      input suggestion csv file
            --db,         -d      input database file
            --output,     -o      the path of output database file
                                  (default: ./data.camsdb)
            --help, -h            print this message

        If arguments contains "--help" or "-h", program will always print this message.
        The program will read the last input arguments. ie, when input "-s student1.csv\s
        -s student2.csv", program will recognize "student2.csv"
        """.strip());
      System.exit(0);
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
              PrintHelper.printError("Cannot parse argument.");
              System.exit(1);
            }
          }
          type = 0;
          continue;
        } else {
          PrintHelper.printError("Cannot parse argument.");
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
          PrintHelper.printError("Cannot parse argument.");
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
    if (!this.db.isEmpty()) {
      System.out.println("Reading database: " + this.db);
    } else {
      if (!this.student.isEmpty()) {
        System.out.println("Reading student: " + this.db);
      }
      if (!this.staff.isEmpty()) {
        System.out.println("Reading staff: " + this.db);
      }
      if (!this.camp.isEmpty()) {
        System.out.println("Reading camp: " + this.db);
      }
      if (!this.enquiry.isEmpty()) {
        System.out.println("Reading enquiry: " + this.db);
      }
      if (!this.suggestion.isEmpty()) {
        System.out.println("Reading suggestion: " + this.db);
      }
    }
    System.out.println("Store the database to: " + storeTo);
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
