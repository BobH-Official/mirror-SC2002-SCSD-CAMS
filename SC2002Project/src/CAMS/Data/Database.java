package CAMS.Data;

//importing the relevant database java classes

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

//import all the relevant dataclasses
import CAMS.Data.StudentData;
import CAMS.Data.StaffData;
import CAMS.Data.EnquiryData;
import CAMS.Data.SuggestionData;
import CAMS.Data.CampData;

public class Database {

  // Static maps
  private static HashMap<String, UserData> userMap = new HashMap<>();
  private static HashMap<String, EnquiryData> enquiryMap = new HashMap<>();
  private static HashMap<String, SuggestionData> suggestionMap = new HashMap<>();
  private static HashMap<String, CampData> campMap = new HashMap<>();

  // Static methods to create instances of data classes and then add them into
  // their respective hashmaps

  public static void createStudent(String name, String email, String faculty) {
    // create a new student data object and then add it into the user hashmap
    StudentData studentData = new StudentData();
    userMap.put(studentData.userID(), studentData);
  }

  public static void createStaff(String name, String email, String faculty) {
    // create a new staff data object and then add it into the user hashmap
    StaffData staffData = new StaffData();
    userMap.put(staffData.userID(), staffData);

  }

  public static void createEnquiry(String sender, String message, String camp) {
    // Create an enquiry data object and then add it into the enquiry hashmap
    EnquiryData enquiryData = new EnquiryData(sender, message, camp);
    enquiryMap.put(enquiryData.id(), enquiryData);
  }

  public static void createSuggestion(String sender, String message, String camp) {
    // Create a suggestion data object and then add it into the enquiry hashmap
    SuggestionData suggestionData = new SuggestionData(sender, message, camp);
    enquiryMap.put(suggestionData.id(), suggestionData);
  }

  public static void createCamp(String name, String staff, String userGroup, boolean visibility,
                                String description, Date startDate, Date endDate,
                                Date registrationClosingDate, String location, int campTotalSlots,
                                int committeeSlots) {

    // Create a camp data object and then add it into the camp hashmap
    CampData campData = new CampData(name, staff, userGroup, visibility, description, startDate, endDate,
      registrationClosingDate,
      location, campTotalSlots, committeeSlots);
    campMap.put(campData.name(), campData);
  }

  // Static methods to get camp data

  public static List<String> getCampsList() {
    // Implementation for getting the list of camp names

    // Get a list of all camp names from the campMap then insert them into the camps
    // list
    List<String> camps = new ArrayList<>(campMap.keySet());

    // Return the list of camp names
    return camps;
  }

  public static List<String> getCampListByFaculty(String faculty) {
    List<String> camps = new ArrayList<>();
    for (CampData camp : campMap.values()) {
      if (camp.information().userGroup() == faculty) {
        camps.add(camp.name());
      }
    }
    return camps;
  }

  public static void printCampsByFaculty(String faculty) {
    // Implementation for printing camps by faculty

    System.out.println("Camps in Faculty:");

    for (CampData camp : campMap.values()) {
      if (camp.information().userGroup() == faculty) {
        System.out.println("    Camp: " + camp.name());
      }
    }
  }

  // Static methods to find data from the hashmaps

  public static Pair<UserType, UserData> findUser(String userID) {
    if (!userMap.containsKey(userID)) {
      // Return the corresponding UserData object
      return null;
    }

    CAMS.data.UserData user = userMap.get(id);

    if (user instanceof CAMS.Data.StudentData) {
      return Pair(CAMS.Data.UserType.STUDENT, user);
    }

    if (user instanceof CAMS.Data.StaffData) {
      return Pair(CAMS.Data.UserType.STAFF, user);
    }

    return null;
  }

  public static StudentData findStudent(String id) {
    if (!userMap.containsKey(id)) {
      return null;
    }

    CAMS.data.UserData user = userMap.get(id);

    if (user instanceof CAMS.Data.StudentData) {
      return (CAMS.Data.StudentData) user;
    }

    return null;
  }

  public static StaffData findStaff(String userID) {
    // Check if the userMap contains the specified userID
    if (userMap.containsKey(userID) && userMap.get(userID) instanceof StaffData) {
      // Return the corresponding StaffData object
      return (StaffData) userMap.get(userID);
    } else {
      // Staff not found or not of the correct type, return null
      return null;
    }
  }

  public static EnquiryData findEnquiry(String id) {
    // Check if the enquiryMap contains the specified id
    if (enquiryMap.containsKey(id)) {
      // Return the corresponding EnquiryData object
      return enquiryMap.get(id);
    } else {
      // Enquiry not found, return null
      return null;
    }
  }

  public static SuggestionData findSuggestion(String id) {
    // Check if the suggestionMap contains the specified id
    if (suggestionMap.containsKey(id)) {
      // Return the corresponding SuggestionData object
      return suggestionMap.get(id);
    } else {
      // Suggestion not found, return null
      return null;
    }
  }
}
