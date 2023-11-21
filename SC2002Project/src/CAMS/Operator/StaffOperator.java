package CAMS.Operator;

import CAMS.Data.*;

import java.io.Console;
import java.util.regex.Pattern;

public class StaffOperator extends UserOperator {
  private final StaffMS userMS;
  private final ManagerEnquiryMS enquiryMS;

  private final StaffSuggestionMS suggestionMS;

  private final StaffCampMS campMS;

  private final CampReportMS reportMS;

  public StaffOperator(String id) {
    super(id);
    this.userMS = new StaffMS(id);

    this.enquiryMS = new ManagerEnquiryMS(id);

    this.suggestionMS = new StaffSuggestionMS(id);

    this.campMS = new StaffCampMS(id);

    this.reportMS = new CampReportMS(id);
  }

  @Override
  public int doOperation() {
    Console console = System.console();
    String choice = console.readLine(STR."""
      Choose your operation: (role: COMMITTEE MEMBER)
          INFORMATION
              1. view information.
              2. change password
          CAMP
              3. create camp
              4. view camps
              5. edit camp
              6. delete camp
          ENQUIRY
              7. view enquiries
              8. reply enquiry
          SUGGESTION
              9.  view suggestions
              10. approve/reject suggestions
          REPORT
              11. generate report

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
        this.createCamp();
        return 0;
      }
      case "4" -> {
        this.viewCamp();
        return 0;
      }
      case "5" -> {
        this.editCamp();
        return 3;
      }
      case "6" -> {
        this.deleteCamp();
        return 0;
      }
      case "7" -> {
        this.viewEnquiries();
        return 0;
      }
      case "8" -> {
        this.replyEnquiry();
        return 0;
      }
      case "9" -> {
        this.viewSuggestion();
        return 0;
      }
      case "10" -> {
        this.editSuggestion();
        return 0;
      }
      case "11" -> {
        this.createReport();
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
    int result = this.userMS.viewInformation();
    System.console().readLine("Enter enter to continue..");
    return result;
  }

  private int changePassword() {
    return userMS.changePassword();
//    return 0;
  }

  private void createCamp() {
    campMS.createCamp();
  }

  private void viewCamp() {
    campMS.viewCamp();
    System.console().readLine("Enter enter to continue..");
  }

  private void editCamp() {
    campMS.editCamp();
  }

  private void deleteCamp() {
    campMS.deleteCamp();
  }

  private void viewEnquiries() {
    enquiryMS.view();
    System.console().readLine("Enter enter to continue..");
  }

  private void replyEnquiry() {
    enquiryMS.reply();
  }

  private void viewSuggestion() {
    suggestionMS.viewSuggestion();
    System.console().readLine("Enter enter to continue..");
  }

  private void editSuggestion() {
    suggestionMS.suggestionActions();
  }

  private void createReport() {
    reportMS.generate();
  }

  private int logout() {
    return 1;
  }

  private int quitProgram() {
    return 2;
  }
}
