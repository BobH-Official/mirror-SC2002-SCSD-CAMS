package CAMS.Data;

public class StaffCampMS {
  private final String userID;

  public StaffCampMS(String userID) {
    this.userID = userID;
  }

  public void createCamp() {

    // 1. ask for camp informations


    // 2. update database

  }

  public void viewCamp() {
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

    // 7. return to step 3, unless step 5. is true
  }

  public void editCamp() {
    // 1. ask for camp id


    // 2. ask for which field to edit
    // only camp description, location can be edited if there are student/committee member in the camp
    // if no attendee/CM, all information should be able to edit.

    // 3. get the input

    // 4. update the campdata.
  }

  public void deleteCamp() {
  }
}
