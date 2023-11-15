package CAMS.Data;

public class StaffSuggestionMS {
    private String userID;

    // Constructor
    public StaffSuggestionMS(String userID) {
        this.userID = userID;
    }

    // Method to view details of a Suggestion
    public void viewSuggestion(String suggestionID) {
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion != null) {
            System.out.println("Suggestion Details: " + suggestion);
        } else {
            System.out.println("Suggestion not found for ID: " + suggestionID);
        }
    }

    // Method to approve a Suggestion
    public void approveSuggestion(String suggestionID) {
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion != null) {
            suggestion.approve();
            System.out.println("Suggestion approved for ID: " + suggestionID);
        } else {
            System.out.println("Suggestion not found for ID: " + suggestionID);
        }
    }

    // Method to reject a Suggestion
    public void rejectSuggestion(String suggestionID) {
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion != null) {
            suggestion.reject();
            System.out.println("Suggestion rejected for ID: " + suggestionID);
        } else {
            System.out.println("Suggestion not found for ID: " + suggestionID);
        }
    }
}

