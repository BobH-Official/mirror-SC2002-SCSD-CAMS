package CAMS.Data;

public class CommitteeSuggestionMS {
    private String userID;

    // Constructor
    public CommitteeSuggestionMS(String userID) {
        this.userID = userID;
    }

    // Method to submit a suggestion and return the suggestion ID
    public String submitSuggestion(String suggestionDescription) {
        // Create a new suggestion data object and add it to the suggestion map in Database
        Database.createSuggestion(this.userID, suggestionDescription, "Default Camp");
        //made temproary module getLastInsertedSuggestionID cuz there's no way to obtain the ID
        // of the suggestion cuz the database doesn't return the ID in any circumstances
        // but accoridng to the ULM, this module is supposed to return the ID.
        String suggestionID = Database.getLastInsertedSuggestionID();
        return suggestionID;
    }

    // Method to view own suggestion details
    public int viewOwnSuggestion(String suggestionID) {
        // Retrieve the suggestion using its ID
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        // Check if the suggestion exists and belongs to the user
        if (suggestion != null && suggestion.sender().equals(this.userID)) {
            System.out.println("Suggestion Details: " + suggestion);
            return 0; // success
        } else {
            System.out.println("Suggestion not found or access denied.");
            return 1; // failure or no permission
        }
    }

    // Method to edit own suggestion
    public int editOwnSuggestion(String suggestionID, String newMessage) {
        // Retrieve the suggestion using its ID
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        // Check if the suggestion exists and belongs to the user
        if (suggestion != null && suggestion.sender().equals(this.userID)) {
            suggestion.setMessage(newMessage);
            Database.updateSuggestion(suggestionID, suggestion);
            return 0; // success
        } else {
            System.out.println("Suggestion not found or access denied.");
            return 1; // failure or no permission
        }
    }

    // Method to delete own suggestion
    public int deleteOwnSuggestion(String suggestionID) {
        // Retrieve the suggestion using its ID
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        // Check if the suggestion exists and belongs to the user
        if (suggestion != null && suggestion.sender().equals(this.userID)) {
            // made temporary module Database.deleteSuggestion exists to delete the suggestion
            // there's only deleteEnuiry in the Student operator
            //nothing about deleting suggestion in Student or Committee Operator
            Database.deleteSuggestion(suggestionID);
            return 0; // success
        } else {
            System.out.println("Suggestion not found or access denied.");
            return 1; // failure or no permission
        }
    }
}
