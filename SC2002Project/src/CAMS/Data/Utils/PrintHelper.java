package CAMS.Data.Utils;

public class PrintHelper {
  public static void printError(String msg) {
    System.err.println(STR. "\033[31m\{ msg }\033[0m" );
  }
}
