package CAMS.Operator;

import CAMS.Data.StudentEnquiryMS;
import CAMS.Data.StudentMS;

import java.io.Console;
import java.util.regex.Pattern;

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
  public int doOperation() {
    Console console = System.console();
    String choice = console.readLine(STR."""
      Choose your operation: (role: STUDENT)
          INFORMATION
              1. view information.
              2. change password
          CAMP
              3. join camp
              4. withdraw from camp
              5. join camp as committee member
          ENQUIRY
              6. create enquiry.
              7. view enquiries
              8. edit enquiry
              9. delete enquiry

          o. logout (type in logout/o/any numbers)
          q. quit program (type in quit/q/any letters)
      Enter your choice:\s""").strip();


    switch (choice) {

      case "1" -> {
        return viewInformation();
      }
      case "2" -> {
        return changePassword();
      }
      case "3" -> {
        joinCamp();
        return 0;
      }
      case "4" -> {
        withdrawCamp();
        return 0;
      }
      case "5" -> {
        joinCampAsCM();
        return 3;
      }
      case "6" -> {
        createEnquiry();
        return 0;
      }
      case "7" -> {
        viewEnquiry();
        return 0;
      }
      case "8" -> {
        editEnquiry();
        return 0;
      }
      case "9" -> {
        deleteEnquiry();
        return 0;
      }
      case String s when Pattern.compile("\\d+\\.?\\d*").matcher(s)
        .matches() -> {
        return logout();
      }
      case "o", "logout" -> {
        return logout();
      }
      default -> {
        return quitProgram();
      }
    }
  }

  private int viewInformation() {
    return this.userMS.viewInformation();
//    return 0;
  }

  private int changePassword() {
    return userMS.changePassword();
//    return 0;
  }

  private void joinCamp() {

    String camp = userMS.joinCamp();
    if (!campMS.addStudent(camp)) {
      userMS.deleteCamp(camp);
    }
//    return 0;
  }

  private void withdrawCamp() {
//    return 0;
  }

  private void joinCampAsCM() {
//    return 3;
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

  private void viewEnquiry() {
//    return 0;
  }

  private void editEnquiry() {
//    return 0;
  }

  private void deleteEnquiry() {
//    return 0;
  }

  private int logout() {
    return 1;
  }

  private int quitProgram() {
    return 2;
  }
}
