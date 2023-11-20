package CAMS.Data;

import java.io.Console;

public class CommitteeSuggestionMS {
  private final String userID;

  // Constructor
  public CommitteeSuggestionMS(String userID) {
    this.userID = userID;
  }

  public boolean createSuggestion() {
    Console console = System.console();
    String campName =
      console.readLine("Enter the name of the camp for your suggestion: ")
        .strip();
    if (campName.isEmpty()) {
      System.err.println("Camp name cannot be empty.");
      return false;
    }

    String description =
      console.readLine("Enter your suggestion description: ").strip();
    if (description.isEmpty()) {
      System.err.println("Description cannot be empty.");
      return false;
    }

    Database.createSuggestion(this.userID, description, campName);
    System.out.println("Suggestion created successfully.");
    return true;
  }

  public boolean viewOwnSuggestion() {
    Console console = System.console();
    String suggestionID =
      console.readLine("Enter the ID of your suggestion to view: ").strip();
    if (suggestionID.isEmpty()) {
      System.err.println("Suggestion ID cannot be empty.");
      return false;
    }

    SuggestionData suggestion = Database.findSuggestion(suggestionID);
    if (suggestion == null || !suggestion.sender().equals(this.userID)) {
      System.err.println("Suggestion not found or access denied.");
      return false;
    }
    System.out.println("Suggestion Details: " + suggestion);
    return true;
  }

  public boolean editOwnSuggestion() {
    Console console = System.console();
    String suggestionID =
      console.readLine("Enter the ID of your suggestion to edit: ").strip();
    if (suggestionID.isEmpty()) {
      System.err.println("Suggestion ID cannot be empty.");
      return false;
    }

    SuggestionData suggestion = Database.findSuggestion(suggestionID);
    if (suggestion == null || !suggestion.sender().equals(this.userID)) {
      System.err.println("Suggestion not found or access denied.");
      return false;
    }

    String newMessage =
      console.readLine("Enter the new message for your suggestion: ").strip();
    if (newMessage.isEmpty()) {
      System.err.println("New message cannot be empty.");
      return false;
    }

    System.out.println("Suggestion updated successfully.");
    return true;
  }

  public boolean deleteOwnSuggestion() {
    Console console = System.console();
    String suggestionID =
      console.readLine("Enter the ID of your suggestion to delete: ").strip();
    if (suggestionID.isEmpty()) {
      System.err.println("Suggestion ID cannot be empty.");
      return false;
    }

    SuggestionData suggestion = Database.findSuggestion(suggestionID);
    if (suggestion == null || !suggestion.sender().equals(this.userID)) {
      System.err.println("Suggestion not found or access denied.");
      return false;
    }

    Database.deleteSuggestion(suggestionID);
    System.out.println("Suggestion deleted successfully.");
    return true;
  }
}
