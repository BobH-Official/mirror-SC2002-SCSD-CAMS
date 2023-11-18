package CAMS.Data;

//importing the relevant database java classes

import java.util.*;

public class Database {

  // Static maps
  private static final HashMap<String, UserData> userMap = new HashMap<>();
  private static final HashMap<String, EnquiryData> enquiryMap =
    new HashMap<>();
  private static final HashMap<String, SuggestionData> suggestionMap =
    new HashMap<>();
  private static final HashMap<String, CampData> campMap = new HashMap<>();

  public static void initialize() {
    Database.createStudent(/*name*/"Example Student", /*email*/
      "STUDENT001@e.ntu.edu.sg", "SCSE", "password");
  }

  static void createStudent(String name, String email, String faculty,
                            String password) {
    // create a new student data object and then add it into the user hashmap
    StudentData studentData = new StudentData(name, email, faculty, password);
    userMap.put(studentData.id(), studentData);
  }

  // Static methods to create instances of data classes and then add them into
  // their respective hashmaps

  static void createStaff(String name, String email, String faculty,
                          String password, List<String> camps) {
    // create a new staff data object and then add it into the user hashmap
    StaffData staffData = new StaffData(name, email, faculty, password, camps);
    userMap.put(staffData.id(), staffData);
  }

  static String createEnquiry(String sender, String message, String camp) {
    // Create an enquiry data object and then add it into the enquiry hashmap
    EnquiryData enquiryData = new EnquiryData(sender, message, camp);
    enquiryMap.put(enquiryData.id(), enquiryData);
    return enquiryData.id();
  }

  static void createSuggestion(String sender, String message, String camp) {
    // Create a suggestion data object and then add it into the enquiry hashmap
    SuggestionData suggestionData = new SuggestionData(sender, message, camp);
    suggestionMap.put(suggestionData.id(), suggestionData);
  }

  static void createCamp(String name, String staff, String userGroup,
                         boolean visibility, String description, Date startDate,
                         Date endDate, Date registrationClosingDate,
                         String location, int campTotalSlots,
                         int committeeSlots) {

    // Create a camp data object and then add it into the camp hashmap
    CampData campData =
      new CampData(name, staff, userGroup, visibility, description, startDate,
        endDate, registrationClosingDate, location, campTotalSlots,
        committeeSlots);
    campMap.put(campData.name(), campData);
  }

  public static void deleteEnquiry(String id) {
    enquiryMap.remove(id);
  }

  public static void deleteRequestForCamp(String id) {

    if (enquiryMap.containsKey(id)) {
      String camp = Database.findEnquiry(id).camp();
      Database.findCamp(camp).deleteEnquiry(id);
    }
    if (suggestionMap.containsKey(id)) {
      String camp = Database.findSuggestion(id).camp();
      Database.findCamp(camp).deleteSuggestion(id);
    }
  }

  static EnquiryData findEnquiry(String id) {
    // Check if the enquiryMap contains the specified id
    // Return the corresponding EnquiryData object
    // Enquiry not found, return null
    return enquiryMap.getOrDefault(id, null);
  }

  static CampData findCamp(String id) {
    // Check if the enquiryMap contains the specified id
    // Return the corresponding EnquiryData object
    // Enquiry not found, return null
    return campMap.getOrDefault(id, null);
  }

  static SuggestionData findSuggestion(String id) {
    // Check if the suggestionMap contains the specified id
    // Return the corresponding SuggestionData object
    // Suggestion not found, return null
    return suggestionMap.getOrDefault(id, null);
  }

  public static void deleteSuggestion(String id) {
    if (!suggestionMap.containsKey(id)) {
      suggestionMap.remove(id);
    }
  }

  public static void deleteCamp(String id) {
    if (!campMap.containsKey(id)) {
      campMap.remove(id);
    }
  }

  static List<String> getCampsList() {
    // Implementation for getting the list of camp names
    // Return the list of camp names
    return new ArrayList<>(campMap.keySet());
  }

  // Static methods to get camp data
  static List<String> getCampListByFaculty(String faculty) {
    List<String> camps = new ArrayList<>();
    for (CampData camp : campMap.values()) {
      if (Objects.equals(camp.information().faculty(), faculty)) {
        camps.add(camp.name());
      }
    }
    return camps;
  }

  static void printCampsByFaculty(String faculty) {
    // Implementation for printing camps by faculty

    System.out.println("Camps in Faculty:");

    for (CampData camp : campMap.values()) {
      if (Objects.equals(camp.information().faculty(), faculty)) {
        System.out.println("    Camp: " + camp.name());
      }
    }
  }

  static void printCampsForStudent(String id) {
    // Implementation for printing camps by faculty
    System.out.println("Camps:");
    String faculty = Objects.requireNonNull(Database.findStudent(id)).faculty();

    int index = 0;
    for (CampData camp : campMap.values()) {
      if (Objects.equals(camp.information().faculty(), "NTU") &&
        camp.isVisible()) {
        System.out.println(STR. "    \{ index }: " + camp.name());
      }
    }

    for (CampData camp : campMap.values()) {
      if (Objects.equals(camp.information().faculty(), faculty) &&
        camp.isVisible()) {
        System.out.println(STR. "    \{ index }: " + camp.name());
      }
    }
  }


  // Static methods to find data from the hashmaps

  static StudentData findStudent(String id) {
    if (!userMap.containsKey(id)) {
      return null;
    }

    UserData user = userMap.get(id);

    if (user instanceof StudentData) {
      return (StudentData) user;
    }

    return null;
  }

  static String facultyOf(String id) {
    if (userMap.containsKey(id)) {
      UserData data = Database.userMap.get(id);
      return data.faculty();
    }
    if (campMap.containsKey(id)) {
      CampData data = Database.campMap.get(id);
      return data.information().faculty();
    }
    return null;
  }

  static CAMS.Data.Utils.Pair<CAMS.Data.UserType, UserData> findUser(
    String id) {
    if (!userMap.containsKey(id)) {
      // Return the corresponding UserData object
      System.err.println(STR. "User \{ id } not found in database." );
      return null;
    }

    UserData user = userMap.get(id);

    if (user instanceof StudentData) {
      return new CAMS.Data.Utils.Pair<>(CAMS.Data.UserType.STUDENT, user);
    }

    if (user instanceof StaffData) {
      return new CAMS.Data.Utils.Pair<>(CAMS.Data.UserType.STAFF, user);
    }

    return null;
  }

  static StaffData findStaff(String userID) {
    // Check if the userMap contains the specified userID
    if (userMap.containsKey(userID) &&
      userMap.get(userID) instanceof StaffData) {
      // Return the corresponding StaffData object
      return (StaffData) userMap.get(userID);
    } else {
      // Staff not found or not of the correct type, return null
      return null;
    }
  }


}
