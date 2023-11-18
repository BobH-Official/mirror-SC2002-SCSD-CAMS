package CAMS.Data;

import java.io.Console;

public class StudentEnquiryMS {

  private final String userID;

  public StudentEnquiryMS(String userID) {
    this.userID = userID;
  }

  public String createEnquiry() {
    System.out.println("\n## ENQUIRY CREATION ##\n");
    Console console = System.console();
    String camp = console.readLine(STR."""
      Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
      Enter camp name:
      """).strip();

    while (camp.isEmpty()) {
      Database.printCampsForStudent(userID);
      camp = console.readLine(STR."""
        Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
        Enter camp name:
        """).strip();
    }

    String faculty = Database.facultyOf(camp);

    if (faculty == null) {
      return null;
    } else if (!faculty.equals(Database.facultyOf(userID)) &&
      !(faculty.equals("NTU"))) {
      System.out.println("ERROR: you do not have access to Camp7.");
      return null;
    }

    String msg = console.readLine(STR."""
      What message do you want to send to Camp1?
      message:
      """).strip();

    String enquiry = Database.createEnquiry(userID, msg, camp);

    System.out.println(STR. """
    Enquiry created: ID: \{ enquiry }
    \{ Database.findEnquiry(enquiry) }
    """ );

    return enquiry;
  }
}
