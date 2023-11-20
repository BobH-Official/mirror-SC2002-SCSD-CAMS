package CAMS.Data;

import java.io.Console;
import java.util.List;
import java.util.regex.Pattern;

public class StaffSuggestionMS {
  private final String userID;

  // Constructor
  public StaffSuggestionMS(String id) {
    this.userID = id;
  }

  // Method to approve a Suggestion
  public void suggestionActions() {

    // 1. you view the suggestions
    // copy the code in viewSuggestions, you will have a List
    viewSuggestion();

    // 2. you get the ID of the suggestion, input is a string

    Console console = System.console();

    if (console == null) {
      System.err.println("No console available");
      return;
    }

    String suggestionID =
      console.readLine("Enter the ID of the suggestion you want to act on: ")
        .strip();

    //Not sure why choice is needed here
    /*String choice = "1";

    while (isNotNumeric(choice)) {
      System.out.println("Please enter a valid number");
      choice = "2";
    }*/


    // 3. you ask for action
    System.out.println("\nChoose your action:");
    System.out.println("1. Approve this suggestion.");
    System.out.println("2. Reject this suggestion.");

    String actionChoiceStr;
    int actionChoice;

    // Loop until a valid numeric choice is entered
    while (true) {
      actionChoiceStr = console.readLine("Type in your choice: ").strip();
      if (isNotNumeric(actionChoiceStr)) {
        System.out.println("Invalid input. Please enter a numeric choice.");
      } else {
        // Change numeric string to an integer
        actionChoice = Integer.parseInt(actionChoiceStr);
        break;
      }
    }

    // 4. Perform the action
    SuggestionData suggestion = Database.findSuggestion(suggestionID);
    if (suggestion == null) {
      System.out.println("No suggestion found for ID: " + suggestionID);
      return;
    }

    switch (actionChoice) {
      case 1:
        suggestion.approve();
        System.out.println("Suggestion approved for ID: " + suggestionID);
        break;
      case 2:
        suggestion.reject();
        System.out.println("Suggestion rejected for ID: " + suggestionID);
        break;
      default:
        System.out.println("Invalid choice.");
    }
  }

  // Method to view details of a Suggestion
  public void viewSuggestion() {
    System.out.println("\n## VIEW SUGGESTION ##\n");
    Console console = System.console();
    String camp = console.readLine(STR."""
      Which camp's suggestion do you want to view? (leave blank to print all the camp for your faculty)
      Enter camp name:\s""").strip();

    while (camp.isEmpty()) {
      Database.printCampsForStaff(userID);
      camp = console.readLine(STR."""
        Which camp's suggestion do you want to view? (leave blank to print all the camp for your faculty)
        Enter camp name:\s""").strip();
    }
    System.out.println(STR. "These are all messages for the camp: \{ camp }" );
    List<RequestData<SuggestionStatus>> suggestions =
      Database.getCampRequestList(camp, Database.RequestType.SUGGESTION);

    for (int i = 0; i < suggestions.size(); i += 1) {
      System.out.print(STR. "    \{ i }. " );
      System.out.println(
        suggestions.get(i).toString().indent(4).stripLeading());
    }
  }

  boolean isNotNumeric(String strNum) {
    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    if (strNum == null) {
      return true;
    }
    return !pattern.matcher(strNum).matches();
  }


}
