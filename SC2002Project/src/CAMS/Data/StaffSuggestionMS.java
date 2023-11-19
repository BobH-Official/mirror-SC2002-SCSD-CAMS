package CAMS.Data;

public class StaffSuggestionMS {
    private String userID;

    // Constructor
    public StaffSuggestionMS(String userID) {
        this.userID = userID;
    }

    // Method to view details of a Suggestion
    public boolean viewSuggestion(String suggestionID) {
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion == null) {
            return false;
        }
        System.out.println("Suggestion Details: " + suggestion);
        return true;
    }

    // Method to approve a Suggestion
    public void approveSuggestion(String suggestionID) {
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion == null) {
            return;
        }
        suggestion.approve();
        System.out.println("Suggestion approved for ID: " + suggestionID);
    }

    // Method to reject a Suggestion
    public void rejectSuggestion(String suggestionID) {
        SuggestionData suggestion = Database.findSuggestion(suggestionID);
        if (suggestion == null) {
            return;
        }
        suggestion.reject();
        System.out.println("Suggestion rejected for ID: " + suggestionID);
    }
}
