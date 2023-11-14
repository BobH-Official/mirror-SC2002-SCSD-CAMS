package CAMS.Data;
import java.util.Date;
import CAMS.Data.DataRange;

class CampInformation {
    private DateRange campDates;
    private Date registerCloseDate;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int committeeSlots;
    private String description;

    public void CampInformation(Date campStartDate, Date campEndDate, Date campRegistrationClosingDate,
    String campUserGroup, String campLocation, int campTotalSlots,int campCommitteeSlots, String campDescription) {
    this.campDates = new DateRange(campStartDate, campEndDate);
    this.registerCloseDate = campRegistrationClosingDate;
    this.userGroup = campUserGroup;
    this.location = campLocation;
    this.totalSlots = campTotalSlots;
    this.committeeSlots = campCommitteeSlots;
    this.description = campDescription;
}

    //Getters & Setters : campDates
protected void setCampStartDate(Date campStartDate){
    this.campDates.setStartDate(campStartDate);
}

protected Date CampStartDate(){
    return this.campDates.getStartDate();
}

protected void setCampEndDate(Date campEndDate){
    this.campDates.setEndDate(campEndDate);
}

protected Date CampEndDate(){
    return this.campDates.getEndDate();
}

//Getters & Setters : campRegistrationClosingDate
protected void setCampRegistrationClosingDate(Date campRegistrationClosingDate) {
    this.campRegistrationClosingDate = campRegistrationClosingDate;
}

protected Date CampRegistrationClosingDate() {
    return this.campRegistrationClosingDate;
}

// Getters & Setters : campUserGroup
protected void setUserGroup(String campUserGroup) {
    this.campUserGroup = campUserGroup;
}

protected String UserGroup() {
    return this.campUserGroup;
}

// Getters & Setters : campLocation
protected void setCampLocation(String campLocation) {
    this.campLocation = campLocation;
}

protected String CampLocation() {
    return this.campLocation;
}

// Getters and Setters for campTotalSlots
protected void setCampTotalSlots(int campTotalSlots) {
    this.campTotalSlots = campTotalSlots;
}

protected int CampTotalSlots() {
    return this.campTotalSlots;
}

// Getters and Setters for campCommitteeSlots
protected void setCampCommitteeSlots(int campCommitteeSlots) {
    this.campCommitteeSlots = campCommitteeSlots;
}

protected int CampCommitteeSlots() {
    return this.campCommitteeSlots;
}

// Getters & Setters: campDescription
protected void setCampDescription(String campDescription) {
    this.campDescription = campDescription;
}

protected String Description() {
    return this.campDescription;
}

// Additional methods
void printSelf() {
    System.out.println(this);
}

@Override
public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("StaffData:\n");
    stringBuilder.append("    name: ").append(this.name).append(";\n");
    stringBuilder.append("    email: ").append(this.email).append(";\n");
    stringBuilder.append("    userID: ").append(this.userID).append(";\n");
    stringBuilder.append("    password: ").append(this.password).append(";\n");
    stringBuilder.append("    faculty: ").append(this.faculty).append(";\n");
    stringBuilder.append("    campInCharge:\n");

    if (this.campInCharge != null && !this.campInCharge.isEmpty()) {
        for (int i = 0; i < this.campInCharge.size(); i++) {
            stringBuilder.append("        ").append(i + 1).append(". ").append(this.campInCharge.get(i)).append("\n");
        }
    } else {
        stringBuilder.append("        No camps assigned.\n");
    }

    return stringBuilder.toString();
}

}
