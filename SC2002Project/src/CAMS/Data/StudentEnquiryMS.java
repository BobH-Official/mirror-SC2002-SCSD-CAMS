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
      Enter camp name:\s""").strip();

    while (camp.isEmpty()) {
      Database.printCampsForStudent(userID);
      camp = console.readLine(STR."""
        Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
        Enter camp name:\s""").strip();
    }

    String faculty = Database.facultyOf(camp);

    if (faculty == null) {
      System.out.println("No such a Camp: " + camp);
      return null;
    } else if (!faculty.equals(Database.facultyOf(userID)) &&
      !(faculty.equals("NTU"))) {
      System.out.println(STR. "ERROR: you do not have access to \{ camp }." );
      return null;
    }

    String msg = console.readLine(STR. """
      What message do you want to send to \{ camp }?
      message:
      """ ).strip();

    String enquiry = Database.createEnquiry(userID, msg, camp);

    System.out.println(STR. """
    Enquiry created: ID: \{ enquiry.toUpperCase() }
    \{ Database.findEnquiry(enquiry) }
    """ .strip());

    return enquiry;
  }
}
