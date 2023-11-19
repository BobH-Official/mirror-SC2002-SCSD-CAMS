package CAMS.Data;

import java.io.Console;

public class CommitteeSuggestionMS {
    private String userID;
    private Console console;

    // Constructor
    public CommitteeSuggestionMS(String userID) {
        this.userID = userID;
        this.console = System.console();
    }

    public boolean createSuggestion() {
        System.out.print("Enter the name of the camp for your suggestion: ");
        String campName = console.readLine();
        if (campName == null || campName.trim().isEmpty()) {
            System.err.println("Camp name cannot be empty.");
            return false;
        }

        System.out.print("Enter your suggestion description: ");
        String description = console.readLine();
        if (description == null || description.trim().isEmpty()) {
            System.err.println("Description cannot be empty.");
            return false;
        }

        Database.createSuggestion(this.userID, description, campName);
        System.out.println("Suggestion created successfully.");
        return true;
    }

    public boolean viewOwnSuggestion() {
        System.out.print("Enter the ID of your suggestion to view: ");
        String suggestionID = console.readLine();
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion == null || !suggestion.sender().equals(this.userID)) {
            System.err.println("Suggestion not found or access denied.");
            return false;
        }
        System.out.println("Suggestion Details: " + suggestion);
        return true;
    }

    public boolean editOwnSuggestion() {
        System.out.print("Enter the ID of your suggestion to edit: ");
        String suggestionID = console.readLine();
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion == null || !suggestion.sender().equals(this.userID)) {
            System.err.println("Suggestion not found or access denied.");
            return false;
        }

        System.out.print("Enter the new message for your suggestion: ");
        String newMessage = console.readLine();
        if (newMessage == null || newMessage.trim().isEmpty()) {
            System.err.println("New message cannot be empty.");
            return false;
        }

        suggestion.setMessage(newMessage); // Assuming SuggestionData has a setMessage method.
        return true;
    }

    public boolean deleteOwnSuggestion() {
        System.out.print("Enter the ID of your suggestion to delete: ");
        String suggestionID = console.readLine();
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion == null || !suggestion.sender().equals(this.userID)) {
            System.err.println("Suggestion not found or access denied.");
            return false;
        }

        Database.deleteSuggestion(suggestionID);
        return true;
    }
}
