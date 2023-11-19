package CAMS.Operator;

import CAMS.Data.StudentEnquiryMS;
import CAMS.Data.StudentMS;

import java.io.Console;

public class StudentOperator extends UserOperator {

  private final CAMS.Data.StudentMS userMS;

  private final CAMS.Data.StudentEnquiryMS enquiryMS;

  private final CAMS.Data.StudentCampMS campMS;


  public StudentOperator(String id) {
    super(id);

    userMS = new StudentMS(id);

    enquiryMS = new StudentEnquiryMS(id);

    this.campMS = new CAMS.Data.StudentCampMS(id);
  }

  private boolean editEnquiry() {
    return true;
  }

  private boolean joinCamp() {
    return true;
  }

  private boolean joinCampAsCM() {
    return true;
  }

  @Override
  public boolean doOperation() {
    Console console = System.console();
    String choice = console.readLine(STR."""
      Choose your operation:
          1. create enquiry.
          5. logout
          q. exit
      Enter your choice:\s""").strip();


    switch (choice) {

      case "1" -> {
        createEnquiry();
        return true;
      }
      case "100", "q" -> {
        return quitProgram();
      }
      default -> {
        return false;
      }
    }
  }

  private void createEnquiry() {
    String enquiry = enquiryMS.createEnquiry();
    if (enquiry == null) {
      return;
    }
    if (!campMS.addEnquiry(enquiry)) {
      enquiryMS.deleteEnquiry(enquiry);
      return;
    }
    if (!userMS.addEnquiry(enquiry)) {
      enquiryMS.deleteEnquiry(enquiry);
      campMS.deleteEnquiry(enquiry);
    }
  }

  private boolean quitProgram() {
    return false;
  }
}
