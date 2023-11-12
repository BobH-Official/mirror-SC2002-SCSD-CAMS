package CAMS.Data;

import java.util.Date;
import java.util.List;

public class CampData {
    private String campName;
    private boolean visible;
    private StaffData campStaffInCharge;
    private CampInformation campInformation;
    private CampStudentList campAttendeeList;
    private CampStudentList campCommitteeList;
    private CampBlackList campWithdrawBlackList;
    private CampRequestList campRequest;
    private CampRequestList campEnquiries;
    private CampRequestList campSuggestions;

    public CampData(StaffData staffInCharge, boolean visible, String campName,
                    Date campStartDate, Date campEndDate, Date campRegistrationClosingDate,
                    String campUserGroup, String campLocation, int campTotalSlots,
                    int campCommitteeSlots, String campDescription) {
        this.campStaffInCharge = staffInCharge;
        this.visible = visible;
        this.campName = campName;
        
        this.campInformation = new CampInformation(campStartDate, campEndDate, 
            campRegistrationClosingDate, campUserGroup, campLocation, 
            campTotalSlots, campCommitteeSlots, campDescription);
        
        this.campAttendeeList = new CampStudentList();
        this.campCommitteeList = new CampStudentList();
        this.campWithdrawBlackList = new CampBlackList();
        this.campRequest = new CampRequestList();
        this.campEnquiries = new CampRequestList();
        this.campSuggestions = new CampRequestList();
    }

    protected String getCampName(){
        return this.campName;
    }

    protected boolean isVisible(){
        return this.visible;
    }

    protected StaffData getStaff(){
        return this.staffInCharge;
    }

    protected CampInformation getCampInformation() {
        return this.campInformation;
    }

    protected CampStudentList getStudentList() {
        return this.campAttendeeList;
    }

    protected CampBlackList getWithdrawBlacklist() {
        return this.campBlacklist;
    }

    protected CampRequestList getCampRequest() {
        return this.campRequests;
    }

    protected CampRequestList getCampEnquiries() {
        return this.campEnquiries;
    }

}