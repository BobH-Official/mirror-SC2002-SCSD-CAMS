package CAMS.Data;
import java.util.Date;
import Utils.DataRange;

public class CampInformation {
    private DateRange campDates;
    private Date campRegistrationClosingDate;
    private String campUserGroup;
    private String campLocation;
    private int campTotalSlots;
    private int campCommitteeSlots;
    private String campDescription;

    public void CampInformation(Date campStartDate, Date campEndDate, Date campRegistrationClosingDate,
    String campUserGroup, String campLocation, int campTotalSlots,int campCommitteeSlots, String campDescription) {
    this.campDates = new DateRange(campStartDate, campEndDate);
    this.campRegistrationClosingDate = campRegistrationClosingDate;
    this.campUserGroup = campUserGroup;
    this.campLocation = campLocation;
    this.campTotalSlots = campTotalSlots;
    this.campCommitteeSlots = campCommitteeSlots;
    this.campDescription = campDescription;
}

    //Getters & Setters : campDates
public void setCampStartDate(Date campStartDate){
    this.campDates.setStartDate(campStartDate);
}

public Date getCampStartDate(){
    return this.campDates.getStartDate();
}

public void setCampEndDate(Date campEndDate){
    this.campDates.setEndDate(campEndDate);
}

public Date getCampEndDate(){
    return this.campDates.getEndDate();
}

//Getters & Setters : campRegistrationClosingDate
public void setCampRegistrationClosingDate(Date campRegistrationClosingDate) {
    this.campRegistrationClosingDate = campRegistrationClosingDate;
}

public Date getCampRegistrationClosingDate() {
    return this.campRegistrationClosingDate;
}

// Getters & Setters : campUserGroup
public void setUserGroup(String campUserGroup) {
    this.campUserGroup = campUserGroup;
}

public String getUserGroup() {
    return this.campUserGroup;
}

// Getters & Setters : campLocation
public void setCampLocation(String campLocation) {
    this.campLocation = campLocation;
}

public String getCampLocation() {
    return this.campLocation;
}

// Getters and Setters for campTotalSlots
public void setCampTotalSlots(int campTotalSlots) {
    this.campTotalSlots = campTotalSlots;
}

public int getCampTotalSlots() {
    return this.campTotalSlots;
}

// Getters and Setters for campCommitteeSlots
public void setCampCommitteeSlots(int campCommitteeSlots) {
    this.campCommitteeSlots = campCommitteeSlots;
}

public int getCampCommitteeSlots() {
    return this.campCommitteeSlots;
}

// Getters & Setters: campDescription
public void setCampDescription(String campDescription) {
    this.campDescription = campDescription;
}

public String getDescription() {
    return this.campDescription;
}

// Additional methods
public void printSelf() {
    
}

@Override
public String toString() {
    // Implement toString() method
    return super.toString();
}
}

