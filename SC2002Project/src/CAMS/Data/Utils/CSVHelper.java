package CAMS.Data.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CSVHelper {
  public static List<List<List<String>>> readDB(String path) {
    List<List<List<String>>> returnList = new ArrayList<>();
    List<List<String>> staffs = new ArrayList<>();
    List<List<String>> students = new ArrayList<>();
    List<List<String>> camps = new ArrayList<>();
    List<List<String>> enquiries = new ArrayList<>();
    List<List<String>> suggestions = new ArrayList<>();
    try {
      ZipFile zf = new ZipFile(path);
      Enumeration<? extends ZipEntry> entries = zf.entries();

      while (entries.hasMoreElements()) {
        ZipEntry ze = entries.nextElement();
        System.out.println("Read " + ze.getName());
        if (ze.getName().matches(".+\\.csv$")) {
          String fileContent = "";
          long size = ze.getSize();
          if (size > 0) {
            System.out.println("Length is " + size);
            BufferedReader br =
              new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
            String line;
            while ((line = br.readLine()) != null) {
              fileContent = fileContent.concat(line);
            }
            br.close();
          }
          switch (ze.getName()) {
            case "staff.csv" -> {
              staffs = CSVHelper.parseCSV(fileContent);
            }
            case "student.csv" -> {
              students = CSVHelper.parseCSV(fileContent);
            }
            case "camp.csv" -> {
              camps = CSVHelper.parseCSV(fileContent);
            }
            case "enquiry.csv" -> {
              enquiries = CSVHelper.parseCSV(fileContent);
            }
            case "suggestion.csv" -> {
              suggestions = CSVHelper.parseCSV(fileContent);
            }
            default -> {
            }
          }
        }
      }
    } catch (IOException e) {
      PrintHelper.printError(STR. "Error: can not read \{ path }" );
    }
    returnList.add(staffs);
    returnList.add(students);
    returnList.add(camps);
    returnList.add(enquiries);
    returnList.add(suggestions);
    return returnList;
  }

  private static List<List<String>> parseCSV(String content) {
    List<List<String>> returnList = new ArrayList<>();

    String[] strList = content.split("\n");
    for (String str : strList) {
      returnList.add(List.of(
        str.replaceAll(/*regex*/" ", /*replacement*/"").split(/*regex*/",")));
    }
    System.out.println(returnList);

    return returnList;
  }

  public static List<List<String>> readCSV(String path) {
    List<List<String>> returnList = new ArrayList<>();

    Scanner sc = null;
    try {
      sc = new Scanner(new File(path));
    } catch (FileNotFoundException e) {
      PrintHelper.printError(
        STR. "Error: can not read file \{ path }.\n\{ e.getMessage() }" );
    }
    if (sc == null) {
      PrintHelper.printError(STR. "Error: can not read file \{ path }." );
    }

    assert sc != null;
    sc.useDelimiter("\n");   //sets the delimiter pattern

    while (sc.hasNext()) {
      returnList.add(List.of(
        sc.next().replaceAll(/*regex*/" ", /*replacement*/"")
          .split(/*regex*/",")));
    }
    System.out.println(returnList);

    return returnList;
  }
}
