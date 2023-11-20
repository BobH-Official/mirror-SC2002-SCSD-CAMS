package CAMS.Operator;

import CAMS.Data.*;

import java.io.Console;
import java.util.regex.Pattern;

public class CommitteeMemberOperator extends UserOperator {
  private final StudentMS userMS;
  private final StudentEnquiryMS enquiryMS;
  private final ManagerEnquiryMS managerEnquiryMS;
  private final CommitteeSuggestionMS suggestionMS;
  private final StudentCampMS campMS;
  private final CommitteeMemberCampMS managerCampMS;
  private final CampReportMS reportMS;

  public CommitteeMemberOperator(String id) {
    super(id);
    this.userMS = new StudentMS(id);

    this.enquiryMS = new StudentEnquiryMS(id);

    this.managerEnquiryMS = new ManagerEnquiryMS(id);

    this.suggestionMS = new CommitteeSuggestionMS(id);

    this.campMS = new StudentCampMS(id);

    this.managerCampMS = new CommitteeMemberCampMS(id);

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
              3. view camps
              4. join camp
              5. withdraw from camp
          ENQUIRY
              6. create enquiry
              7. view enquiries
              8. edit enquiry
              9. delete enquiry
          CAMP MANAGEMENT
              10. view committee camp
              11. reply enquiry
              12. create suggestion
              13. view suggestions
              14. edit suggestion
              15. delete suggestion
          REPORT
              16. generate report

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
        createEnquiry();
        return 3;
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
      case "10" -> {
        this.viewCMCamp();
        return 0;
      }
      case "11" -> {
        this.replyEnquiry();
        return 0;
      }
      case "12" -> {
        this.createSuggestion();

        return 0;
      }
      case "13" -> {
        this.viewSuggestion();
        return 0;
      }
      case "14" -> {
        this.editSuggestion();
        return 0;
      }
      case "15" -> {
        this.deleteSuggestion();
        return 0;
      }
      case "16" -> {
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

  private void viewCMCamp() {
    managerCampMS.viewCamp();
  }

  private void replyEnquiry() {
    managerEnquiryMS.reply();
  }

  private void createSuggestion() {
    String suggestion = suggestionMS.createSuggestion();
    if (suggestion == null) {
      return;
    }
    if (!managerCampMS.addSuggestion(suggestion)) {
      suggestionMS.deleteEnquiry(suggestion);
      return;
    }
    if (!userMS.addEnquiry(suggestion)) {
      suggestionMS.deleteEnquiry(suggestion);
      managerCampMS.deleteSuggestion(suggestion);
    }
  }

  private void viewSuggestion() {
    suggestionMS.viewOwnSuggestion();
  }

  private void editSuggestion() {
    suggestionMS.editOwnSuggestion();
  }

  private void deleteSuggestion() {
    String suggestion = suggestionMS.deleteOwnSuggestion();
    if (suggestion == null) {
      return;
    }

    managerCampMS.deleteSuggesion(suggestion);

    userMS.deleteSuggestion(suggestion);
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
