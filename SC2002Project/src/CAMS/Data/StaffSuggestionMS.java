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

  // Method to approve a Suggestion
  public void suggestionActions() {


    // 1. you view the suggestions
    // copy the code in viewSuggestions, you will have a List

    // 2. you get the ID of the suggestion, input is a string

    String choice = "1";

    while (isNotNumeric(choice)) {
      // ask for input again

      choice = "2";
    }


    // 3. you ask for action

    // 4. you preform the action
//        SuggestionData suggestion = Database.findSuggestion(suggestionID);
//        if (suggestion == null) {
//            return;
//        }
//        suggestion.approve();
//        System.out.println("Suggestion approved for ID: " + suggestionID);
  }

  boolean isNotNumeric(String strNum) {
    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    if (strNum == null) {
      return true;
    }
    return !pattern.matcher(strNum).matches();
  }


}
