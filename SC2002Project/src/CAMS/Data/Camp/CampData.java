package CAMS.Data;

import java.util.Date;
import java.util.List;

class CampData {
    private String campName;
    private boolean visible;
    private String staff;
    private CampInformation campInformation;
    private CampStudentList campAttendeeList;
    private CampStudentList campCommitteeList;
    private CampBlackList campBlackList;
    private CampRequestList campRequests;
    private CampRequestList campEnquiries;
    private CampRequestList campSuggestions;

    public CampData(String staff, boolean visible, String campName,
                    Date campStartDate, Date campEndDate, Date campRegistrationClosingDate,
                    String campUserGroup, String campLocation, int campTotalSlots,
                    int campCommitteeSlots, String campDescription) {
        this.staff = staff;
        this.visible = visible;
        this.campName = campName;

        this.campInformation = new CampInformation(campStartDate, campEndDate, 
            campRegistrationClosingDate, campUserGroup, campLocation, 
            campTotalSlots, campCommitteeSlots, campDescription);

        this.campAttendeeList = new CampStudentList(campTotalSlots); // Assuming CampStudentList takes the total slots as a parameter
        this.campCommitteeList = new CampStudentList(campCommitteeSlots); // Assuming the same here for committee slots
        this.campBlackList = new CampBlackList();
        this.campEnquiries = new CampRequestList();
        this.campSuggestions = new CampRequestList();
    }


    void addAttendee(String userID) throws IllegalStateException{
        // Check if the user is on the blacklist.
        if (campBlackList.contains(userID)) {
            throw new IllegalStateException("Cannot add attendee. User is on the blacklist.");
        }
        // Check if the user is already a committee member.
        if (campCommitteeList.contains(userID)) {
            throw new IllegalStateException("Cannot add attendee. User is a committee member.");
        }
        // If checks pass, add the user as an attendee.
        campAttendeeList.addMember(userID);
    }
    
    void addCommitteeMember(String userID) throws IllegalStateException{
        // Check if the user is on the blacklist.
        if (campBlackList.contains(userID)) {
            throw new IllegalStateException("Cannot add committee member. User is on the blacklist.");
        }
        // Check if the user is already an attendee.
        if (campAttendeeList.contains(userID)) {
            throw new IllegalStateException("Cannot add committee member. User is an attendee.");
        }
        // If checks pass, add the user as a committee member.
        campCommitteeList.addMember(userID);
    }
    
    void withdrawAttendee(String userID) throws IllegalStateException{
        // Check if the user is an attendee before attempting to remove.
        if (!campAttendeeList.contains(userID)) {
            throw new IllegalStateException("Cannot withdraw attendee. User is not an attendee.");
        }
        // If the check passes, remove the user from the attendee list.
        campAttendeeList.withdrawMember(userID);
    }
    

    String getCampName(){
        return this.campName;
    }

    boolean isVisible(){
        return this.visible;
    }

    String getStaff(){
        return this.staff;
    }

    CampInformation getCampInformation() {
        return this.campInformation;
    }

    CampStudentList getStudentList() {
        return this.campAttendeeList;
    }

    CampStudentList getCommitteeMembers() {
        return campCommitteeList;
    }

    CampBlackList getBlacklist() {
        return this.campBlackList;
    }

    CampRequestList getRequests() {
        return campRequests;
    }

    CampRequestList getEnquiries() {
        return campEnquiries;
    }

    CampRequestList getSuggestions() {
        return campSuggestions;
    }

    void printSelf(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "CampData:\n" +
            "  campName='" + this.campName + "'\n" +
            "  visible=" + this.visible + "\n" +
            "  staff='" + this.staff + "'\n" +
            "  campInformation=" + this.campInformation + "\n" +
            "  campAttendeeList=" + this.campAttendeeList + "\n" +
            "  campCommitteeList=" + this.campCommitteeList + "\n" +
            "  campBlackList=" + this.campBlackList + "\n" +
            "  campRequests=" + this.campRequests + "\n" +
            "  campEnquiries=" + this.campEnquiries + "\n" +
            "  campSuggestions=" + this.campSuggestions + "\n";
}



}