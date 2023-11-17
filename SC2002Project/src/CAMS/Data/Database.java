package CAMS.Data;

//importing the relevant database java classes

import java.util.*;

public class Database {

  // Static maps
  private static final HashMap<String, CAMS.Data.UserData> userMap =
    new HashMap<>();
  private static final HashMap<String, CAMS.Data.EnquiryData> enquiryMap =
    new HashMap<>();
  private static final HashMap<String, CAMS.Data.SuggestionData> suggestionMap =
    new HashMap<>();
  private static final HashMap<String, CAMS.Data.CampData> campMap =
    new HashMap<>();

  // Static methods to create instances of data classes and then add them into
  // their respective hashmaps

  static void createStudent(String name, String email, String faculty,
                            String password) {
    // create a new student data object and then add it into the user hashmap
    CAMS.Data.StudentData studentData =
      new CAMS.Data.StudentData(name, email, faculty, password);
    userMap.put(studentData.id(), studentData);
  }

  static void createStaff(String name, String email, String faculty,
                          String password, List<String> camps) {
    // create a new staff data object and then add it into the user hashmap
    CAMS.Data.StaffData staffData =
      new CAMS.Data.StaffData(name, email, faculty, password, camps);
    userMap.put(staffData.id(), staffData);
  }

  static void createEnquiry(String sender, String message, String camp) {
    // Create an enquiry data object and then add it into the enquiry hashmap
    CAMS.Data.EnquiryData enquiryData =
      new CAMS.Data.EnquiryData(sender, message, camp);
    enquiryMap.put(enquiryData.id(), enquiryData);
  }

  static void createSuggestion(String sender, String message, String camp) {
    // Create a suggestion data object and then add it into the enquiry hashmap
    CAMS.Data.SuggestionData suggestionData =
      new CAMS.Data.SuggestionData(sender, message, camp);
    suggestionMap.put(suggestionData.id(), suggestionData);
  }

  static void createCamp(String name, String staff, String userGroup,
                         boolean visibility, String description, Date startDate,
                         Date endDate, Date registrationClosingDate,
                         String location, int campTotalSlots,
                         int committeeSlots) {

    // Create a camp data object and then add it into the camp hashmap
    CAMS.Data.CampData campData =
      new CAMS.Data.CampData(name, staff, userGroup, visibility, description,
        startDate, endDate, registrationClosingDate, location, campTotalSlots,
        committeeSlots);
    campMap.put(campData.name(), campData);
  }

  // Static methods to get camp data

  static List<String> getCampsList() {
    // Implementation for getting the list of camp names
    // Return the list of camp names
    return new ArrayList<>(campMap.keySet());
  }

  static List<String> getCampListByFaculty(String faculty) {
    List<String> camps = new ArrayList<>();
    for (CAMS.Data.CampData camp : campMap.values()) {
      if (Objects.equals(camp.information().userGroup(), faculty)) {
        camps.add(camp.name());
      }
    }
    return camps;
  }

  static void printCampsByFaculty(String faculty) {
    // Implementation for printing camps by faculty

    System.out.println("Camps in Faculty:");

    for (CAMS.Data.CampData camp : campMap.values()) {
      if (Objects.equals(camp.information().userGroup(), faculty)) {
        System.out.println("    Camp: " + camp.name());
      }
    }
  }

  // Static methods to find data from the hashmaps

  static CAMS.Data.Utils.Pair<CAMS.Data.UserType, CAMS.Data.UserData> findUser(
    String id) {
    if (!userMap.containsKey(id)) {
      // Return the corresponding UserData object
      System.err.println(STR. "User \{ id } not found in database." );
      return null;
    }

    CAMS.Data.UserData user = userMap.get(id);

    if (user instanceof CAMS.Data.StudentData) {
      return new CAMS.Data.Utils.Pair<>(CAMS.Data.UserType.STUDENT, user);
    }

    if (user instanceof CAMS.Data.StaffData) {
      return new CAMS.Data.Utils.Pair<>(CAMS.Data.UserType.STAFF, user);
    }

    return null;
  }

  static CAMS.Data.StudentData findStudent(String id) {
    if (!userMap.containsKey(id)) {
      return null;
    }

    CAMS.Data.UserData user = userMap.get(id);

    if (user instanceof CAMS.Data.StudentData) {
      return (CAMS.Data.StudentData) user;
    }

    return null;
  }

  static CAMS.Data.StaffData findStaff(String userID) {
    // Check if the userMap contains the specified userID
    if (userMap.containsKey(userID) &&
      userMap.get(userID) instanceof CAMS.Data.StaffData) {
      // Return the corresponding StaffData object
      return (CAMS.Data.StaffData) userMap.get(userID);
    } else {
      // Staff not found or not of the correct type, return null
      return null;
    }
  }

  static CAMS.Data.EnquiryData findEnquiry(String id) {
    // Check if the enquiryMap contains the specified id
    // Return the corresponding EnquiryData object
    // Enquiry not found, return null
    return enquiryMap.getOrDefault(id, null);
  }

  static CAMS.Data.SuggestionData findSuggestion(String id) {
    // Check if the suggestionMap contains the specified id
    // Return the corresponding SuggestionData object
    // Suggestion not found, return null
    return suggestionMap.getOrDefault(id, null);
  }
}
