package CAMS.Operator;

import CAMS.Data.StudentCampMS;
import CAMS.Data.StudentEnquiryMS;
import CAMS.Data.StudentMS;

import java.io.Console;
import java.util.regex.Pattern;

public class StudentOperator extends UserOperator {

  private final StudentMS userMS;

  private final StudentEnquiryMS enquiryMS;

  private final StudentCampMS campMS;


  public StudentOperator(String id) {
    super(id);

    userMS = new StudentMS(id);

    enquiryMS = new StudentEnquiryMS(id);

    this.campMS = new StudentCampMS(id);
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
              3. view camps
              4. join camp
              5. withdraw from camp
              6. join camp as committee member
          ENQUIRY
              7. create enquiry.
              8. view enquiries
              9. edit enquiry
              10. delete enquiry

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
        this.viewCamp();
        return 0;
      }
      case "4" -> {
        joinCamp();
        return 0;
      }
      case "5" -> {
        withdrawCamp();
        return 0;
      }
      case "6" -> {
        joinCampAsCM();
        return 3;
      }
      case "7" -> {
        createEnquiry();
        return 0;
      }
      case "8" -> {
        viewEnquiry();
        return 0;
      }
      case "9" -> {
        editEnquiry();
        return 0;
      }
      case "10" -> {
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
  }

  private int changePassword() {
    return userMS.changePassword();
  }

  private void viewCamp() {
    campMS.viewCamps();
  }

  private void joinCamp() {
    String camp = userMS.joinCamp();
    if (campMS.addStudent(camp)) {
      userMS.addCamp(camp);
    }
  }

  private void withdrawCamp() {
    String camp = userMS.withdrawCamp();
    if (campMS.withdrawStudent(camp)) {
      userMS.deleteCamp(camp);
    }
  }

  private void joinCampAsCM() {
    String camp = userMS.joinCampAsCM();
    if (campMS.addCommitteeMember(camp)) {
      userMS.becomeCMOf(camp);
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

  private void viewEnquiry() {
    enquiryMS.viewOwnEnquiries();
  }

  private void editEnquiry() {
    enquiryMS.editOwnEnquiries();
  }

  private void deleteEnquiry() {
    String enquiry = enquiryMS.deleteOwnEnquiry();
    if (enquiry == null) {
      return;

    }

    campMS.deleteEnquiry(enquiry);

    userMS.deleteEnquiry(enquiry);
  }

  private int logout() {
    return 1;
  }

  private int quitProgram() {
    return 2;
  }
}
