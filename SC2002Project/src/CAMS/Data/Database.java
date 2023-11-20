package CAMS.Data;

//importing the relevant database java classes

import CAMS.Data.Utils.CLIArgs;
import CAMS.Data.Utils.DateHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Database {

  // Static maps
  private static final HashMap<String, UserData> userMap = new HashMap<>();
  private static final HashMap<String, EnquiryData> enquiryMap =
    new HashMap<>();
  private static final HashMap<String, SuggestionData> suggestionMap =
    new HashMap<>();
  private static final HashMap<String, CampData> campMap = new HashMap<>();

  public static void initialize(CLIArgs args) {
    Database.createStudent(/*name*/"Example Student", /*email*/
      "STUDENT001@e.ntu.edu.sg", "SCSE", "password");

    Database.createCamp("Camp1", "STAFF001", "NTU", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);

    Database.createCamp("Camp2", "STAFF001", "SCSE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp3", "STAFF001", "SCSE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp4", "STAFF001", "NTU", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp5", "STAFF001", "NTU", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp6", "STAFF001", "EEE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp7", "STAFF001", "SCSE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp8", "STAFF001", "SCSE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp9", "STAFF001", "SCSE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
  }

  static void createStudent(String name, String email, String faculty,
                            String password) {
    // create a new student data object and then add it into the user hashmap
    StudentData studentData = new StudentData(name, email, faculty, password);
    userMap.put(studentData.id(), studentData);
  }

  static boolean createCamp(String name, String staff, String userGroup,
                            boolean visibility, String description,
                            Date startDate, Date endDate,
                            Date registrationClosingDate, String location,
                            int campTotalSlots, int committeeSlots) {

    if (campMap.containsKey(name)) {
      System.out.println("Camp Already Exists");
      return false;
    }
    // Create a camp data object and then add it into the camp hashmap
    CampData campData =
      new CampData(name, staff, userGroup, visibility, description, startDate,
        endDate, registrationClosingDate, location, campTotalSlots,
        committeeSlots);
    campMap.put(campData.name(), campData);
    return true;
  }

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

  static String createSuggestion(String sender, String message, String camp) {
    // Create a suggestion data object and then add it into the enquiry hashmap
    SuggestionData suggestionData = new SuggestionData(sender, message, camp);
    suggestionMap.put(suggestionData.id(), suggestionData);
    return suggestionData.id();
  }

  public static void storeDB(String path) {
    try (final FileOutputStream fos = new FileOutputStream(path)) {
      ZipOutputStream zipOut = new ZipOutputStream(fos);
//      ZipEntry zipEntry = ;
      zipOut.setLevel(9);
      zipOut.putNextEntry(new ZipEntry("mimetype"));
      zipOut.write("application/camsdb+zip".getBytes(StandardCharsets.UTF_8));
      zipOut.putNextEntry(new ZipEntry("META-INF/container.xml"));
      zipOut.write("""
        <?xml version="1.0" encoding="UTF-8"?>
        <container version="1.0" xmlns="ntu:sc2002:cams:xmlns:container">
          <files>
            <file full-path="staff.csv" media-type="text/csv" />
            <file full-path="student.csv" media-type="text/csv" />
            <file full-path="camp.csv" media-type="text/csv" />
            <file full-path="enquiry.csv" media-type="text/csv" />
            <file full-path="suggestion.csv" media-type="text/csv" />
          </files>
        </container>
        """.strip().getBytes(StandardCharsets.UTF_8));
      zipOut.putNextEntry(new ZipEntry("staff.csv"));
      zipOut.write(
        Database.getCsvUsers().strip().getBytes(StandardCharsets.UTF_8));
      zipOut.close();
    } catch (IOException e) {
      System.err.println(e.getLocalizedMessage());
    }
  }

  static String getCsvUsers() {
    String str = "userID,password,name,email,faculty\n";
    for (UserData user : userMap.values()) {
      str = str.concat(user.toCsv());
    }
    return str.strip();
  }

  static String getCsvStaff() {
    String str = "userID,password,name,email,faculty,camps\n";
    for (UserData user : userMap.values()) {
      if (Objects.requireNonNull(user) instanceof StaffData staff) {
        str = str.concat(staff.toCsv());
      }
    }
    return str.strip();
  }

  static String getCsvStudent() {
    String str = "userID,password,name,email,faculty\n";
    for (UserData user : userMap.values()) {
      if (Objects.requireNonNull(user) instanceof StudentData student) {
        str = str.concat(student.toCsv());
      }
    }
    return str.strip();
  }

  static String getCsvCamp() {
    String str = "name,staff,visibility,start,end,closingRegistration," +
      "faculty,location,totalSlots,committeeSlots,description,attendee," +
      "committeeMembers,blacklist,enquiries,suggestions\n";
    for (CampData camp : campMap.values()) {
      str = str.concat(camp.toCsv());
    }
    return str.strip();
  }

  static String getCsvSuggestions() {
    String str = "id,sender,camp,status,message";
    for (SuggestionData suggestion : suggestionMap.values()) {
      str = str.concat(suggestion.toCsv());
    }
    return str.strip();
  }

  static String getCsvSEnquiries() {
    String str = "id,sender,camp,status,message,reply";
    for (EnquiryData enquiries : enquiryMap.values()) {
      str = str.concat(enquiries.toCsv());
    }
    return str.strip();
  }

  static void deleteEnquiry(String id) {
    enquiryMap.remove(id);
  }

  static void deleteSuggestion(String id) {
    if (!suggestionMap.containsKey(id)) {
      suggestionMap.remove(id);
    }
  }

  static void deleteCamp(String id) {
    if (!campMap.containsKey(id)) {
      campMap.remove(id);
    }
  }

  static List<String> getCampsList() {
    // Implementation for getting the list of camp names
    // Return the list of camp names
    List<String> list = new ArrayList<>(campMap.keySet());
    Collections.sort(list);
    return list;
  }

  static List<CampData> getCampListForStudent(String id) {
    String faculty = Objects.requireNonNull(Database.findStudent(id)).faculty();

    List<CampData> camps = getCampListByFaculty(faculty);
    camps.addAll(getCampListByFaculty("NTU"));
    camps.sort(Comparator.comparing(CampData::name));

    return camps;
  }

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

  // Static methods to get camp data
  static List<CampData> getCampListByFaculty(String faculty) {
    List<CampData> camps = new ArrayList<>();
    List<CampData> camplist = new ArrayList<>(campMap.values());
    camplist.sort(Comparator.comparing(CampData::name));
    for (CampData camp : camplist) {
      if (Objects.equals(camp.information().faculty(), faculty)) {
        camps.add(camp);
      }
    }
    return camps;
  }

  static void printCampsByFaculty(String faculty) {
    // Implementation for printing camps by faculty

    System.out.println("Camps in Faculty:");
    List<CampData> camps = new ArrayList<>(campMap.values());
    camps.sort(Comparator.comparing(CampData::name));
    for (CampData camp : camps) {
      if (Objects.equals(camp.information().faculty(), faculty)) {
        System.out.println("    Camp: " + camp.name());
      }
    }
  }

  static void printCampsForStudent(String id) {
    // Implementation for printing camps by faculty
    System.out.println("Camps:");
    String faculty = Objects.requireNonNull(Database.findStudent(id)).faculty();

    int index = 1;
    System.out.print(STR."    ");

    List<CampData> camps = new ArrayList<>(campMap.values());
    camps.sort(Comparator.comparing(CampData::name));
    for (CampData camp : camps) {
      if ((Objects.equals(camp.information().faculty(), "NTU") ||
        Objects.equals(camp.information().faculty(), faculty)) &&
        camp.isVisible()) {
        if (index % 5 == 0) {
          System.out.print(STR."\n    ");
        }
        System.out.print(STR. "\{ camp.name() } " );
        index += 1;
      }
    }
    System.out.println();
  }

  static <T extends RequestStatus> List<RequestData<T>> getCampRequestList(
    String camp, RequestType type) {
    List<String> requestIds;
    List<RequestData<T>> requests = new ArrayList<>();
    switch (type) {
      case ENQUIRY -> {
        requestIds = Database.findCamp(camp).enquiries().requests();
        for (String id : requestIds) {
          EnquiryData data = Database.findEnquiry(id);
          requests.add((RequestData<T>) data);
        }
      }
      case SUGGESTION -> {
        requestIds = Database.findCamp(camp).suggestions().requests();
        for (String id : requestIds) {
          SuggestionData data = Database.findSuggestion(id);
          requests.add((RequestData<T>) data);
        }
      }
    }
    return requests;
  }

  static CampData findCamp(String id) {
    // Check if the enquiryMap contains the specified id
    // Return the corresponding EnquiryData object
    // Enquiry not found, return null
    if (!campMap.containsKey(id)) {
      System.err.println("Error: Camp not found. ID: " + id);
    }
    return campMap.getOrDefault(id, null);
  }

  static EnquiryData findEnquiry(String id) {
    // Check if the enquiryMap contains the specified id
    // Return the corresponding EnquiryData object
    // Enquiry not found, return null
    if (!enquiryMap.containsKey(id)) {
      System.err.println("Error: Enquiry not found. ID: " + id);
    }
    return enquiryMap.getOrDefault(id, null);
  }

  static SuggestionData findSuggestion(String id) {
    // Check if the suggestionMap contains the specified id
    // Return the corresponding SuggestionData object
    // Suggestion not found, return null
    if (!suggestionMap.containsKey(id)) {
      System.err.println("Error: Suggestion not found. ID: " + id);
    }
    return suggestionMap.getOrDefault(id, null);
  }

  static void printCampsForStaff(String id) {
    System.out.println("Camps:");
    List<String> camps =
      Objects.requireNonNull(Database.findStaff(id)).getCampsUnderManagement();
    Collections.sort(camps);
    int index = 1;
    System.out.print(STR."    ");
    for (String camp : camps) {
      if (index % 5 == 0) {
        System.out.print(STR."\n    ");
      }
      System.out.print(STR. "\{ camp } " );
      index += 1;
    }
  }


  // Static methods to find data from the hashmaps

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

  static String facultyOf(String id) {
    if (userMap.containsKey(id)) {
      UserData data = Database.userMap.get(id);
      return data.faculty();
    }
    if (campMap.containsKey(id)) {
      CampData data = Database.campMap.get(id);
      return data.information().faculty();
    }

    System.err.println("No such object in database: " + id);
    return null;
  }

  static String campOf(String id) {
    if (enquiryMap.containsKey(id)) {
      EnquiryData data = Database.enquiryMap.get(id);
      return data.camp();
    }
    if (suggestionMap.containsKey(id)) {
      SuggestionData data = Database.suggestionMap.get(id);
      return data.camp();
    }
    System.err.println("No such object in database: " + id);
    return null;
  }

  static boolean isNotInDatabase(String id) {
    if (userMap.containsKey(id)) {
      return false;
    }
    if (campMap.containsKey(id)) {
      return false;
    }
    if (enquiryMap.containsKey(id)) {
      return false;
    }
    if (suggestionMap.containsKey(id)) {
      return false;
    }
    System.err.println("No such object in database: " + id);
    return true;
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

    return new CAMS.Data.Utils.Pair<>(CAMS.Data.UserType.NOT_FOUND, user);
  }

  enum RequestType {
    ENQUIRY, SUGGESTION,

  }
}
