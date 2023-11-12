package CAMS.Data;

import java.util.Date;
import java.util.List;

public class CampData {
    private String campName;
    private boolean visible;
    private StaffData staffInCharge;
    private CampInformation campInformation;
    private CampStudentList campAttendeeList;
    private CampStudentList campWaitlist;
    private CampBlackList campBlacklist;
    private CampCommitteeList campCommitteeList;
    private CampRequestList campRequests;
    private CampRequestList campEnquiries;
    private CampRequestList campSuggestions;

    public CampData(StaffData staffInCharge, boolean visible, String campName,
                    CampInformation campInformation, CampStudentList campAttendeeList,
                    CampStudentList campWaitlist, CampBlackList campBlacklist,
                    CampCommitteeList campCommitteeList, CampRequestList campRequests,
                    CampRequestList campEnquiries, CampRequestList campSuggestions) {

        this.staffInCharge = staffInCharge;
        this.visible = visible;
        this.campName = campName;
        this.campInformation = campInformation;
        this.campAttendeeList = campAttendeeList;
        this.campWaitlist = campWaitlist;
        this.campBlacklist = campBlacklist;
        this.campCommitteeList = campCommitteeList;
        this.campRequests = campRequests;
        this.campEnquiries = campEnquiries;
        this.campSuggestions = campSuggestions;
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