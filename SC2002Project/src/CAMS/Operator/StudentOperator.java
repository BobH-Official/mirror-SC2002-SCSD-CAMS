package CAMS.Operator;

import CAMS.Data.Database;
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

  @Override
  public boolean doOperation() {
    Console console = System.console();
    String choice = console.readLine(STR."""
      Choose your operation:
          1. create enquiry.
          2. logout
      Enter your choice:
      """).strip();

    
    switch (choice) {

      case "0" -> {
        userMS.changePassword("new pass");
        return true;
      }

      case "1" -> {
        String enquiry = enquiryMS.createEnquiry();
        if (enquiry == null) {
          return true;
        }
        if (!campMS.addEnquiry(enquiry)) {

          Database.deleteEnquiry(enquiry);
          return true;
        }

        if (!userMS.addEnquiry(enquiry)) {
          Database.deleteEnquiry(enquiry);
          Database.deleteRequestForCamp(enquiry);
          return true;
        }

        return true;
      }

      default -> {
        return false;
      }
    }
  }
}
