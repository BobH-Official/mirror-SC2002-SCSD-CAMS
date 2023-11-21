package CAMS.Data;

public class StudentMS extends CAMS.Data.UserMS {
  public StudentMS(String id) {
    super(id);
  }

  public boolean addEnquiry(String id) {
    // 1. find out the Student data in database, called student

    // 2. check if student is cm
    // if false, return false;

    // 2. return student.addEnquiry(camp)
    return true;
  }

  public boolean addSuggestion(String id) {
    // 1. find out the Student data in database, called student

    // 2. return student.addEnquiry(camp)
    return true;
  }

  public String joinCamp() {
    // 1. find out the Student data in database, called student

    // 2. ask for user input, it should not be empty

    // 3. check if user input == student.getCampAsCommitteeMember()
    // if true: print error message and then return null;

    // 4. check if student.getCampAsAttendee().contain(user input)
    // if true: print error message and then return null;

    // 5. return the user input.
    // (do not modify the student data, addCamp() will do)
    return "";
  }

  public void addCamp(String camp) {
    // 1. find out the Student data in database, called student

    // 2. call student.joinAsAttendeeOf(camp)
    // 3. check the value get from last step, if false, print error message
  }

  public String withdrawCamp() {
    // 1. find out the Student data in database, called student

    // 2. ask for user input, it should not be empty

    // 3. check if user input == student.getCampAsCommitteeMember()
    // if true: print error message and then return null;

    // 4. check if student.getCampAsAttendee().contain(user input)
    // if false: print error message and then return null;

    // 5. return the user input.
    // (do not modify the student data, deleteCamp() will do)
    return "";
  }

  public void deleteCamp(String camp) {
    // 1. find out the Student data in database, called student

    // 2. check if camp == student.getCampAsCommitteeMember()
    // if true, return;

    // 3. call student.withdrawFrom(camp)
  }

  public String joinCampAsCM() {
    // 1. find out the Student data in database, called student

    // 2. check if the student is cm already
    // if is cm already: print error message and then return null;

    // 3. ask for user input, it should not be empty


    // 4. return the user input.
    // (do not modify the student data, becomeCMOf() will do)
    return "";
  }

  public void becomeCMOf(String camp) {
    // 1. find out the Student data in database, called student

    // 2. call student.joinAsCommitteeMemberOf(camp)
  }

  public boolean deleteEnquiry(String enquiry) {
    // 1. find out the Student data in database, called student

    // 2. return student.removeEnquiry(enquiry);
    return true;
  }

  public boolean deleteSuggestion(String suggestion) {

    // 1. find out the Student data in database, called student

    // 2. check if student is cm
    // if false, return false;

    // 2. return student.removeSuggestion(suggestion);
    return true;
  }
}
