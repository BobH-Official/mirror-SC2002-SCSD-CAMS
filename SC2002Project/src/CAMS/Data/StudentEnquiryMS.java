package CAMS.Data;

import java.io.Console;
import java.util.List;
import java.util.Objects;

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
      return null;
    } else if (!faculty.equals(Database.facultyOf(userID)) &&
      !(faculty.equals("NTU"))) {
      System.out.println(STR. "ERROR: you do not have access to \{ camp }." );
      return null;
    }

    String msg = console.readLine(STR. """
      What message do you want to send to \{ camp }?
      message:\s""" ).strip();

    String enquiry = Database.createEnquiry(userID, msg, camp);

    System.out.println(STR. """
    Enquiry created: ID: \{ enquiry.toUpperCase() }
    \{ Database.findEnquiry(enquiry) }
    """ .strip());

    return enquiry;
  }

  public void viewOwnEnquiries() {

    System.out.println("\n## ENQUIRY VIEWING ##\n");
    Console console = System.console();
    String camp = console.readLine(
      "Which camp do you want to view enquiry from? " +
        "(leave blank to print all the camp for your faculty)\n" +
        "Enter camp name: ").strip();

    while (camp.isEmpty()) {
      Database.printCampsForStudent(userID);
      camp = console.readLine("Which camp do you want to view " +
        "enquiry from? (leave blank to print all the camp for your faculty)\n" +
        "Enter camp name: ").strip();
    }

    String faculty = Database.facultyOf(camp);

    if (faculty == null) {
      System.out.println("No such a Camp: " + camp);
      return;
    } else if (!faculty.equals(Database.facultyOf(userID)) &&
      !(faculty.equals("NTU"))) {
      System.out.println(STR. "ERROR: you do not have access to \{ camp }." );
      return;
    }

    System.out.println(STR. "These are all enquiry for the camp: \{ camp }" );

    List<RequestData<EnquiryStatus>> enquiries =
      Database.getCampRequestList(camp, Database.RequestType.ENQUIRY);

    for (int i = 0; i < enquiries.size(); i += 1) {
      //only show the enquiry that student make for this camp
      if (Objects.equals(enquiries.get(i).sender(), userID)) {
        System.out.print(STR. "    \{ i }. " );
        System.out.println(
          enquiries.get(i).toString().indent(4).stripLeading());
      }
    }
  }

  public void deleteEnquiry(String id) {
    String sender = Database.findEnquiry(id).sender();
    if (!(Objects.equals(sender, this.userID))) {
      System.err.println(
        STR. "Error: User \{ this.userID }: do not have access to the data, failed to delete." );
    }
    Database.deleteEnquiry(id);
  }

}
