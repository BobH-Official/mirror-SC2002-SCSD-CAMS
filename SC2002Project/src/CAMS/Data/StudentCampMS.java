package CAMS.Data;

public class StudentCampMS {
  private final String userID;

  public StudentCampMS(String id) {
    this.userID = id;
  }

  public boolean addEnquiry(String id) {
    String camp = Database.findEnquiry(id).camp();
    Database.findCamp(camp).addEnquiry(id);
    return true;
  }

  public boolean deleteEnquiry(String id) {
    if (Database.isNotInDatabase(id)) {
      return false;
    }
    String camp = Database.facultyOf(id);
    return Database.findCamp(camp).deleteEnquiry(id);
  }

  public boolean addStudent(String camp) {
    return false;
  }

  public boolean withdrawStudent(String camp) {
    return true;
  }

  public boolean addCommitteeMember(String camp) {
    return true;
  }

  public void viewCamps() {

    // 1. ask for filter, default alphabetically
    // (the get camps funcs in Database returns alphabetically)

    // 2. get the camp list from database, and sort the list
    // for student,
    // call Database.getCampListForStudent(String userID)
    // for sorting, use switch to match Comparator, default to use name.
    // camps.sort(Comparator.comparing(CampData::name));

    // 3. print the names

    // 4. ask for the index

    // 5. if is out of index, or input e/q, to exit the function

    // 6. print the camp details
    // for student: print name, description, Location, faculty, dates,
    // closing registration dates, remaining slots, replied enquiries

    // 7. return to step 3, unless step 5. is true
  }
}
