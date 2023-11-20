package CAMS.Operator;

import CAMS.Data.*;

import java.io.Console;
import java.util.regex.Pattern;

public class CommitteeMemberOperator extends UserOperator {
  private final CAMS.Data.StudentMS userMS;
  private final CAMS.Data.StudentEnquiryMS enquiryMS;
  private final CAMS.Data.ManagerEnquiryMS managerEnquiryMS;
  private final CAMS.Data.CommitteeSuggestionMS suggestionMS;
  private final CAMS.Data.StudentCampMS campMS;
  private final CAMS.Data.CommitteeMemberCampMS managerCampMS;

  public CommitteeMemberOperator(String id) {
    super(id);
    this.userMS = new StudentMS(id);

    this.enquiryMS = new StudentEnquiryMS(id);

    this.managerEnquiryMS = new ManagerEnquiryMS(id);

    this.suggestionMS = new CommitteeSuggestionMS(id);

    this.campMS = new StudentCampMS(id);
    this.managerCampMS = new CommitteeMemberCampMS(id);
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
              3. join camp
              4. withdraw from camp
          ENQUIRY
              5. create enquiry
              6. view enquiries
              7. edit enquiry
              8. delete enquiry
          CAMP MANAGEMENT
              9.  reply enquiry
              10. create suggestion
              11. view suggestions
              12. edit suggestion
              13. delete suggestion

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
        createEnquiry();
        return 3;
      }
      case "6" -> {
        viewEnquiry();
        return 0;
      }
      case "7" -> {
        editEnquiry();
        return 0;
      }
      case "8" -> {
        deleteEnquiry();
        return 0;
      }
      case "9" -> {
        this.replyEnquiry();
        return 0;
      }
      case "10" -> {
        this.createSuggestion();

        return 0;
      }
      case "11" -> {
        this.viewSuggestion();
        return 0;
      }
      case "12" -> {
        this.editSuggestion();
        return 0;
      }
      case "13" -> {
        this.deleteSuggestion();
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

  private int logout() {
    return 1;
  }

  private int quitProgram() {
    return 2;
  }
}
