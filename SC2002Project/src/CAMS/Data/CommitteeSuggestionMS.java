package CAMS.Data;

public class CommitteeSuggestionMS {
    private String userID;
    private String campName;
    //added attribute campName to use
    //Database.createSuggestion

    // Constructor
    public CommitteeSuggestionMS(String userID, String campName) {
        this.userID = userID;
        this.campName = campName;
    }

    public String submitSuggestion(String suggestionDescription) {
        // Call the returnSuggestionID method which creates the suggestion and returns the suggestion ID
        String suggestionID = Database.returnSuggestionID(this.userID, suggestionDescription, this.campName);
        return suggestionID; // Return the ID of the new suggestion
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
            Database.deleteSuggestion(suggestionID);
            return 0; // success
        } else {
            System.out.println("Suggestion not found or access denied.");
            return 1; // failure or no permission
        }
    }
}
